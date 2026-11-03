package edu.temple.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlin.time.Duration.Companion.seconds

class MyTimerService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val countDown = intent?.getIntExtra(timerValKey, 0)
        intent?.run{
            getIntExtra(timerValKey, 0).let{
                CoroutineScope(Dispatchers.Default).launch{
                    for(i in countDown downTo  0) {
                        delay(1.seconds)
                        Log.d("Countdown", "$i")
                    }
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
}
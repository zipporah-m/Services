package edu.temple.services

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.temple.services.ui.theme.ServicesTheme

val timerValKey = "timerValue"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServicesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoundownTimer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}

@Composable
fun CoundownTimer(modifier: Modifier = Modifier) {

    var timerValue by rememberSaveable {mutableStateOf("")}

    val context = LocalContext.current

    Column(Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        TextField(
            value = timerValue,
            onValueChange = {timerValue = it},
            label = { Text("Enter a number")}
        )

        Button(onClick = {
            val intent = Intent(context, MyTimerService::class.java)
            intent.putExtra(timerValKey, timerValue.toInt())
            context.startService(intent) }

        // professor did it like context.startService(Intent(context, MyTimerService::class.java)
        // .apply{intent.putExtra("timerValue", timerValue.toInt())})
        )
        {
            Text("Start Timer")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServicesTheme {
        CoundownTimer(
            modifier = Modifier.padding(16.dp)
        )
    }
}
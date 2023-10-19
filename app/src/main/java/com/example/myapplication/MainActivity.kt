package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NumberGuessingGame()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
        Text( text = "Milena")
    }
}

@Composable
fun NumberGuessingGame() {
    var minRange by remember {mutableStateOf(-50)}
    var maxRange by remember {mutableStateOf(50)}
    var sliderValue by remember {mutableStateOf((minRange + maxRange) / 2)}
    var targetValue by remember {mutableStateOf((minRange + maxRange) / 2)}

    var score by remember {mutableStateOf(0)}
    var message by remember {mutableStateOf("Score: ")}


    fun checkNumber(number: Int):String {
        return when {
            number > 0 -> "Positive"
            number < 0 -> "Negative"
            else -> "Zero"
        }
    }

    Column(   modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally){
        Text("Number Guessing Game", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Guessing the number between $minRange and $maxRange", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Target: $targetValue", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = sliderValue.toFloat(),
            onValueChange = {newValue -> sliderValue = newValue.toInt()},
            valueRange = minRange.toFloat()..maxRange.toFloat()
            )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println(sliderValue)

                println(checkNumber(sliderValue))
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Tap")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text( text = message, fontSize = 20.sp, color = Color.Green)

    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        NumberGuessingGame()
    }
}


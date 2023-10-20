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
import kotlin.math.absoluteValue
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGame()
        }
    }
}



fun generateRandomValue(min: Int, max: Int): Int {
    require(min <= max) { "min must be less than or equal to max" }
    return Random.nextInt(min, max + 1)
}

@Composable
fun NumberGuessingGame() {
    var minRange by remember { mutableStateOf(0) }
    var maxRange by remember { mutableStateOf(100) }
    var sliderValue by remember { mutableStateOf((minRange + maxRange) / 2) }
    var targetValue by remember { mutableStateOf(generateRandomValue(minRange, maxRange)) }

    var score by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("") } // Initialize message as an empty string

    fun updateScore() {
        val difference = (sliderValue - targetValue).absoluteValue
        when {
            difference <= 3 -> {
                score += 5
                message = "Close! You get 5 points."
            }
            difference <= 8 -> {
                score += 1
                message = "Not bad, you get 1 point."
            }
            else -> {
                message = "You missed the target."
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bull's Eye Game", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(36.dp))

        Text("Move the slider as close as you can to: $targetValue", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(200.dp))

        Slider(
            value = sliderValue.toFloat(),
            onValueChange = { newValue -> sliderValue = newValue.toInt() },
            valueRange = minRange.toFloat()..maxRange.toFloat()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println(sliderValue)
                updateScore()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Hit Me!")
        }

        Spacer(modifier = Modifier.height(72.dp))

        Text(text = "You Score: $score", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(72.dp))

        Text(text = message, fontSize = 20.sp, color = Color.Black)
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        NumberGuessingGame()
    }
}


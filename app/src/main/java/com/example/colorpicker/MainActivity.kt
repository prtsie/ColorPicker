package com.example.colorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var red by rememberSaveable { mutableIntStateOf(0) }
    var green by rememberSaveable { mutableIntStateOf(0) }
    var blue by rememberSaveable { mutableIntStateOf(0) }
    var savedRed by rememberSaveable { mutableIntStateOf(0) }
    var savedGreen by rememberSaveable { mutableIntStateOf(0) }
    var savedBlue by rememberSaveable { mutableIntStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("#${savedRed.toString(16).padStart(2, '0')}${savedGreen.toString(16).padStart(2, '0')}${savedBlue.toString(16).padStart(2, '0')}")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f),
                    shape = RoundedCornerShape(30.dp),
                    color = Color(savedRed, savedGreen, savedBlue)
                ) { }
            }

            Button({ showDialog = true }) {
                Text("Выбрать цвет")
            }
        }
        if (showDialog) {
            BasicAlertDialog({}) {
                Surface(shape = RoundedCornerShape(10.dp)) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row {
                            Text("R")
                            Slider(value = red.toFloat(),
                                {x -> red = x.roundToInt()},
                                valueRange = 0f..255f,
                                modifier = Modifier.fillMaxWidth(0.8f))
                            TextField(red.toString(),
                                {x ->
                                    if (x.isEmpty()) {
                                        red = 0
                                    } else
                                    if (x.all { c -> c.isDigit() }) {
                                        val inputVal = x.toInt()
                                        red = if (inputVal > 255) 255 else inputVal
                                    }
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth())
                        }
                        Row(modifier = Modifier.padding(vertical = 20.dp)) {
                            Text("G")
                            Slider(value = green.toFloat(),
                                {x -> green = x.roundToInt()},
                                valueRange = 0f..255f,
                                modifier = Modifier.fillMaxWidth(0.8f))
                            TextField(green.toString(),
                                {x ->
                                    if (x.isEmpty()) {
                                        green = 0
                                    } else
                                    if (x.all { c -> c.isDigit() }) {
                                        val inputVal = x.toInt()
                                        green = if (inputVal > 255) 255 else inputVal
                                    }
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth())
                        }
                        Row {
                            Text("B")
                            Slider(value = blue.toFloat(),
                                {x -> blue = x.roundToInt()},
                                valueRange = 0f..255f,
                                modifier = Modifier.fillMaxWidth(0.8f))
                            TextField(blue.toString(),
                                {x ->
                                    if (x.isEmpty()) {
                                        blue = 0
                                    } else
                                    if (x.all { c -> c.isDigit() }) {
                                        val inputVal = x.toInt()
                                        blue = if (inputVal > 255) 255 else inputVal
                                    }
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth())
                        }
                        Button({
                            savedRed = red
                            savedGreen = green
                            savedBlue = blue
                            showDialog = false
                        },
                            colors = ButtonColors(Color(red, green, blue), Color(255 - red, 255 - green, 255 - blue), Color.DarkGray, Color.White),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)) {
                            Text("Применить")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting()
}
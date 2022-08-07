package com.haya.my_compose_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.haya.my_compose_sample.ui.theme.My_compose_sampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My_compose_sampleTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
//    Text(text = "Hello $name!"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    My_compose_sampleTheme {
        MyApp()
    }
}


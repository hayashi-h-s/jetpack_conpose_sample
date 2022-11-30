package com.haya.my_compose_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.haya.my_compose_sample.presentation.util.Navigation
import com.haya.my_compose_sample.ui.theme.My_compose_sampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My_compose_sampleTheme {
//                // topにSimpleBottomNavigationを設置することですべての画面で表示される
//                Scaffold(bottomBar = { SimpleBottomNavigation() }) {
//                    it
                    Navigation()
//                }
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
        Navigation()
    }
}


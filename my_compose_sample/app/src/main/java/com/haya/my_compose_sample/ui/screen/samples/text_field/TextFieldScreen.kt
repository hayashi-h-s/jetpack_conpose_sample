package com.haya.my_compose_sample.ui.screen.samples.text_field

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haya.my_compose_sample.R


@Composable
fun TextFieldScreen(toSamples: () -> Unit) {
//    var inputValue by remember { mutableStateOf("") }
    // 画面回転に対応、保持される。型はBundleに詰めることができる値
    var inputValue by rememberSaveable{ mutableStateOf("") }

    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }
        Text("textFieldサンプル")
        TextField(value = inputValue, onValueChange = { newValue ->
            inputValue = newValue
        })
    }
}
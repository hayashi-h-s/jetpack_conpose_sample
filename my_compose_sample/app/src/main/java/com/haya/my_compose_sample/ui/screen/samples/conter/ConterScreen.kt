package com.haya.my_compose_sample.ui.screen.samples.conter

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*


@Composable
fun CounterScreen(toSamples: () -> Unit) {
//    var counter  = 0 // これだとUIが更新されない
//    val counter = remember { mutableStateOf(0) } // 更新されるとvalueを使用しているComposable関数を再度呼び出し画面を更新している(再コンポーズ)
    var counter by remember { mutableStateOf(0) } // byでvalueがいらなくなる

    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }

        Text("Counterサンプル")

        Button(onClick = {
            Log.d("TAG", "Logs = $counter");
            counter++
        }) {
            Text(text = "$counter")
        }
    }
}
package com.haya.my_compose_sample.ui.screen.samples.disposable_effect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DisposableEffectScreen(
    toSamples: () -> Unit
) {
    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }

        Text("DisposableEffectサンプル")

        LazyColumn{
            item {

            }
        }
    }
}



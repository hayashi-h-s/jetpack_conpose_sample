package com.haya.my_compose_sample.ui.screen.samples.list_view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListViewScreen(toSamples: () -> Unit) {
//    var inputValue by remember { mutableStateOf("") }
    // 画面回転に対応、保持される。型はBundleに詰めることができる値
//    var inputValue by rememberSaveable{ mutableStateOf("") }

    val names = (1..100).map { "リストサンプル ${it}行目" }

    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }
        Text("textFieldサンプル")

        LazyColumn(modifier = Modifier.fillMaxWidth()) { // fillMaxWidthを指定しないと、テキストの範囲しかスクロールできない
            items(names) { name ->
                ListItem(name) {
                    Log.d("TAG","Logs = $name")
                }
            }
        }
    }
}

@Composable
fun ListItem(name: String, onClick: (name: String) -> Unit) {
    Box(modifier = Modifier
        .height(40.dp)
        .clickable {
            onClick(name)
        }) {
        Text(name)
    }
}
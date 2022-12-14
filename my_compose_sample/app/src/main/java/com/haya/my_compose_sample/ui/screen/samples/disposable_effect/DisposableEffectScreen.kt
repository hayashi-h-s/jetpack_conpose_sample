package com.haya.my_compose_sample.ui.screen.samples.disposable_effect

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleEventObserver

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
                DisposableEffectSampleToggle()
            }
        }
    }
}

@Composable
fun DisposableEffectSampleToggle() {
    val context = LocalContext.current // LocalContext.currentの説明 http://y-anz-m.blogspot.com/2021/04/jetpack-compose-composition-local.html
    var state by remember { mutableStateOf(true) }

    val lifecycleOwner = LocalLifecycleOwner.current
    if (state) {
        DisposableEffect(lifecycleOwner) {
            // Androidライフサイクルを購読するためObserverを生成して追加する
            Toast.makeText(context, "start effect func", Toast.LENGTH_SHORT).show()
            val observer = LifecycleEventObserver { _, event -> print("$event") }
            lifecycleOwner.lifecycle.addObserver(observer)

            // Androidライフサイクルを購読しているObserverの登録を削除するDisposableEffectResultを生成する
            val disposableEffectResult = onDispose {
                Toast.makeText(context, "start dispose func", Toast.LENGTH_SHORT).show()
                lifecycleOwner.lifecycle.removeObserver(observer)
            }

            // 生成したDisposableEffectResultを戻り値として、コンポジションから退場するとき(再コンポジション時も退場して入場となる)にDispose処理が呼び出されるようにする
            disposableEffectResult
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = state.toString(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(onClick = { state = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "TRUE")
            }
            Button(onClick = { state = false }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "FALSE")
            }
        }
    }
}
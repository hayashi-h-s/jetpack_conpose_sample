package com.haya.my_compose_sample.ui.screen.samples.launched_effect

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LaunchedEffectScreen(
    viewModel: LaunchedEffectViewModel,
    toSamples: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    val scrollState = rememberScrollState()

    // このログが何度も実行される
    Log.d("TAG", "Logs = // このログが何度も実行されるのでサーバーの付加が上がる")
    LaunchedEffect(Unit) { // ()の中に引数を入れると値が変更されるごとに、{}の中が実行される
        // LaunchedEffectの中ならsuspendをつけなくて良い
        viewModel.getUser()
        Log.d("TAG", "Logs = // LaunchedEffectの中なら1回のみの実行")
    }

    Column(Modifier.verticalScroll(scrollState)) { // Modifier.verticalScroll(scrollState)でスクロール可能に
            Button(onClick = {
                toSamples()
            }) {
                Text(text = "Samples画面へ")
            }
            Text("LaunchedEffectScreenサンプル")

            Text(
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(all = 8.dp),
                text = "3秒後に秒後に表示されるよ！name = ${user?.name}"
            )

            LaunchedEffectSampleToggle()

            LaunchedEffectSampleCounter()

    }
}


@Composable
fun LaunchedEffectSampleToggle() {
    val context = LocalContext.current
    var state by remember { mutableStateOf(false) }

    if (state) {
        LaunchedEffect(Unit) { // Unitだと1度だけ実行 // 再コンポジションでメソッド内を再度実行
            Toast.makeText(context, "start $state", Toast.LENGTH_SHORT).show()
            delay(2000)
            Toast.makeText(context, "end $state", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.height(500.dp)) {
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

@Composable
fun LaunchedEffectSampleCounter() {
    val context = LocalContext.current
    var count by remember { mutableStateOf(0) }

    LaunchedEffect(count) { // countが変更されるたびに実行
        Toast.makeText(context, "start $count", Toast.LENGTH_SHORT).show()
        delay(2000)
        Toast.makeText(context, "end $count", Toast.LENGTH_SHORT).show()
    }

    Box(modifier = Modifier.height(400.dp)) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(onClick = { count += 1 }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "INCREMENT")
            }
            Button(onClick = { count -= 1 }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "DECREMENT")
            }
            Button(onClick = { count = 0 }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "ZERO CLEAR")
            }
        }
    }
}
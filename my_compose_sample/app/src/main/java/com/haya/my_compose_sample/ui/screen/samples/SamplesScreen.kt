package com.haya.my_compose_sample.ui.screen.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SamplesScreen(
    toTop: () -> Unit,
    toCounter: () -> Unit,
    toTextField: () -> Unit,
    toLazyColumn: () -> Unit,
    toLaunchedEffect: () -> Unit,
    toMessageList: () -> Unit,
    toDisposableEffect: () -> Unit,
    toCustomView: () -> Unit,
    toConstraintLayout: () -> Unit,
    toThemeSampleScreen: () -> Unit,
    toRoomSampleScreen: () -> Unit,
    toBottomNavigationSampleScreen: () -> Unit,
    toPreviewParameterScreen: () -> Unit,
    toTabInViewPagerScreen: () -> Unit,
    toExoPlayerSampleScreen: () -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Button(onClick = {
            toTop()
        }) {
            Text(text = "Topへ")
        }
        Button(onClick = {
            toCounter()
        }) {
            Text(text = "Counter アプリへ")
        }
        Button(onClick = {
            toTextField()
        }) {
            Text(text = "テキスト入力サンプルへ")
        }
        Button(onClick = {
            toLazyColumn()
        }) {
            Text(text = "リスト表示サンプル")
        }
        Button(onClick = {
            toLaunchedEffect()
        }) {
            Text(text = "LaunchedEffect表示サンプル")
        }
        Button(onClick = {
            toMessageList()
        }) {
            Text(text = "MessageList表示サンプル")
        }
        Button(onClick = {
            toDisposableEffect()
        }) {
            Text(text = "DisposableEffect表示サンプル")
        }
        Button(onClick = {
            toCustomView()
        }) {
            Text(text = "CustomView表示サンプル")
        }
        Button(onClick = {
            toConstraintLayout()
        }) {
            Text(text = "ConstraintLayout表示サンプル")
        }
        Button(onClick = {
            toThemeSampleScreen()
        }) {
            Text(text = "Themeサンプル")
        }
        Button(onClick = {
            toRoomSampleScreen()
        }) {
            Text(text = "Roomサンプル")
        }
        Button(onClick = {
            toBottomNavigationSampleScreen()
        }) {
            Text(text = "BottomNavigationSampleサンプル")
        }
        Button(onClick = {
            toPreviewParameterScreen()
        }) {
            Text(text = "PreviewParameterScreenサンプル")
        }
        Button(onClick = {
            toTabInViewPagerScreen()
        }) {
            Text(text = "TabInViewPagerScreen")
        }
        Button(onClick = {
            toExoPlayerSampleScreen()
        }) {
            Text(text = "ExoPlayerSampleScreen")
        }
    }
}
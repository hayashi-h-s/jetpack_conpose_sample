package com.haya.my_compose_sample.ui.screen.samples.preview_parameter

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.haya.my_compose_sample.ui.screen.samples.preview_parameter.models.ExampleUiState
import com.haya.my_compose_sample.ui.screen.samples.preview_parameter.models.Memo
import com.haya.my_compose_sample.ui.theme.My_compose_sampleTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PreviewParameterScreen(uiState: ExampleUiState, toSamples: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "ExampleScreen") }
            )
        }
    ) {
        if (uiState.memos.isEmpty()) {
            Text(
                text = "まだメモがありません",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                textAlign = TextAlign.Center,
            )
        } else {
            MemoList(memos = uiState.memos)
        }

        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        if (uiState.isError) {
            Dialog(onDismissRequest = {}) {
                Column(
                    modifier = Modifier
                        .size(300.dp, 200.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "エラーです", color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun MemoList(memos: List<Memo>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(memos) { memo ->
            Text(text = memo.title)
        }
    }
}

@Preview
@Composable
fun ExamplePreview(
    @PreviewParameter(FakeExampleUiStateProvider::class) uiState: ExampleUiState // ここ！
) {
    My_compose_sampleTheme {
        PreviewParameterScreen(uiState = uiState) {}
    }
}

class FakeExampleUiStateProvider : PreviewParameterProvider<ExampleUiState> {
    override val values = sequenceOf(
        ExampleUiState(),
        ExampleUiState(
            memos = List(50) { Memo(title = "タイトル No.$it") }
        ),
        ExampleUiState(
            memos = List(50) { Memo(title = "タイトル No.$it") },
            isLoading = true
        ),
        ExampleUiState(
            memos = List(50) { Memo(title = "タイトル No.$it") },
            isError = true
        ),
        ExampleUiState(
            memos = List(50) {
                Memo(title = "長いタイトル長いタイトル長いタイトル長いタイトル長いタイトル長いタイトル No.$it")
            }
        )
    )
}
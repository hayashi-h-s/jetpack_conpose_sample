package com.haya.my_compose_sample.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    toTop: () -> Unit
) {
    val isFavorite by viewModel.isFavorite.collectAsState()
    Column {
        Text(text = "詳細画面だよ")
        Button(onClick = {
            toTop()
        }) {
            Text(text = "Top画面へ")
        }
        Button(onClick = {
            viewModel.setFavorite(!isFavorite)
        }) {
            if (isFavorite) {
                Text("お気に入りに済み")
            } else {
                Text("お気に入りに追加")
            }
        }
    }
}
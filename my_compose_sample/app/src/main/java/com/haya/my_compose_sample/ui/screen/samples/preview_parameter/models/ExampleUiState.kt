package com.haya.my_compose_sample.ui.screen.samples.preview_parameter.models

data class ExampleUiState(
    val memos: List<Memo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

data class Memo(
    val title: String
)
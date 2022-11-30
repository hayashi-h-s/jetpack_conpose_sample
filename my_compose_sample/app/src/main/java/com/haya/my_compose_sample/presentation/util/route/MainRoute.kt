package com.haya.my_compose_sample.presentation.util.route

// MainScreenからの画面遷移先一覧
sealed class MainRoute(val route: String) {
    object MainScreen: MainRoute("main_screen")
    object SamplesScreen: MainRoute("samples_screen")
}
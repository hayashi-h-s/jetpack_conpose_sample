package com.haya.my_compose_sample

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haya.my_compose_sample.ui.screen.detail.DetailScreen
import com.haya.my_compose_sample.ui.screen.detail.DetailViewModel
import com.haya.my_compose_sample.ui.screen.samples.SamplesScreen
import com.haya.my_compose_sample.ui.screen.samples.conter.CounterScreen
import com.haya.my_compose_sample.ui.screen.samples.disposable_effect.DisposableEffectScreen
import com.haya.my_compose_sample.ui.screen.samples.launched_effect.LaunchedEffectScreen
import com.haya.my_compose_sample.ui.screen.samples.launched_effect.LaunchedEffectViewModel
import com.haya.my_compose_sample.ui.screen.samples.list_view.ListViewScreen
import com.haya.my_compose_sample.ui.screen.samples.message_list.MessageListScreen
import com.haya.my_compose_sample.ui.screen.samples.text_field.TextFieldScreen
import com.haya.my_compose_sample.ui.screen.top.TopScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "top") {
        composable("top") {
            TopScreen(
                { navController.navigate("detail") },
                { navController.navigate("samples") }
            )
        }
        composable("detail") {
            val vm: DetailViewModel = viewModel()
            DetailScreen(vm) {
                navController.navigate("top")
            }
        }
        //samples start region
        composable("samples") {
            SamplesScreen(
                { navController.navigate("top") },
                { navController.navigate("counter") },
                { navController.navigate("textField") },
                { navController.navigate("lazyColumn") },
                { navController.navigate("launchedEffect") },
                { navController.navigate("messageList") },
                { navController.navigate("disposableEffect") },
            )
        }
        composable("counter") {
            CounterScreen { navController.navigate("samples") }
        }
        composable("textField") {
            TextFieldScreen { navController.navigate("samples") }
        }
        composable("lazyColumn") {
            ListViewScreen { navController.navigate("samples") }
        }
        composable("launchedEffect") {
            val vm: LaunchedEffectViewModel = viewModel()
            LaunchedEffectScreen(vm) {
                navController.navigate("samples")
            }
        }
        composable("messageList") {
            MessageListScreen {
                navController.navigate("samples")
            }
        }
        composable("disposableEffect") {
            DisposableEffectScreen {
                navController.navigate("samples")
            }
        }
        //end region
    }
}
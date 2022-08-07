package com.haya.my_compose_sample.ui.screen.samples.launched_effect

import androidx.lifecycle.ViewModel
import com.haya.my_compose_sample.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class LaunchedEffectViewModel : ViewModel() {
    val user: MutableStateFlow<User?> = MutableStateFlow(null)

    suspend fun getUser() {
        delay(3000)
        user.value = User("LaunchedEffectViewModel")
    }
}
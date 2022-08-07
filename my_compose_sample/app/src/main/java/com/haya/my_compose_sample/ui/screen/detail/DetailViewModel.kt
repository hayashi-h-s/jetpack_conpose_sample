package com.haya.my_compose_sample.ui.screen.detail


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel : ViewModel() {
    val isFavorite = MutableStateFlow(false)

    fun setFavorite(isUpdateFavorite: Boolean) {
        isFavorite.value = isUpdateFavorite
    }
}
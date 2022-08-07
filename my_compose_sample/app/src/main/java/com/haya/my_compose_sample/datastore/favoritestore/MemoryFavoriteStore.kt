package com.haya.my_compose_sample.datastore.favoritestore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MemoryFavoriteStore:FavoriteStore {
    val data = MutableStateFlow(false)
    override fun sava() {
        data.value = true
    }

    override fun load(): Flow<Boolean> {
        return data
    }
}
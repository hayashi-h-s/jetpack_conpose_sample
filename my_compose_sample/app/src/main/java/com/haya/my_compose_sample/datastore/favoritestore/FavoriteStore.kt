package com.haya.my_compose_sample.datastore.favoritestore

import kotlinx.coroutines.flow.Flow

interface FavoriteStore {
    fun sava()
    fun load(): Flow<Boolean>
}

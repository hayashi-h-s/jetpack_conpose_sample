package com.haya.my_compose_sample.domain.model

import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id:Int? =  null,
) {
    companion object {
        val noteColors = listOf(Red)
    }
}

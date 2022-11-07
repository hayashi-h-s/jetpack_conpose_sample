package com.haya.my_compose_sample.ui.screen.samples.room_sample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todos")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String = "",
    val time: Date = Date(),
    val isDone: Boolean = false
)
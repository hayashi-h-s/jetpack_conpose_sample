package com.haya.my_compose_sample.models.sample_models

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
package com.haya.my_compose_sample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haya.my_compose_sample.ui.screen.samples.room_sample.dao.DateTimeConverter
import com.haya.my_compose_sample.ui.screen.samples.room_sample.dao.ToDoDao
import com.haya.my_compose_sample.ui.screen.samples.room_sample.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}
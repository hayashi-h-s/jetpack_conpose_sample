package com.haya.my_compose_sample.dao

import androidx.room.*
import com.haya.my_compose_sample.models.sample_models.Task
import java.util.*

@Dao
interface ToDoDao {
    @Query("select * from todos order by time asc")
    fun getAll(): MutableList<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun post(todo: Task)

    @Delete
    fun delete(todo: Task)
}

class DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
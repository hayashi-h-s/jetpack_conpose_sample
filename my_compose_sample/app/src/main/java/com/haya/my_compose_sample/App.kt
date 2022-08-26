package com.haya.my_compose_sample

import android.app.Application
import androidx.room.Room
import com.haya.my_compose_sample.database.AppDatabase

class App : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todos"
        ).build()
    }
}
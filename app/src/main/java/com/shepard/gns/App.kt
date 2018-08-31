package com.shepard.gns

import android.app.Application
import androidx.room.Room
import com.shepard.gns.database.AppDatabase

/**
 * @author shepard
 * @since 29.12.2017
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "gns").build()
    }
}
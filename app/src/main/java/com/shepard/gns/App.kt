package com.shepard.gns

import android.app.Application
import android.arch.persistence.room.Room
import com.github.salomonbrys.kodein.*
import com.shepard.gns.database.AppDatabase
import com.shepard.gns.database.dao.UserRepository

/**
 * @author shepard
 * @since 29.12.2017
 */
class App : Application(), KodeinAware {
    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "gns").build()
    }

    // TODO: divide into separate provider-functions (in separate file)
    override val kodein by Kodein.lazy {
        bind<UserRepository>() with provider { db.userRepository() }
    }
}
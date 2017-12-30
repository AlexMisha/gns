package com.shepard.gns.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author shepard
 * @since 28.12.2017
 */
@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long = 0,

        @ColumnInfo(name = "name")
        var name: String
)

package com.shepard.gns.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author shepard
 * @since 28.12.2017
 */
@Entity(tableName = "git_account")
data class GitAccount(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long = 0,

        @ColumnInfo(name = "name")
        var name: String
)

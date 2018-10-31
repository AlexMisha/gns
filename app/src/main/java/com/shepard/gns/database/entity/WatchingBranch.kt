package com.shepard.gns.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author shepard
 * @since 04.01.2018
 */
@Entity(tableName = "watching_branch")
data class WatchingBranch(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "checked")
    var checked: Boolean = true,

    @ColumnInfo(name = "latest_commit_date")
    var latestCommitDate: Long = 0
)
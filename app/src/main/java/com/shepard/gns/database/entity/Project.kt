package com.shepard.gns.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author shepard
 * @since 04.01.2018
 */
@Entity(tableName = "repository")
data class Project(@ColumnInfo(name = "id")
                   @PrimaryKey(autoGenerate = true)
                   var id: Long = 0,

                   @ColumnInfo(name = "name")
                   var name: String = "",

                   @ColumnInfo(name = "avatar_url")
                   var avatarUrl: String = "",

                   @ColumnInfo(name = "is_private")
                   var isPrivate: Boolean = false,

                   @ColumnInfo(name = "license")
                   var license: String = "",

                   @ColumnInfo(name = "forks")
                   var forks: Long = 0,

                   @ColumnInfo(name = "owner")
                   var owner: String = "")
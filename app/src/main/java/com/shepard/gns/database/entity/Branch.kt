package com.shepard.gns.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * @author shepard
 * @since 04.01.2018
 */
@Entity(tableName = "branch", foreignKeys = [(ForeignKey(entity = Project::class,
        parentColumns = [("id")],
        childColumns = [("project_id")]))])
data class Branch(@ColumnInfo(name = "id")
                  @PrimaryKey(autoGenerate = true)
                  var id: Long = 0,

                  @ColumnInfo(name = "name")
                  var name: String = "",

                  @ColumnInfo(name = "project_id")
                  var projectId: Long)
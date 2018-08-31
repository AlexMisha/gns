package com.shepard.gns.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
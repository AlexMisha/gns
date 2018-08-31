package com.shepard.gns.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

/**
 * @author shepard
 * @since 04.01.2018
 */
@Entity(tableName = "commit",
        foreignKeys = [
            (ForeignKey(entity = Project::class,
                    parentColumns = [("id")],
                    childColumns = [("project_id")])),
            (ForeignKey(entity = Branch::class,
                    parentColumns = [("id")],
                    childColumns = [("branch_id")]))])
data class Commit(@ColumnInfo(name = "id")
                  @PrimaryKey(autoGenerate = true)
                  var id: Long = 0,

                  @ColumnInfo(name = "name")
                  var sha: String = "",

                  @ColumnInfo(name = "message")
                  var message: String = "",

                  @ColumnInfo(name = "author")
                  var author: String = "",

                  @ColumnInfo(name = "author_avatar_url")
                  var authorAvatarUrl: String = "",

                  @ColumnInfo(name = "project_id")
                  var projectId: Long,

                  @ColumnInfo(name = "branch_id")
                  var branchId: Long)
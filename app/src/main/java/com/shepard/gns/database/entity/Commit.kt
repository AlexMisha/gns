package com.shepard.gns.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

/**
 * @author shepard
 * @since 04.01.2018
 */
@Entity(tableName = "commit", foreignKeys = [(ForeignKey(entity = WatchingBranch::class,
        parentColumns = [("id")],
        childColumns = [("branch_id")]))])
data class Commit(@ColumnInfo(name = "id")
                  @PrimaryKey(autoGenerate = true)
                  var id: Long = 0,

                  @ColumnInfo(name = "message")
                  var message: String = "",

                  @ColumnInfo(name = "committer_name")
                  var committerName: String = "",

                  @ColumnInfo(name = "committer_photo_url")
                  var committerPhotoUrl: String = "",

                  @ColumnInfo(name = "name")
                  var sha: String = "",

                  @ColumnInfo(name = "branch_id")
                  var branchId: Long = 1,

                  @ColumnInfo(name = "date")
                  var date: Long = 0)
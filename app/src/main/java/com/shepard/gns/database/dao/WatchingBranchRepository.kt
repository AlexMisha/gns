package com.shepard.gns.database.dao

import androidx.lifecycle.LiveData
import com.shepard.gns.database.entity.WatchingBranch

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * @author shepard
 * @since 04.01.2018
 */
@Dao
interface WatchingBranchRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: WatchingBranch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<WatchingBranch>)

    @Query("select * from watching_branch")
    fun findAll(): LiveData<List<WatchingBranch>>

    @Query("select * from watching_branch where id = :id limit 1")
    fun findById(id: Long): LiveData<WatchingBranch>

    @Update
    fun update(entity: WatchingBranch)

    @Delete
    fun delete(entity: WatchingBranch)

    @Delete
    fun deleteAll(entities: List<WatchingBranch>)
}

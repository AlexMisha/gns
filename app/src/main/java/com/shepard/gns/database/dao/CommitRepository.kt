package com.shepard.gns.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shepard.gns.database.entity.Commit

/**
 * @author shepard
 * @since 04.01.2018
 */
@Dao
interface CommitRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: Commit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<Commit>)

    @Query("select * from `commit`")
    fun findAll(): LiveData<List<Commit>>

    @Query("select * from `commit` where id = :id limit 1")
    fun findById(id: Long): LiveData<Commit>

    @Query("select * from `commit` where branch_id = :branchId")
    fun findByBranchId(branchId: Long): LiveData<List<Commit>>

    @Update
    fun update(entity: Commit)

    @Delete
    fun delete(entity: Commit)

    @Delete
    fun deleteAll(entities: List<Commit>)

    @Query("select * from `commit` where branch_id = :branchId")
    fun syncFindByBranchId(branchId: Long): List<Commit>
}

package com.shepard.gns.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shepard.gns.database.entity.GitAccount

/**
 * @author shepard
 * @since 24.12.2017
 */
@Dao
interface GitAccountRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: GitAccount)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<GitAccount>)

    @Query("select * from git_account")
    fun findAll(): LiveData<List<GitAccount>>

    @Query("select * from git_account where id = :id limit 1")
    fun findById(id: Long): LiveData<GitAccount>

    @Update
    fun update(entity: GitAccount)

    @Delete
    fun delete(entity: GitAccount)

    @Delete
    fun deleteAll(entities: List<GitAccount>)

    @Query("select * from git_account")
    fun synсFindAll(): List<GitAccount>
}

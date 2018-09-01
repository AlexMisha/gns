package com.shepard.gns.database.dao

import androidx.lifecycle.LiveData
import com.shepard.gns.database.entity.Token
import com.shepard.gns.database.entity.TokenType

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * @author shepard
 * @since 24.12.2017
 */
@Dao
interface TokenRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: Token)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<Token>)

    @Query("select * from token")
    fun findAll(): LiveData<List<Token>>

    @Query("select * from token where id = :id limit 1")
    fun findById(id: Long): LiveData<Token>

    @Query("select * from token where account_id = :accountId limit 1")
    fun findByAccountId(accountId: Long): LiveData<Token>

    @Update
    fun update(entity: Token)

    @Delete
    fun delete(entity: Token)

    @Delete
    fun deleteAll(entities: List<Token>)
}

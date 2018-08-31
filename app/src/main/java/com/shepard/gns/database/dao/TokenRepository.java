package com.shepard.gns.database.dao;

import com.shepard.gns.database.entity.Token;
import com.shepard.gns.database.entity.TokenType;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author shepard
 * @since 24.12.2017
 */
@Dao
public interface TokenRepository extends CRUDRepository<Token, Long> {
    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Token entity);

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Token> entities);

    @Override
    @Query("select * from token")
    Flowable<List<Token>> findAll();

    @Override
    @Query("select * from token where id = :id limit 1")
    Flowable<Token> findById(Long id);

    @Query("select * from token where type = :type")
    Flowable<List<Token>> findByTokenType(TokenType type);

    @Query("select * from token where type = :type and user_id = :userId limit 1")
    Flowable<Token> findByTokenTypeAndUserId(TokenType type, Long userId);

    @Override
    @Update
    void updateEntity(Token entity);

    @Override
    @Delete
    void delete(Token entity);

    @Override
    @Delete
    void deleteAll(List<Token> entities);
}

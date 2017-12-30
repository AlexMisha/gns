package com.shepard.gns.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shepard.gns.database.entity.Token;
import com.shepard.gns.database.entity.TokenType;

import java.util.List;

import io.reactivex.Flowable;

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

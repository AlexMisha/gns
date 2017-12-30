package com.shepard.gns.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shepard.gns.database.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author shepard
 * @since 24.12.2017
 */
@Dao
public interface UserRepository {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(User entity);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void saveAll(List<User> entities);

  @Query("select * from user")
  Flowable<List<User>> findAll();

  @Query("select * from user where id = :id limit 1")
  Flowable<User> findById(long id);

  @Query("select * from user where name = :name limit 1")
  Flowable<User> findByName(String name);

  @Update
  void updateEntity(User entity);

  @Delete
  void delete(User entity);

  @Delete
  void deleteAll(List<User> entities);

}

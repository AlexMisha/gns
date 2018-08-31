package com.shepard.gns.database.dao;

import com.shepard.gns.database.entity.User;

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
public interface UserRepository extends CRUDRepository<User, Long> {

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(User entity);

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<User> entities);

    @Override
    @Query("select * from user")
    Flowable<List<User>> findAll();

    @Override
    @Query("select * from user where id = :id limit 1")
    Flowable<User> findById(Long id);

    @Query("select * from user where name = :name limit 1")
    Flowable<User> findByName(String name);

    @Override
    @Update
    void updateEntity(User entity);

    @Override
    @Delete
    void delete(User entity);

    @Override
    @Delete
    void deleteAll(List<User> entities);

}

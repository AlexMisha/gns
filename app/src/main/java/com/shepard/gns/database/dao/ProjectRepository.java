package com.shepard.gns.database.dao;

import com.shepard.gns.database.entity.Project;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author shepard
 * @since 04.01.2018
 */
@Dao
public interface ProjectRepository extends CRUDRepository<Project, Long> {

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Project entity);

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Project> entities);

    @Override
    @Query("select * from project")
    Flowable<List<Project>> findAll();

    @Override
    @Query("select * from project where id = :id limit 1")
    Flowable<Project> findById(Long id);

    @Override
    @Update
    void updateEntity(Project entity);

    @Override
    @Delete
    void delete(Project entity);

    @Override
    @Delete
    void deleteAll(List<Project> entities);
}

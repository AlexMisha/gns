package com.shepard.gns.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shepard.gns.database.entity.Project;

import java.util.List;

import io.reactivex.Flowable;

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

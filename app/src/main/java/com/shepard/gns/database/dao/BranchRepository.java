package com.shepard.gns.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shepard.gns.database.entity.Branch;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author shepard
 * @since 04.01.2018
 */
@Dao
public interface BranchRepository extends CRUDRepository<Branch, Long> {

  @Override
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(Branch entity);

  @Override
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void saveAll(List<Branch> entities);

  @Override
  @Query("select * from branch")
  Flowable<List<Branch>> findAll();

  @Override
  @Query("select * from branch where id = :id limit 1")
  Flowable<Branch> findById(Long id);

  @Query("select * from branch where project_id = :projectId")
  Flowable<List<Branch>> findByProjectId(Long projectId);

  @Override
  @Update
  void updateEntity(Branch entity);

  @Override
  @Delete
  void delete(Branch entity);

  @Override
  @Delete
  void deleteAll(List<Branch> entities);
}

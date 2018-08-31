package com.shepard.gns.database.dao;

import com.shepard.gns.database.entity.Branch;

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

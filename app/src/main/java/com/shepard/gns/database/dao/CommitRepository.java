package com.shepard.gns.database.dao;

import com.shepard.gns.database.entity.Commit;

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
public interface CommitRepository extends CRUDRepository<Commit, Long> {

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Commit entity);

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Commit> entities);

    @Override
    @Query("select * from `commit`")
    Flowable<List<Commit>> findAll();

    @Override
    @Query("select * from `commit` where id = :id limit 1")
    Flowable<Commit> findById(Long id);

    @Query("select * from `commit` where project_id = :projectId")
    Flowable<List<Commit>> findByProjectId(Long projectId);

    @Query("select * from `commit`where branch_id = :branchId")
    Flowable<List<Commit>> findByBranchId(Long branchId);

    @Override
    @Update
    void updateEntity(Commit entity);

    @Override
    @Delete
    void delete(Commit entity);

    @Override
    @Delete
    void deleteAll(List<Commit> entities);
}

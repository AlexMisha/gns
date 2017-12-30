package com.shepard.gns.database.dao;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author shepard
 * @since 30.12.2017
 */
public interface CRUDRepository<T, ID> {
  void save(T entity);

  void saveAll(List<T> entities);

  Flowable<List<T>> findAll();

  Flowable<T> findById(ID id);

  void updateEntity(T entity);

  void delete(T entity);

  void deleteAll(List<T> entities);
}

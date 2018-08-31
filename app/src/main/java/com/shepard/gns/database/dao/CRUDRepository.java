package com.shepard.gns.database.dao;

import java.util.List;

/**
 * @param <T>  Type of entity
 * @param <ID> Type of entity's id
 * @author shepard
 * @since 30.12.2017
 * <p>
 * Interface for generic CRUD operations on a repository for a specific type.
 */
public interface CRUDRepository<T, ID> {

    /**
     * Saves a given entity.
     *
     * @param entity must not be null.
     */
    void save(T entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be null.
     */
    void saveAll(List<T> entities);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Flowable<List<T>> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be null.
     * @return the entity with the given id or null if none found
     */
    Flowable<T> findById(ID id);

    /**
     * Updates given entity.
     *
     * @param entity must not be null
     */
    void updateEntity(T entity);

    /**
     * Deletes a given entity.
     *
     * @param entity must not be null.
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities must not be null.
     */
    void deleteAll(List<T> entities);
}

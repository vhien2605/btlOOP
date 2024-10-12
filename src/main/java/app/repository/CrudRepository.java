package app.repository;

import java.util.Optional;

/**
 * Interface CrudRepository used for all data access logic in application
 * 
 * @param <T>  Object type reference to entity in database
 * @param <ID> Other data type used in implement methods
 */
public interface CrudRepository<T, ID> {
    /**
     * Find all the document in Table entity
     *
     *
     * @return collection of objects mapping from documents in database
     */
    Iterable<T> findAll();

    /**
     * Find document which have ID input in database
     *
     *
     * @param id Document's id want to query from database
     * @return Optional wrapper of object . Optional object will return the true
     *         object or Optional.empty()
     *         avoid from access null pointer error
     */
    Optional<T> findById(ID id);

    /**
     * Delete the document which have ID input in database
     *
     *
     * @param id Document's ID want to delete from T table in database
     */
    void deleteById(ID id);

    /**
     * Save the object as a document to the database
     *
     *
     * @param entity Object want to save to database (mapping to T type table)
     */
    void save(T entity);

    /**
     * Count document in table of T object in database
     *
     * @return num of document of T class mapping to entity in database
     */
    int count();
}

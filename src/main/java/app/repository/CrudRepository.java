package app.repository;

import java.util.Optional;

/**
 * Interface {@link CrudRepository} used for all data access logic in application
 *
 * @param <T>  Object type reference to entity in database
 * @param <ID> Other data type used in implement methods
 */
public interface CrudRepository<T, ID> {
    /**
     * Find all the document in Table entity.
     *
     * @return Collection of {@link T} mapping from documents in database
     */
    Iterable<T> findAll();

    /**
     * Find document which have ID input in database.
     *
     * @param id Document's id want to query from database
     * @return {@code Optional} wrapper of object . Optional object will return the true
     * {@code Optional<T>} or {@code Optional.empty()}
     * to avoid from access null pointer error
     */
    Optional<T> findById(ID id);

    /**
     * Delete the {@link T} object which have ID input in database.
     *
     * @param id Object's ID you want to delete from {@link T} entity.
     */
    boolean deleteById(ID id);

    /**
     * Save the {@link T} object to the database.
     *
     * @param entity Object want to save to database (mapping to {@link T} type table)
     * @return {@code boolean} when updated or not
     */
    void save(T entity);

    /**
     * Count document in table of {@link T} object in database.
     *
     * @return num of document of {@link T} class mapping to entity in database
     */
    int count();
}

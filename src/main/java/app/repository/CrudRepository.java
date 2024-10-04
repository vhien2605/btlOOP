package app.repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    Iterable<T> findAll();

    Optional<T> findById(ID id);

    void deleteById(ID id);

    <S extends T> S save(S entity);

    long count();
}

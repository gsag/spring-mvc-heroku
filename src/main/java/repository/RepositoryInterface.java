package repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by guilherme on 25/11/15.
 */
public interface RepositoryInterface<T, ID extends Serializable> {
    void save(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(T entity) throws Exception;

    Optional<T> findOne(ID primaryKey) throws Exception;

    List<T> findAll() throws Exception;

    Long count() throws Exception;

    boolean exists(ID primaryKey) throws Exception;
}
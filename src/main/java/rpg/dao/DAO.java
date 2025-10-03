package rpg.dao;

import java.util.List;

/**
 * Interface générique DAO
 */
public interface DAO<T> {
    void save(T item) throws Exception;
    T findByName(String name) throws Exception;
    List<T> findAll() throws Exception;
}

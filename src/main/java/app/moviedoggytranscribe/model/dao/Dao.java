package app.moviedoggytranscribe.model.dao;

import java.util.List;

public interface Dao<T> {

    Integer add(T entity);
    List<T> getAll();
    void delete(Integer id);
    void update(T entity);

}

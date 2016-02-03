package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {

    Integer add(T entity);
    List<T> getAll();
    void delete(Integer id);
    void update(T entity);

}

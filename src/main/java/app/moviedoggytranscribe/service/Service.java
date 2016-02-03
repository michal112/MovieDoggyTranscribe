package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.model.dao.Dao;
import app.moviedoggytranscribe.model.entity.Entity;

import java.util.List;
import java.util.Observer;

public interface Service<T extends Entity, E extends NoSuchEntityException> extends Observer, ServiceObservable {

    void clearEntities();
    List<T> getAll();
    T get(Integer id) throws E;
    Integer add(T entity);
    void delete(Integer id) throws E;
    void update(T entity) throws E;
    Dao<T> getDao();

}

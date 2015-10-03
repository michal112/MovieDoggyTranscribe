package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.entity.Entity;

import java.util.List;

public interface Service<T extends Entity> {

    void clearData();
    List<T> getAll();
    T get(Integer id) throws NoSuchEntityException;
    Integer add(T entity);
    void delete(Integer id) throws NoSuchMovieException;
    void update(T entity) throws NoSuchMovieException;

}

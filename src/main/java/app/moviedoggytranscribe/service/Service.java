package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.exception.NoSuchMovieException;

import java.util.List;

public interface Service<T> {

    List<T> getAll();
    T get(Integer id) throws NoSuchEntityException;
    Integer add(T entity);
    void delete(Integer id) throws NoSuchMovieException;
    void update(T entity) throws NoSuchMovieException;

}

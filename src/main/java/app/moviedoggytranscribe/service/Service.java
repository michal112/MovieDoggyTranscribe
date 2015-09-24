package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import java.util.List;

public interface Service<T> {

    List<T> getAll();
    T get(Integer id) throws NoSuchEntityException;
    Integer add(T entity);

}

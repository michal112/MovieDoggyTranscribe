package app.moviedoggytranscribe.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();
    T get(Integer id);

}

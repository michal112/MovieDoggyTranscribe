package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.model.dao.Dao;
import app.moviedoggytranscribe.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieService implements Service<Movie> {

    @Autowired
    private Dao<Movie> movieDao;
    private List<Movie> movies = Collections.emptyList();

    @Override
    public List<Movie> getAll() {
        initMovies();
        return movies;
    }

    @Override
    public Movie get(Integer id) {
        initMovies();
        if (movies.isEmpty()) {
            return null;
        }
        return movies.stream().filter(m -> m.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Integer add(Movie entity) {
        movieDao.add()
    }

    private void initMovies() {
        if (movies.isEmpty()) {
            movies = movieDao.getAll();
        }
    }

}

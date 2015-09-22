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
        //TODO refactor
        List<Movie> movieList = movies.stream().filter(m -> m.getId() == id).collect(Collectors.toList());
        if (movieList.isEmpty()) {
            return null; //TODO exception
        }
        return movieList.get(0);
    }

    private void initMovies() {
        if (movies.isEmpty()) {
            movies = movieDao.getAll();
        }
    }

}

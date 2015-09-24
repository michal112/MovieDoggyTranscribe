package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.dao.Dao;
import app.moviedoggytranscribe.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class MovieService implements Service<Movie> {

    @Autowired
    private Dao<Movie> movieDao;
    private List<Movie> movies;

    public MovieService() {
        this.movies = new ArrayList<>();
    }

    @Override
    public List<Movie> getAll() {
        initMovies();
        return movies;
    }

    @Override
    public Movie get(Integer id) throws NoSuchMovieException {
        initMovies();
        if (movies.stream().anyMatch(movie -> movie.getId().equals(id))) {
            return movies.stream().filter(movie -> movie.getId().equals(id)).collect(Collectors.toList()).get(0);
        } else {
            throw new NoSuchMovieException(id);
        }
    }

    @Override
    public Integer add(Movie entity) {
        return movieDao.add(entity);
    }

    private void initMovies() {
        if (movies.isEmpty()) {
            movies = movieDao.getAll();
        }
    }

}

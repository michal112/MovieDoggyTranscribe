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

    public void clearMovies() {
        this.movies.clear();
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
        Integer movieId = movieDao.add(entity);
        movies.add(entity);
        return movieId;
    }

    @Override
    public void delete(Integer id) throws NoSuchMovieException {
        movies.remove(get(id));
        movieDao.delete(id);
    }

    @Override
    public void update(Movie entity) throws NoSuchMovieException {
        if (movies.stream().anyMatch(movie -> movie.getId() == entity.getId())) {
            Movie movie = movies.stream().filter(m -> m.getId() == entity.getId())
                    .collect(Collectors.toList()).get(0);
            movies.remove(movie);
            movies.add(entity);
            movieDao.update(entity);
        } else {
            throw new NoSuchMovieException(entity.getId());
        }

    }

    private void initMovies() {
        if (movies.isEmpty()) {
            movies = movieDao.getAll();
        }
    }

}

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
    public void clearData() {
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
    public Integer add(Movie movie) {
        Integer movieId = movieDao.add(movie);
        movies.add(movie);
        return movieId;
    }

    @Override
    public void delete(Integer id) throws NoSuchMovieException {
        movies.remove(get(id));
        movieDao.delete(id);
    }

    @Override
    public void update(Movie movie) throws NoSuchMovieException {
        if (movies.stream().anyMatch(m -> m.getId() == movie.getId())) {
            Movie mov = movies.stream().filter(m -> m.getId() == movie.getId())
                    .collect(Collectors.toList()).get(0);
            movies.remove(mov);
            movies.add(movie);
            movieDao.update(movie);
        } else {
            throw new NoSuchMovieException(movie.getId());
        }

    }

    private void initMovies() {
        if (movies.isEmpty()) {
            movies = movieDao.getAll();
        }
    }

}

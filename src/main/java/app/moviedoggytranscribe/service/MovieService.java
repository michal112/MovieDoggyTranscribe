package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.dao.SimpleMovieDao;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;

import java.util.List;

@org.springframework.stereotype.Service
public class MovieService extends AbstractService<Movie, NoSuchMovieException> implements SimpleMovieService {

    @Override
    public List<Status> getMovieStasuses(Movie movie) {
        return ((SimpleMovieDao) getDao()).getStatuses(movie.getId());
    }

    @Override
    public List<Watcher> getMovieWatchers(Movie movie) {
        return ((SimpleMovieDao) getDao()).getWatchers(movie.getId());
    }

}

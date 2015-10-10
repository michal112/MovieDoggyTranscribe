package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.dao.MovieDao;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;

import java.util.List;

@org.springframework.stereotype.Service
public class MovieService extends AbstractService<Movie, NoSuchMovieException> implements SimpleMovieService {

    @Override
    public List<Status> getMovieStasuses(Movie movie) {
        return ((MovieDao) getDao()).getStatuses(movie.getId());
    }

    @Override
    public List<Watcher> getMovieWatchers(Movie movie) {
        return ((MovieDao) getDao()).getWatchers(movie.getId());
    }

}

package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.dao.SimpleMovieWatcherDao;
import app.moviedoggytranscribe.model.entity.MovieWatcher;

@org.springframework.stereotype.Service
public class MovieWatcherService extends AbstractService<MovieWatcher, NoSuchConnectionException>
        implements SimpleMovieWatcherService {

    @Override
    public void deleteByMovieIdAndWatcherId(Integer movieId, Integer watcherId) throws NoSuchConnectionException {
        MovieWatcher movieWatcher = ((SimpleMovieWatcherDao) getDao()).getByMovieIdAndWatcherId(movieId, watcherId);
        delete(movieWatcher.getId());
    }

}

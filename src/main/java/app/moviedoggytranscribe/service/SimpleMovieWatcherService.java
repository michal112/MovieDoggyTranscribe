package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.entity.MovieWatcher;

public interface SimpleMovieWatcherService extends Service<MovieWatcher, NoSuchConnectionException> {

    void deleteByMovieIdAndWatcherId(Integer movieId, Integer watcherId) throws NoSuchConnectionException;

}

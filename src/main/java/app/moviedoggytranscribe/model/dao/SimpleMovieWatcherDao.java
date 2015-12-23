package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.MovieWatcher;

public interface SimpleMovieWatcherDao extends Dao<MovieWatcher> {

    MovieWatcher getByMovieIdAndWatcherId(Integer movieId, Integer watcherId);

}

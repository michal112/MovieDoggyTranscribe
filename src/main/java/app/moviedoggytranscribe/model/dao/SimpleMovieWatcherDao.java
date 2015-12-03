package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.MovieWatcher;

public interface SimpleMovieWatcherDao extends Dao<MovieWatcher> {

    void deleteByWatcherId(Integer watcherId);

}

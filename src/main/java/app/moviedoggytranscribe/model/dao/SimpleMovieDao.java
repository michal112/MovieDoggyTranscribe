package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;

import java.util.List;

public interface SimpleMovieDao extends Dao<Movie> {

    List<Status> getStatuses(Integer movieId);
    List<Watcher> getWatchers(Integer movieId);

}

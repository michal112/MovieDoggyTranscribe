package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;

import java.util.List;

public interface SimpleMovieService extends Service<Movie, NoSuchMovieException> {

    List<Status> getMovieStasuses(Movie movie);
    List<Watcher> getMovieWatchers(Movie movie);

}

package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.entity.MovieStatus;

public interface SimpleMovieStatusService extends Service<MovieStatus, NoSuchConnectionException> {

    void deleteByStatusId(Integer statusId) throws NoSuchConnectionException;

}

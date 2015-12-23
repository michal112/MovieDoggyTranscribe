package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.MovieStatus;

public interface SimpleMovieStatusDao extends Dao<MovieStatus> {

    MovieStatus getByMovieIdAndStatusId(Integer movieId, Integer statusId);

}

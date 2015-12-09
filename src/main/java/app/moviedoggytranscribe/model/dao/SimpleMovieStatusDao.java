package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.MovieStatus;

public interface SimpleMovieStatusDao extends Dao<MovieStatus> {
    void deleteByStatusId(Integer statusId);

}

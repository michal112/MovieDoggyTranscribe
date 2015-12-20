package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.dao.SimpleMovieStatusDao;
import app.moviedoggytranscribe.model.entity.MovieStatus;

@org.springframework.stereotype.Service
public class MovieStatusService extends AbstractService<MovieStatus, NoSuchConnectionException>
        implements SimpleMovieStatusService {

    @Override
    public void deleteByMovieIdAndStatusId(Integer movieId, Integer statusId) throws NoSuchConnectionException {
        MovieStatus movieStatus = ((SimpleMovieStatusDao) getDao()).getByMovieIdAndStatusId(movieId, statusId);
        delete(movieStatus.getId());
    }

}

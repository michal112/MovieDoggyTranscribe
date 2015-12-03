package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.dao.MovieWatcherDao;
import app.moviedoggytranscribe.model.entity.MovieWatcher;

@org.springframework.stereotype.Service
public class MovieWatcherService extends AbstractService<MovieWatcher, NoSuchConnectionException>
        implements SimpleMovieWatcherService {

    @Override
    public void deleteByWatcherId(Integer watcherId) {
        ((MovieWatcherDao) getDao()).deleteByWatcherId(watcherId);
    }

}

package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.dao.SimpleMovieWatcherDao;
import app.moviedoggytranscribe.model.entity.MovieWatcher;

@org.springframework.stereotype.Service
public class MovieWatcherService extends AbstractService<MovieWatcher, NoSuchConnectionException>
        implements SimpleMovieWatcherService {

    @Override
    public void deleteByWatcherId(Integer watcherId) throws NoSuchConnectionException {
        initEntities();
        entities.remove(get(watcherId));
        ((SimpleMovieWatcherDao) getDao()).deleteByWatcherId(watcherId);

        notifyObservers();
    }

}

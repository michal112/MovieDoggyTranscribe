package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.model.dao.SimpleWatcherDao;
import app.moviedoggytranscribe.model.entity.Watcher;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class WatcherService extends AbstractService<Watcher, NoSuchWatcherException> implements SimpleWatcherService {

    @Override
    public List<String> getAllExistWatchers() throws NoSuchWatcherException {
        List<String> stringList = new ArrayList<String>();
        List<Watcher> watcherList = ((SimpleWatcherDao) getDao()).getAll();
        for (Watcher watcher : watcherList) {
            stringList.add(watcher.getNick());
        }

        return stringList;

    }

    @Override
    public Watcher getWatcherByNameAndSurname(String nick) throws NoSuchWatcherException {
        Watcher watcher = ((SimpleWatcherDao) getDao()).getWatcherByNameAndSurname(nick);
        return watcher;
    }


}

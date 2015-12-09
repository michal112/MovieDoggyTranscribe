package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.model.entity.Watcher;

import java.util.List;

public interface SimpleWatcherService extends Service<Watcher, NoSuchWatcherException> {
    List<String> getAllExistWatchers() throws NoSuchWatcherException;
    Watcher getWatcherByNameAndSurname(String nick) throws NoSuchWatcherException;
}

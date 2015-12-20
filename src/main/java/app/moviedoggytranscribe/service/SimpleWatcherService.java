package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.model.entity.Watcher;

public interface SimpleWatcherService extends Service<Watcher, NoSuchWatcherException> {}

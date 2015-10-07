package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.model.entity.Watcher;

@org.springframework.stereotype.Service
public class WatcherService extends AbstractService<Watcher, NoSuchWatcherException> {}

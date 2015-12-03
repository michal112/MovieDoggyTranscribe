package app.moviedoggytranscribe.model;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.model.entity.*;
import app.moviedoggytranscribe.service.Service;
import app.moviedoggytranscribe.service.SimpleMovieService;
import app.moviedoggytranscribe.service.SimpleMovieWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Observable;

@Component
public class DataSourceHolder extends Observable {

    @Autowired
    private SimpleMovieService movieService;
    @Autowired
    private Service<Status, NoSuchStatusException> statusService;
    @Autowired
    private Service<Watcher, NoSuchWatcherException> watcherService;
    @Autowired
    private Service<MovieStatus, NoSuchConnectionException> movieStatusService;
    @Autowired
    private SimpleMovieWatcherService movieWatcherService;

    private DataSourceType dataSourceType;

    @PostConstruct
    public void init() {
        dataSourceType = DataSourceType.DEFAULT;
        addObserver(movieService);
        addObserver(statusService);
        addObserver(watcherService);
        addObserver(movieStatusService);
        addObserver(movieWatcherService);
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        DataSourceType old = this.dataSourceType;
        this.dataSourceType = dataSourceType;

        if (!old.equals(this.dataSourceType)) {
            setChanged();
        } else {
            clearChanged();
        }

        notifyObservers();
    }

    public DataSourceType getDataSourceType() {
        return this.dataSourceType;
    }

}

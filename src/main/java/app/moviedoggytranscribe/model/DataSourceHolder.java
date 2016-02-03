package app.moviedoggytranscribe.model;

import app.moviedoggytranscribe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Observable;

@Component
public class DataSourceHolder extends Observable {

    @Autowired
    private SimpleMovieService movieService;
    @Autowired
    private SimpleStatusService statusService;
    @Autowired
    private SimpleWatcherService watcherService;
    @Autowired
    private SimpleMovieStatusService movieStatusService;
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

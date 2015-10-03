package app.moviedoggytranscribe.model.data;

import javafx.beans.property.SimpleStringProperty;

public class MovieData implements Data {

    private SimpleStringProperty movieName = new SimpleStringProperty();
    private SimpleStringProperty watcherName = new SimpleStringProperty();;
    private SimpleStringProperty status = new SimpleStringProperty();;

    public String getMovieName() {
        return movieName.get();
    }

    public SimpleStringProperty movieNameProperty() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
    }

    public String getWatcherName() {
        return watcherName.get();
    }

    public SimpleStringProperty watcherNameProperty() {
        return watcherName;
    }

    public void setWatcherName(String watcherName) {
        this.watcherName.set(watcherName);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

}

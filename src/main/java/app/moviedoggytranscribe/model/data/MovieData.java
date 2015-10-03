package app.moviedoggytranscribe.model.data;

import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class MovieData implements Data {

    private ObjectProperty<Movie> movie = new SimpleObjectProperty<>();
    private ObjectProperty<List<Watcher>> watchers = new SimpleObjectProperty<>();
    private ObjectProperty<List<Status>> statuses = new SimpleObjectProperty<>();

    public Movie getMovie() {
        return movie.get();
    }

    public ObjectProperty<Movie> movieProperty() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie.set(movie);
    }

    public List<Watcher> getWatchers() {
        return watchers.get();
    }

    public ObjectProperty<List<Watcher>> watchersProperty() {
        return watchers;
    }

    public void setWatchers(List<Watcher> watchers) {
        this.watchers.set(watchers);
    }

    public List<Status> getStatuses() {
        return statuses.get();
    }

    public ObjectProperty<List<Status>> statusesProperty() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses.set(statuses);
    }

}

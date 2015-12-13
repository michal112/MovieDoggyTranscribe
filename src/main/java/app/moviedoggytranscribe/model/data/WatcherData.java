package app.moviedoggytranscribe.model.data;

import app.moviedoggytranscribe.model.entity.Watcher;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WatcherData implements Data {

    private ObjectProperty<Watcher> watcher = new SimpleObjectProperty<>();

    public WatcherData(Watcher watcher) {
        this.watcher.set(watcher);
    }

    public Watcher getWatcher() {
        return watcher.get();
    }

    public ObjectProperty<Watcher> watcherProperty() {
        return watcher;
    }

    @Override
    public String toString() {
        return watcher.get().getName() + " " + watcher.get().getSurname();
    }

}

package app.moviedoggytranscribe.model.data;

import app.moviedoggytranscribe.model.entity.Status;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class StatusData implements Data {

    private ObjectProperty<Status> status = new SimpleObjectProperty<>();

    public StatusData(Status status) {
        this.status.set(status);
    }

    public Status getStatus() {
        return status.get();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }

    @Override
    public String toString() {
        return status.get().getName();
    }

}

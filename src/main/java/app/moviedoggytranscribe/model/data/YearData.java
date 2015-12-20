package app.moviedoggytranscribe.model.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class YearData implements Data {

    private ObjectProperty<Integer> year = new SimpleObjectProperty<>();

    public Integer getYear() {
        return year.get();
    }

    public ObjectProperty<Integer> yearProperty() {
        return year;
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }

    @Override
    public String toString() {
        return year.get() != null ? year.get().toString() : "Wszystko";
    }

}

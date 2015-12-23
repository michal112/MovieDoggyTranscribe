package app.moviedoggytranscribe.model.data;

import info.talacha.filmweb.models.Film;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FilmData implements Data {

    private ObjectProperty<Film> film = new SimpleObjectProperty<>();
    private ObjectProperty<String> title = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> year = new SimpleObjectProperty<>();

    public Film getFilm() {
        return film.get();
    }

    public ObjectProperty<Film> filmProperty() {
        return film;
    }

    public void setFilm(Film film) {
        this.film.set(film);
    }

    public String getTitle() {
        return title.get();
    }

    public ObjectProperty<String> titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public Integer getYear() {
        return year.get();
    }

    public ObjectProperty<Integer> yearProperty() {
        return year;
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }

}

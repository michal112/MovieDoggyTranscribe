package app.moviedoggytranscribe.controller;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.models.Film;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


@Component
@Scope(value = "prototype")
public class MovieAddViewController implements DataController {

    @FXML
    private TableView<Film> mainTable;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn yearColumn;
    @FXML
    private TableColumn genreColumn;

    @FXML
    private Button addMovie;

    public MovieAddViewController() {
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public void initialize() {
        addMovie.setOnAction((event) -> {
            FilmwebApi fa = new FilmwebApi();
            ArrayList<Film> film = fa.getFilmList("Killer");
            System.out.println(film.get(0).getTitle());
        });

    }

    @Override
    public void addObservables() {

    }

    @Override
    public void update() {
    }

    @Override
    public void removeObservables() {

    }

    @Override
    public void setData(Object data) {
    }
}

package app.moviedoggytranscribe.controller;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.models.Film;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


@Component
public class FilmwebViewController implements DataController{

    @FXML
    private TableView<Film> mainTable;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn yearColumn;
    @FXML
    private TableColumn genreColumn;

    @FXML
    private Button addFilmweb;

    public FilmwebViewController() {
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public void initialize() {
        addFilmweb.setOnAction((event) -> {
            FilmwebApi fa = new FilmwebApi();
            ArrayList<Film> film = fa.getFilmList("Killer");
            System.out.println(film.get(0).getTitle());
        });

    }

    @Override
    public void update() {
    }

    @Override
    public void setData(Object data) {
    }
}

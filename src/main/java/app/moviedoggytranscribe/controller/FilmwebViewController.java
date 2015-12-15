package app.moviedoggytranscribe.controller;

import info.talacha.filmweb.models.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.table.TableColumn;


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
    }

    @Override
    public void update() {
    }

    @Override
    public void setData(Object data) {
    }
}

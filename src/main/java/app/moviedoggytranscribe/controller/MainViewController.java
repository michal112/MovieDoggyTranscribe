package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.mapper.ToMovieDataMapper;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.Service;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@org.springframework.stereotype.Component
public class MainViewController {

    @FXML
    private TableView<MovieData> mainTable;
    @FXML
    private TableColumn<MovieData, Movie> movieColumn;
    @FXML
    private TableColumn<MovieData, List<Watcher>> watchersColumn;
    private ObservableList<MovieData> movieDataList;

    @Autowired
    private Service<Movie, NoSuchMovieException> movieService;

    @PostConstruct
    public void init() {
        movieDataList = FXCollections.observableArrayList();
        Mapper<MovieData> movieDataMapper = new ToMovieDataMapper();
        MovieData movieData = movieDataMapper.mapToData(movieService.getAll());
        movieDataList.add(movieData);
    }

    @FXML
    private void initialize() {
        movieColumn.setCellValueFactory(cellData -> cellData.getValue().movieProperty());
        movieColumn.setCellFactory(cell -> new TableCell<MovieData, Movie>() {
            @Override
            protected void updateItem(Movie item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });
        watchersColumn.setCellValueFactory(cellData -> cellData.getValue().watchersProperty());

        mainTable.setItems(movieDataList);
    }
}

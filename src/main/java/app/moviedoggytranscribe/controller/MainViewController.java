package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private TableColumn<MovieData, List<Status>> statusesColumn;

    private ObservableList<MovieData> movieDataList;

    @Autowired
    private Mapper<Movie, MovieData> movieDataMapper;
    @Autowired
    private Service<Movie, NoSuchMovieException> movieService;

    @FXML
    private Button addMovie;

    @PostConstruct
    public void init() {
        movieDataList = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        List<MovieData> movieDatas = movieDataMapper.mapToData(movieService.getAll());
        movieDataList.addAll(movieDatas);

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
        watchersColumn.setCellFactory(cell -> new TableCell<MovieData, List<Watcher>>() {
            @Override
            protected void updateItem(List<Watcher> item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    String watchers = new String();
                    for (Watcher watcher : item) {
                        watchers += watcher.getName() + " " + watcher.getSurname() + " ";
                    }
                    setText(watchers);
                }
            }
        });

        statusesColumn.setCellValueFactory(cellData -> cellData.getValue().statusesProperty());
        statusesColumn.setCellFactory(cell -> new TableCell<MovieData, List<Status>>() {
            @Override
            protected void updateItem(List<Status> item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    String statuses = new String();
                    for (Status status : item) {
                        statuses += status.getName() + " ";
                    }
                    setText(statuses);
                }
            }
        });

        mainTable.setItems(movieDataList);

        addMovie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dialog<Integer> dialog = new Dialog<Integer>();
                dialog.contentTextProperty().setValue("addMovieButton clicked");
                dialog.show();
            }
        });
    }

}

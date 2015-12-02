package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.ApplicationCore;
import app.moviedoggytranscribe.FxmlElement;
import app.moviedoggytranscribe.SpringFxmlLoader;
import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@org.springframework.stereotype.Component
public class MainViewController implements Controller {

    @FXML
    private TableView<MovieData> mainTable;
    @FXML
    private TableColumn<MovieData, Movie> movieColumn;
    @FXML
    private TableColumn<MovieData, List<Watcher>> watchersColumn;
    @FXML
    private TableColumn<MovieData, List<Status>> statusesColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button editMovie;
    @FXML
    private Button deleteMovie;

    private ObservableList<MovieData> movieDataList;

    @Autowired
    private Mapper<Movie, MovieData> movieDataMapper;
    @Autowired
    private Service<Movie, NoSuchMovieException> movieService;

    @FXML
    private Button addMovie;

    @Override
    public void setData(Object data) {}

    @PostConstruct
    public void init() {
        movieDataList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        initializeTableView();

        // search engine

        mainTable.setItems(movieDataList);
        movieColumn.setCellValueFactory(cellData -> cellData.getValue().movieProperty());
        FilteredList<MovieData> filteredData = new FilteredList<>(movieDataList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movieData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (movieData.getMovie().getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });

        SortedList<MovieData> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(mainTable.comparatorProperty());
        mainTable.setItems(sortedData);

        // mouseEvent - double click on row -> open movie details

        mainTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + AppConstants.VIEWS_FOLDER_NAME
                            + File.separator + "movieView.fxml"));
                    MovieViewController controller = new MovieViewController(mainTable.getSelectionModel().getSelectedItem());
                    loader.setController(controller);
                    Parent root;
                    try {
                        root = (Parent) loader.load();
                        Scene scene = new Scene(root, 700, 600);
                        Stage stage = new Stage();
                        stage.setTitle(ViewConstants.MOVIE_VIEW_WINDOW_TITLE);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MovieViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        // mouseEvent - click on Delete Button

        deleteMovie.setOnAction((event) -> {
            MovieData selectionMovie = mainTable.getSelectionModel().getSelectedItem();
            if(selectionMovie == null) {
                return;
            }
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Usuń film");
                    alert.setHeaderText("Czy chcesz usunąć film z bazy danych ?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        movieService.delete(selectionMovie.getMovie().getId());
                        movieDataList.clear();
                        List<MovieData> movieDatas = movieDataMapper.mapToData(movieService.getAll());
                        movieDataList.addAll(movieDatas);
                    }
                } catch (NoSuchMovieException e) {
                    e.printStackTrace();
                }
        });

        // mouseEvent - click on Edit Button

        editMovie.setOnAction((event) -> {
            MovieData selectionMovie = mainTable.getSelectionModel().getSelectedItem();
            if(selectionMovie == null) {
                return;
            }

            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<MovieEditViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + "movieEditView.fxml", MovieEditViewController.class,
                    mainTable.getSelectionModel().getSelectedItem());

            Scene scene = new Scene(fxmlElement.getRoot(), 700, 700);
            Stage stage = new Stage();
            stage.setTitle(ViewConstants.MOVIE_VIEW_WINDOW_TITLE);
            stage.setScene(scene);
            stage.show();
        });
    }

    public void initializeTableView() {
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
                        watchers += watcher.getNick() + " ";
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
    }


}

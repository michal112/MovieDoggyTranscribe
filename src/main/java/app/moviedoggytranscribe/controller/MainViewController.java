package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.ApplicationCore;
import app.moviedoggytranscribe.FxmlElement;
import app.moviedoggytranscribe.SpringFxmlLoader;
import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.data.StatusData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
    private ChoiceBox<StatusData> statusChoiceBox;
    @FXML
    private Button editMovie;
    @FXML
    private Button deleteMovie;
    @FXML
    private Button addMovie;
    @FXML
    private Button adminView;

    @Autowired
    private Mapper<Movie, MovieData> movieDataMapper;
    @Autowired
    private Mapper<Status, StatusData> statusDataMapper;
    @Autowired
    private SimpleMovieService movieService;
    @Autowired
    private SimpleStatusService statusService;
    @Autowired
    private SimpleWatcherService watcherService;
    @Autowired
    private SimpleMovieStatusService movieStatusService;
    @Autowired
    private SimpleMovieWatcherService movieWatcherService;

    private ObservableList<MovieData> movieDataList;
    private ObservableList<StatusData> statusDataList;

    private Predicate<MovieData> statusPredicate = movieData -> true;
    private Predicate<MovieData> titlePredicate = movieData -> true;

    @PostConstruct
    public void init() {
        movieDataList = FXCollections.observableArrayList();
        statusDataList = FXCollections.observableArrayList();

        movieService.addObserver(this);
        statusService.addObserver(this);
        watcherService.addObserver(this);
        movieStatusService.addObserver(this);
        movieWatcherService.addObserver(this);
    }

    @FXML
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        initializeTable();

        // filters - title filter and status filter

        FilteredList<MovieData> filteredMovieDataList = new FilteredList<>(movieDataList, statusPredicate.and(titlePredicate));

        statusChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            statusPredicate = getStatusPredicate(newValue);
            filteredMovieDataList.setPredicate(statusPredicate.and(titlePredicate));
            update();
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            titlePredicate = getTitlePredicate(newValue);
            filteredMovieDataList.setPredicate(statusPredicate.and(titlePredicate));
            update();
        });

        SortedList<MovieData> sortedMovieDataList = new SortedList<>(filteredMovieDataList);
        sortedMovieDataList.comparatorProperty().bind(mainTable.comparatorProperty());
        mainTable.setItems(sortedMovieDataList);

        // mouseEvent - double click on row -> open movie details

        mainTable.setOnMousePressed((event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                MovieData selectionMovie = mainTable.getSelectionModel().getSelectedItem();
                if(selectionMovie == null) {
                    return;
                }

                SpringFxmlLoader loader = ApplicationCore.getLoader();
                FxmlElement<AnchorPane, MainViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                     + File.separator + ViewConstants.MOVIE_DETAIL_VIEW_FILE_NAME, MovieViewController.class, selectionMovie);

                Scene scene = new Scene(fxmlElement.getRoot(), 700, 600);
                Stage stage = new Stage();
                stage.setTitle(ViewConstants.MOVIE_VIEW_TITLE);
                stage.setScene(scene);
                stage.show();
            }
        });

        // mouseEvent - click on delete button

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

                    update();
                }
            } catch (NoSuchMovieException e) {
                Logger.getLogger(MainViewController.class.getCanonicalName()).severe("Movie already deleted");
            }
        });

        // mouseEvent - click on Edit Button

        editMovie.setOnAction((event) -> {
            MovieData selectionMovie = mainTable.getSelectionModel().getSelectedItem();
            if(selectionMovie == null) {
                return;
            }

            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, MovieEditViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                + File.separator + ViewConstants.MOVIE_EDIT_VIEW_FILE_NAME, MovieEditViewController.class, selectionMovie);

            Scene scene = new Scene(fxmlElement.getRoot(), 700, 700);
            Stage stage = new Stage();
            stage.setTitle(ViewConstants.MOVIE_VIEW_TITLE);
            stage.setScene(scene);
            stage.show();
        });

        // mouseEvent - click on Add Button

        addMovie.setOnAction((event) -> {
            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, MovieAddViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + ViewConstants.MOVIE_ADD_VIEW_FILE_NAME, MovieAddViewController.class);

            Scene scene = new Scene(fxmlElement.getRoot(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle(ViewConstants.MOVIE_ADD_VIEW_TITLE);
            stage.setScene(scene);
            stage.show();
        });

        // mouseEvent - click on Settings Button

        adminView.setOnAction((event) -> {
            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, MovieAddViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + ViewConstants.ADMIN_VIEW_FILE_NAME, AdminViewController.class);

            Scene scene = new Scene(fxmlElement.getRoot(), 600, 800);
            Stage stage = new Stage();
            stage.setTitle(ViewConstants.ADMIN_VIEW_TITLE);
            stage.setScene(scene);
            stage.show();
        });

    }

    private Predicate<MovieData> getTitlePredicate(String title) {
        return movieData -> {
            if (title == null || title.isEmpty()) {
                return true;
            }

            String lowerCaseTitle = title.toLowerCase();
            if (movieData.getMovie().getTitle().toLowerCase().contains(lowerCaseTitle)) {
                return true;
            }
            return false;
        };
    }

    private Predicate<MovieData> getStatusPredicate(StatusData statusData) {
        return movieData -> {
            if (statusData.getStatus().getId() == null) {
                return true;
            }

            for (Status status : movieData.getStatuses()) {
                if (status.getId().equals(statusData.getStatus().getId())) {
                    return true;
                }
            }
            return false;
        };
    }

    private void initializeTable() {
        movieDataList.addAll(movieDataMapper.mapToData(movieService.getAll()));

        Status defaultStatus = new Status();
        defaultStatus.setName("Wszystko");
        StatusData defaultStatusData = new StatusData(defaultStatus);
        statusDataList.add(defaultStatusData);
        statusDataList.addAll(statusDataMapper.mapToData(statusService.getAll()));
        statusChoiceBox.setItems(statusDataList);
        statusChoiceBox.setValue(defaultStatusData);

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
                    String watchers = "";
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
                    HBox vBox = new HBox();
                    for (Status status : item) {
                        Circle circle = new Circle(15, 15, 15);
                        Paint fill = Paint.valueOf(status.getColour());
                        circle.setFill(fill);
                        vBox.setSpacing(10);
                        vBox.getChildren().add(circle);
                    }
                    setGraphic(vBox);
                }
            }
        });

        mainTable.setItems(movieDataList);
    }

    private void refreshData() {
        if (!movieDataList.isEmpty()) {
            movieDataList.clear();
        }

        movieDataList.addAll(movieDataMapper.mapToData(movieService.getAll()));
        mainTable.refresh();
    }

    @Override
    public void update() {
        refreshData();
    }
}

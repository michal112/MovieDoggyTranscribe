package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.model.data.Data;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.data.StatusData;
import app.moviedoggytranscribe.model.data.WatcherData;
import app.moviedoggytranscribe.model.entity.MovieStatus;
import app.moviedoggytranscribe.model.entity.MovieWatcher;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class MovieEditViewController implements DataController {

    private static final Logger LOG = Logger.getLogger(MovieEditViewController.class.getCanonicalName());

    @Autowired
    private Mapper<Watcher, WatcherData> watcherDataMapper;
    @Autowired
    private Mapper<Status, StatusData> statusDataMapper;
    @Autowired
    private SimpleMovieWatcherService movieWatcherService;
    @Autowired
    private SimpleMovieService movieService;
    @Autowired
    private SimpleMovieStatusService movieStatusService;

    @Autowired
    private SimpleStatusService statusService;

    @Autowired
    private SimpleWatcherService watcherService;

    @FXML
    private ImageView imageView;
    @FXML
    private Label title;
    @FXML
    private Label rating;
    @FXML
    private Label type;
    @FXML
    private Label year;
    @FXML
    private TextArea describe;
    @FXML
    private ListView<WatcherData> watchers;
    @FXML
    private ListView<StatusData> statuses;

    @FXML
    private Button addWatcher;
    @FXML
    private Button addStatus;
    @FXML
    private Button deleteWatcher;
    @FXML
    private Button deleteStatus;

    private MovieData movieData;

    private ObservableList<WatcherData> watcherDataList;
    private ObservableList<StatusData> statusesDataList;

    @PostConstruct
    public void init() {
        watcherDataList = FXCollections.observableArrayList();
        statusesDataList = FXCollections.observableArrayList();

        movieWatcherService.addObserver(this);
        movieStatusService.addObserver(this);
        watcherService.addObserver(this);
        statusService.addObserver(this);
        movieService.addObserver(this);
    }

    public void setMovieData(MovieData movieData) {
        this.movieData = movieData;
    }

    public MovieEditViewController() {
    }

    @Override
    public void setData(Object data) {
        setMovieData((MovieData) data);
    }

    @FXML
    @Override
    public void initialize() {
        title.setText(movieData.getMovie().getTitle());
        type.setText(movieData.getMovie().getGenre());
        imageView.setImage(new Image(movieData.getMovie().getImageUrl()));
        year.setText("Rok produkcji: " + movieData.getMovie().getYear());
        describe.setText(movieData.getMovie().getDescription());
        rating.setText(movieData.getMovie().getRating());

        describe.setEditable(false);
        watchers.setEditable(false);
        statuses.setEditable(false);
        describe.setWrapText(true);

        insertWatchersToListView();
        insertStatusesToListView();

        // add Watcher

        addWatcher.setOnAction(event -> {
            List<WatcherData> choices = new ArrayList<>();
            List<Integer> movieWatchers = watcherDataList.stream().map(
                watcherData -> watcherData.getWatcher().getId()).collect(Collectors.toList());

            choices.addAll(watcherDataMapper.mapToData(watcherService.getAll()).stream().filter(watcherData ->
                !movieWatchers.contains(watcherData.getWatcher().getId())).collect(Collectors.toList()));

            ChoiceDialog<WatcherData> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Dodaj oglądającego");
            dialog.setHeaderText("Dodaj osobę, która oglądała z Tobą film.");
            dialog.setContentText("Osoba:");

            Optional<WatcherData> result = dialog.showAndWait();

            Watcher watcher = result.get().getWatcher();

            Integer watcherId = watcher.getId();
            Integer movieId = movieData.getMovie().getId();

            movieData.getWatchers().add(watcher);

            movieWatcherService.add(new MovieWatcher(movieId, watcherId));
        });

        // add Status

        addStatus.setOnAction(event -> {
            List<StatusData> choices = new ArrayList<>();
            List<Integer> movieStatuses = statusesDataList.stream().map(
                    statusData -> statusData.getStatus().getId()).collect(Collectors.toList());

            choices.addAll(statusDataMapper.mapToData(statusService.getAll()).stream().filter(statusData ->
                    !movieStatuses.contains(statusData.getStatus().getId())).collect(Collectors.toList()));

            ChoiceDialog<StatusData> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Dodaj status filmu");
            dialog.setHeaderText("Dodaj aktualny status filmu.");
            dialog.setContentText("Status:");

            Optional<StatusData> result = dialog.showAndWait();

            Status status = result.get().getStatus();

            Integer statusId = status.getId();
            Integer movieId = movieData.getMovie().getId();

            movieData.getStatuses().add(status);

            movieStatusService.add(new MovieStatus(movieId, statusId));
        });

        // delete Watcher

        deleteWatcher.setOnAction(event -> {
            ObservableList<WatcherData> selectedItems = watchers.getSelectionModel().getSelectedItems();
            if(selectedItems == null) {
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuń oglądającego film");
            alert.setHeaderText("Czy chcesz usunąć oglądającego ten film?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Watcher watcher = !selectedItems.isEmpty() ? selectedItems.get(0).getWatcher() : null;
                if (watcher == null) {
                    return;
                }

                try {
                    movieData.getWatchers().remove(watcher);
                    movieWatcherService.deleteByMovieIdAndWatcherId(movieData.getMovie().getId(), watcher.getId());
                } catch (NoSuchConnectionException e) {
                    LOG.severe("No connection between movie and watcher " + e.getMessage());
                }
            }
        });

        // delete Status

        deleteStatus.setOnAction(event -> {
            ObservableList<StatusData> selectedItems = statuses.getSelectionModel().getSelectedItems();
            if(selectedItems == null) {
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuń status filmu");
            alert.setHeaderText("Czy chcesz usunąć status tego filmu?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                Status status = !selectedItems.isEmpty() ? selectedItems.get(0).getStatus() : null;
                if (status == null) {
                    return;
                }

                try {
                    movieData.getStatuses().remove(status);
                    movieStatusService.deleteByMovieIdAndStatusId(movieData.getMovie().getId(), status.getId());
                } catch (NoSuchConnectionException e) {
                    LOG.severe("No connection between movie and status " + e.getMessage());
                }
            }
        });
    }

    private void insertWatchersToListView() {
        clearObservable(watcherDataList);

        watcherDataList.addAll(watcherDataMapper.mapToData(movieData.getWatchers()));

        watchers.setCellFactory(new Callback<ListView<WatcherData>, ListCell<WatcherData>>() {
            @Override
            public ListCell<WatcherData> call(ListView<WatcherData> param) {
                return new ListCell<WatcherData>() {
                    @Override
                    protected void updateItem(WatcherData item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            Watcher watcher = item.getWatcher();
                            setText(watcher.getName() + " " + watcher.getSurname());
                        }
                    }
                };
            }
        });

        watchers.setItems(watcherDataList);
    }

    private void insertStatusesToListView() {
        clearObservable(statusesDataList);

        statusesDataList.addAll(statusDataMapper.mapToData(movieData.getStatuses()));

        statuses.setCellFactory(new Callback<ListView<StatusData>, ListCell<StatusData>>() {
            @Override
            public ListCell<StatusData> call(ListView<StatusData> param) {
                return new ListCell<StatusData>() {
                    @Override
                    protected void updateItem(StatusData item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            Status status = item.getStatus();
                            
                            setStyle("-fx-background-color:" + status.getColour());
                            setText(status.getName());
                        }
                    }
                };
            }
        });

        statuses.setItems(statusesDataList);
    }

    private <T extends Data> void clearObservable(ObservableList<T> observableList) {
        if (!observableList.isEmpty()) {
            observableList.clear();
        }
    }

    @Override
    public void update() {
        insertStatusesToListView();
        insertWatchersToListView();
    }

}
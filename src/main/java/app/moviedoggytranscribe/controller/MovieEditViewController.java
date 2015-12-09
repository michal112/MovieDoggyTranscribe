package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.MovieWatcher;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.SimpleMovieStatusService;
import app.moviedoggytranscribe.service.SimpleMovieWatcherService;
import app.moviedoggytranscribe.service.SimpleStatusService;
import app.moviedoggytranscribe.service.SimpleWatcherService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieEditViewController implements DataController {

    @Autowired
    private SimpleMovieWatcherService movieWatcherService;

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
    private ListView<String> watchers;
    @FXML
    private ListView<String> statuses;

    @FXML
    private Button addWatcher;
    @FXML
    private Button addStatus;
    @FXML
    private Button deleteWatcher;
    @FXML
    private Button deleteStatus;

    private MovieData movieData;

    private ObservableList<String> watchersObservableList;
    private ObservableList<String> statusesObservableList;

    @PostConstruct
    public void init() {
        watchersObservableList = FXCollections.observableArrayList();
        statusesObservableList = FXCollections.observableArrayList();

        movieWatcherService.addObserver(this);
        movieStatusService.addObserver(this);
        watcherService.addObserver(this);
        statusService.addObserver(this);
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
            List<String> choices = new ArrayList<>();
            try {
                choices.addAll(watcherService.getAllExistWatchers());
            } catch (NoSuchWatcherException e) {
                e.printStackTrace();
            }

            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Dodaj oglądającego");
            dialog.setHeaderText("Dodaj osobę, która oglądała z Tobą film.");
            dialog.setContentText("Osoba:");

            Optional<String> result = dialog.showAndWait();

            if(watchersObservableList.contains(result.get())) {
                System.out.println("JUZ JEST DODANY");
            } else {
                // dodajemy watchera do movie
                try {
                    Integer trolololo = watcherService.getWatcherByNick(result.get()).getId();
                    MovieWatcher movieWatcher = new MovieWatcher(movieData.getMovie().getId(), trolololo);
                    movieWatcherService.add(new MovieWatcher(movieData.getMovie().getId(), trolololo));
                    // trzeba odswiezyc
                } catch (NoSuchWatcherException e) {
                    e.printStackTrace();
                }
            }
        });

        // add Status

        addStatus.setOnAction(event -> {
            List<String> choices = new ArrayList<>();
            try {
                choices.addAll(statusService.getAllExistStatuses());
            } catch (NoSuchStatusException e) {
                e.printStackTrace();
            }

            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Dodaj status filmu");
            dialog.setHeaderText("Dodaj aktualny status filmu.");
            dialog.setContentText("Status:");

            Optional<String> result = dialog.showAndWait();
            System.out.println(result.get());
        });

        // delete Watcher

        deleteWatcher.setOnAction(event -> {
            ObservableList<String> selectedItems = watchers.getSelectionModel().getSelectedItems();
            if(selectedItems == null) {
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuń oglądającego film");
            alert.setHeaderText("Czy chcesz usunąć oglądającego ten film ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Watcher watcher = movieData.getWatchers().get(watchers.getSelectionModel().getSelectedIndex());
                movieData.getWatchers().remove(watcher);
                try {
                    movieWatcherService.deleteByWatcherId(watcher.getId());
                } catch (NoSuchConnectionException e) {
                    e.printStackTrace();
                }
            }
        });

        // delete Status

        deleteStatus.setOnAction(event -> {
            ObservableList<String> selectedItems = statuses.getSelectionModel().getSelectedItems();
            if(selectedItems == null) {
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuń status filmu");
            alert.setHeaderText("Czy chcesz usunąć status tego filmu ?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                Status status = movieData.getStatuses().get(statuses.getSelectionModel().getSelectedIndex());
                movieData.getStatuses().remove(status);
                try {
                    movieStatusService.deleteByStatusId(status.getId());
                } catch (NoSuchConnectionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void insertWatchersToListView() {
        clearObservable(watchersObservableList);

        watchersObservableList.addAll(movieData.getWatchers().stream().map(watcher -> watcher.getName()
                + " " + watcher.getSurname()).collect(Collectors.toList()));

        watchers.setItems(watchersObservableList);
    }

    private void insertStatusesToListView() {
        clearObservable(statusesObservableList);

        statusesObservableList.addAll(movieData.getStatuses().stream().map(Status::getName).collect(Collectors.toList()));

        statuses.setItems(statusesObservableList);
    }

    private void clearObservable(ObservableList<String> observableList) {
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
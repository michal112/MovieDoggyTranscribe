package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.service.SimpleMovieService;
import app.moviedoggytranscribe.service.SimpleMovieStatusService;
import app.moviedoggytranscribe.service.SimpleMovieWatcherService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
@Scope(value = "prototype")
public class MovieViewController implements DataController {

    @Autowired
    private SimpleMovieStatusService movieStatusService;
    @Autowired
    private SimpleMovieWatcherService movieWatcherService;
    @Autowired
    private SimpleMovieService movieService;

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
    private TextArea description;
    @FXML
    private ListView<String> watchers;
    @FXML
    private ListView<String> statuses;

    private Movie movie;

    private ObservableList<String> watchersDataList;
    private ObservableList<String> statusesDataList;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @PostConstruct
    @Override
    public void init() {
        watchersDataList = FXCollections.observableArrayList();
        statusesDataList = FXCollections.observableArrayList();

        addObservables();
    }

    @FXML
    public void initialize() {
        title.setText(movie.getTitle());
        type.setText(movie.getGenre());
        imageView.setImage(new Image(movie.getImageUrl()));
        year.setText("Rok produkcji: " + movie.getYear());
        description.setText(movie.getDescription());
        rating.setText(movie.getRating());

        description.setEditable(false);
        watchers.setEditable(false);
        statuses.setEditable(false);
        description.setWrapText(true);

        watchers.setItems(watchersDataList);
        statuses.setItems(statusesDataList);
    }

    private void clearObservable(ObservableList<String> observableList) {
        if (!observableList.isEmpty()) {
            observableList.clear();
        }
    }

    @Override
    public void setData(Object data) {
        MovieData movieData = (MovieData) data;

        setMovie(movieData.getMovie());

        watchersDataList.addAll(movieData.getWatchers().stream().map(watcher -> watcher.getName() + " "
                + watcher.getSurname()).collect(Collectors.toList()));
        statusesDataList.addAll(movieData.getStatuses().stream().map(Status::getName).collect(Collectors.toList()));
    }

    @Override
    public void addObservables() {
        movieWatcherService.addObserver(this);
        movieStatusService.addObserver(this);
    }

    @Override
    public void update() {
        clearObservable(watchersDataList);
        clearObservable(statusesDataList);

        watchersDataList.addAll(movieService.getMovieWatchers(movie).stream().map(watcher -> watcher.getName() + " "
                + watcher.getSurname()).collect(Collectors.toList()));
        statusesDataList.addAll(movieService.getMovieStasuses(movie).stream().map(Status::getName).collect(Collectors.toList()));

        watchers.setItems(watchersDataList);
        statuses.setItems(statusesDataList);
    }

    @Override
    public void removeObservables() {
        movieWatcherService.removeObserver(this);
        movieStatusService.removeObserver(this);
    }

}

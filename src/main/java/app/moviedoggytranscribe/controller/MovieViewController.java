package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class MovieViewController implements DataController {
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

    public void setMovieData(MovieData movieData) {
        this.movieData = movieData;
    }

    private MovieData movieData;

    private ObservableList<String> watchersObservableList;
    private ObservableList<String> statusesObservableList;

    @PostConstruct
    public void init() {
        watchersObservableList = FXCollections.observableArrayList();
        statusesObservableList = FXCollections.observableArrayList();
    }

    @FXML
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
    }

    private void insertWatchersToListView() {
        clearObservable(watchersObservableList);

        watchersObservableList.addAll(movieData.getWatchers().stream().map(watcher -> watcher.getName() + " "
                + watcher.getSurname()).collect(Collectors.toList()));

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
    public void setData(Object data) {
        setMovieData((MovieData) data);
    }

    @Override
    public void update() {}

}

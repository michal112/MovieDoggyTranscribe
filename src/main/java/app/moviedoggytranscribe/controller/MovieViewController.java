package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MovieViewController implements Initializable {
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

    private MovieData movieData;

    private ObservableList<String> watchersObservableList = FXCollections.observableArrayList();
    private ObservableList<String> statusesObservableList = FXCollections.observableArrayList();

    public MovieViewController(MovieData movieData) {
        this.movieData = movieData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(movieData.getMovie().getTitle());
        type.setText(movieData.getMovie().getGenre());
        imageView.setImage(new Image(movieData.getMovie().getImageUrl()));
        year.setText("Rok produkcji: " + movieData.getMovie().getYear());
        describe.setText(movieData.getMovie().getDescription());
        rating.setText(movieData.getMovie().getRating());

        watchers.setEditable(false);
        statuses.setEditable(false);

        insertWatchersToListView();
        insertStatusesToListView();
    }

    private void insertWatchersToListView() {
        watchersObservableList.addAll(movieData.getWatchers().stream().map(watcher -> watcher.getName() + " "
                + watcher.getSurname()).collect(Collectors.toList()));
        watchers.setItems(watchersObservableList);
    }

    private void insertStatusesToListView() {
        statusesObservableList.addAll(movieData.getStatuses().stream().map(Status::getName).collect(Collectors.toList()));
        statuses.setItems(statusesObservableList);
    }
}

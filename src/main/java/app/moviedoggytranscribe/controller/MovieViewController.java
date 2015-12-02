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
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class MovieViewController implements Controller {
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

    private ObservableList<String> watchersObservableList = FXCollections.observableArrayList();
    private ObservableList<String> statusesObservableList = FXCollections.observableArrayList();

    public MovieViewController(MovieData selectedItem) {}

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

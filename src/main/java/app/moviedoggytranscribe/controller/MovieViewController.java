package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.data.MovieData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Text title;
    @FXML
    private Text type;
    @FXML
    private Text year;
    @FXML
    private Text rating;
    @FXML
    private Text describe;

    private MovieData movieData;

    public MovieViewController(MovieData movieData) {
        this.movieData = movieData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image(movieData.getMovie().getImageUrl()));
        title.setText(movieData.getMovie().getTitle());
        type.setText(movieData.getMovie().getGenre());
        year.setText(movieData.getMovie().getYear());
        rating.setText(movieData.getMovie().getRating());
        describe.setText(movieData.getMovie().getDescription());
    }
}

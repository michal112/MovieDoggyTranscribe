package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.mapper.ToMovieDataMapper;
import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@org.springframework.stereotype.Component
public class MainViewController {

    @FXML
    private TableView<MovieData> tableData;
    private ObservableList<MovieData> movieDataList;
    
    @Autowired
    private Service<Movie> movieService;

    @PostConstruct
    public void init() {
        movieDataList = FXCollections.observableArrayList();
        Mapper<MovieData> movieDataMapper = new ToMovieDataMapper();
        MovieData movieData = movieDataMapper.mapToData(movieService.getAll());
        movieDataList.add(movieData);
    }

    @FXML
    private void initialize() {
        tableData.setItems(movieDataList);
    }
}

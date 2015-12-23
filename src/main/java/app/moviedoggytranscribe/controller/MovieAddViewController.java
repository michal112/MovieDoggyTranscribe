package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.mapper.FilmMapper;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.model.data.FilmData;
import app.moviedoggytranscribe.model.data.YearData;
import info.talacha.filmweb.api.FilmWebApi;
import info.talacha.filmweb.api.UrlParameter;
import info.talacha.filmweb.models.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "prototype")
public class MovieAddViewController implements Controller {

    @FXML
    private TableView<FilmData> mainTable;
    @FXML
    private TableColumn<FilmData, String> titleColumn;
    @FXML
    private TableColumn<FilmData, Integer> yearColumn;
    @FXML
    private TextField movieTitle;
    @FXML
    private ChoiceBox<YearData> startYearChoiceBox;
    @FXML
    private ChoiceBox<YearData> endYearChoiceBox;
    @FXML
    private Button addMovie;

    @Autowired
    private FilmMapper filmMapper;

    private Integer startYear = null;
    private Integer endYear = null;

    private ObservableList<FilmData> filmDataList;
    private ObservableList<YearData> startYearDataList;
    private ObservableList<YearData> endYearDataList;

    @PostConstruct
    @Override
    public void init() {
        filmDataList = FXCollections.observableArrayList();
        startYearDataList = FXCollections.observableArrayList();
        endYearDataList = FXCollections.observableArrayList();
    }

    @FXML
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        initializeTable();

        startYearChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            startYear = newValue.getYear();
        });

        endYearChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            endYear = newValue.getYear();
        });

        movieTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 2) {
                FilmWebApi filmWebApi = new FilmWebApi();
                List<UrlParameter> urlParameterList = new ArrayList<>();

                if (endYear != null) {
                    UrlParameter endYearParamater = UrlParameter.END_YEAR;
                    endYearParamater.setValue(endYear.toString());
                    urlParameterList.add(endYearParamater);
                }

                if (startYear != null) {
                    UrlParameter startYearParameter = UrlParameter.START_YEAR;
                    startYearParameter.setValue(startYear.toString());
                    urlParameterList.add(startYearParameter);
                }

                if (!filmDataList.isEmpty()) {
                    filmDataList.clear();
                }
                
                filmDataList.addAll(filmMapper.mapToData(filmWebApi.getFilmList(newValue, urlParameterList)));
            }
        });


    }

    private void initializeTable() {
        mainTable.setItems(filmDataList);

        initChoiceBox(startYearDataList, startYearChoiceBox);
        initChoiceBox(endYearDataList, endYearChoiceBox);

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setCellFactory(cell -> new TableCell<FilmData, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });

        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        yearColumn.setCellFactory(cell -> new TableCell<FilmData, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }

    private void initChoiceBox(ObservableList<YearData> list, ChoiceBox<YearData> choiceBox) {
        YearData defaultYear = new YearData();
        defaultYear.setYear(null);
        list.add(defaultYear);
        for (int i = 2020; i > 1887; i--) {
            YearData yearData = new YearData();
            yearData.setYear(i);
            list.add(yearData);
        }

        choiceBox.setItems(list);
        choiceBox.setValue(defaultYear);
    }

}

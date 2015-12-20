package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.data.FilmData;
import app.moviedoggytranscribe.model.data.YearData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
    private ChoiceBox<YearData> yearChoiceBox;
    @FXML
    private Button addMovie;

    private ObservableList<FilmData> filmDataList;
    private ObservableList<YearData> yearDataList;

    @PostConstruct
    @Override
    public void init() {
        filmDataList = FXCollections.observableArrayList();
        yearDataList = FXCollections.observableArrayList();
    }

    @FXML
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        initializeTable();
    }

    private void initializeTable() {
        mainTable.setItems(filmDataList);

        YearData defaultYear = new YearData();
        defaultYear.setYear(null);
        yearDataList.add(defaultYear);
        for (int i = 2020; i > 1887; i--) {
            YearData yearData = new YearData();
            yearData.setYear(i);
            yearDataList.add(yearData);
        }

        yearChoiceBox.setItems(yearDataList);
        yearChoiceBox.setValue(defaultYear);

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

}

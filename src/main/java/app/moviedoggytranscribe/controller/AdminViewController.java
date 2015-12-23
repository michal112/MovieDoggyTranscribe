package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.ApplicationCore;
import app.moviedoggytranscribe.FxmlElement;
import app.moviedoggytranscribe.SpringFxmlLoader;
import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import app.moviedoggytranscribe.mapper.Mapper;
import app.moviedoggytranscribe.model.data.StatusData;
import app.moviedoggytranscribe.model.data.WatcherData;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.SimpleStatusService;
import app.moviedoggytranscribe.service.SimpleWatcherService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Optional;
import java.util.logging.Logger;


@Component
@Scope(value = "prototype")
public class AdminViewController implements ControllerObserver {
    @FXML
    private Button addWatcher;
    @FXML
    private Button deleteWatcher;
    @FXML
    private Button addStatus;
    @FXML
    private Button deleteStatus;

    @FXML
    private TableView<WatcherData> mainWatcherTable;
    @FXML
    private TableColumn<WatcherData, Watcher> nameColumn;
    @FXML
    private TableColumn<WatcherData, Watcher> surnameColumn;
    @FXML
    private TableColumn<WatcherData, Watcher> nickColumn;
    @FXML
    private TableView<StatusData> mainStatusTable;
    @FXML
    private TableColumn<StatusData, Status> titleColumn;
    @FXML
    private TableColumn<StatusData, Status> colorColumn;

    @Autowired
    private Mapper<Watcher, WatcherData> watcherDataMapper;
    @Autowired
    private SimpleWatcherService watcherService;
    @Autowired
    private Mapper<Status, StatusData>  statusDataMapper;
    @Autowired
    private SimpleStatusService statusService;

    private ObservableList<WatcherData> watcherDataList;
    private ObservableList<StatusData> statusDataList;

    @PostConstruct
    public void init() {
        watcherDataList = FXCollections.observableArrayList();
        statusDataList = FXCollections.observableArrayList();

        addObservables();
    }

    @FXML
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        initializeTables();

        // mouseEvent - click on AddWatcher button
        addWatcher.setOnAction((event) -> {
            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, AdminWatcherViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + ViewConstants.ADMIN_VIEW_ADD_WATCHER_FILE_NAME, AdminWatcherViewController.class);

            ApplicationCore.getInstance().displayFxmlElement(fxmlElement, ViewConstants.ADMIN_WATCHERS_VIEW_TITLE, 400, 400);
        });

        // mouseEvent - click on DeleteWatcher button

        deleteWatcher.setOnAction((event) -> {
            WatcherData selectionWatcher = mainWatcherTable.getSelectionModel().getSelectedItem();
            if(selectionWatcher == null) {
                return;
            }

            try {
                Optional<ButtonType> result = displayAlert("Usuń oglądającego", "Czy chcesz usunąć oglądającego z bazy danych?");

                if (result.get() == ButtonType.OK){
                    watcherService.delete(selectionWatcher.getWatcher().getId());
                }
            } catch (NoSuchWatcherException e) {
                Logger.getLogger(MainViewController.class.getCanonicalName()).severe("Watcher already deleted");
            }
        });


        // mouseEvent - click on AddStatus button
        addStatus.setOnAction((event) -> {
            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, AdminStatusViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + ViewConstants.ADMIN_VIEW_ADD_STATUS_FILE_NAME, AdminStatusViewController.class);

            ApplicationCore.getInstance().displayFxmlElement(fxmlElement, ViewConstants.ADMIN_STATUSES_VIEW_TITLE, 400, 400);
        });

        // mouseEvent - click on DeleteStatus button

        deleteStatus.setOnAction((event) -> {
            StatusData selectionStatus = mainStatusTable.getSelectionModel().getSelectedItem();
            if(selectionStatus == null) {
                return;
            }

            try {
                Optional<ButtonType> result = displayAlert("Usuń status", "Czy chcesz usunąć status z bazy danych?");

                if (result.get() == ButtonType.OK){
                    statusService.delete(selectionStatus.getStatus().getId());
                }
            } catch (NoSuchStatusException e) {
                Logger.getLogger(MainViewController.class.getCanonicalName()).severe("Status already deleted");
            }
        });
    }

    private void initializeTables() {
        initializeWatchersTable();
        initializeStatusesTable();

        mainWatcherTable.getSelectionModel().select(0);
        mainStatusTable.getSelectionModel().select(0);
    }

    private void initializeWatchersTable() {
        watcherDataList.addAll(watcherDataMapper.mapToData(watcherService.getAll()));

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().watcherProperty());
        nameColumn.setCellFactory(cell -> new TableCell<WatcherData, Watcher>() {
            @Override
            protected void updateItem(Watcher item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().watcherProperty());
        surnameColumn.setCellFactory(cell -> new TableCell<WatcherData, Watcher>() {
            @Override
            protected void updateItem(Watcher item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getSurname());
                }
            }
        });

        nickColumn.setCellValueFactory(cellData -> cellData.getValue().watcherProperty());
        nickColumn.setCellFactory(cell -> new TableCell<WatcherData, Watcher>() {
            @Override
            protected void updateItem(Watcher item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNick());
                }
            }
        });

        mainWatcherTable.setItems(watcherDataList);
    }

    private void initializeStatusesTable() {
        statusDataList.addAll(statusDataMapper.mapToData(statusService.getAll()));

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        titleColumn.setCellFactory(cell -> new TableCell<StatusData, Status>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        colorColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        colorColumn.setCellFactory(cell -> new TableCell<StatusData, Status>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    Paint fill = Paint.valueOf(item.getColour());
                    BackgroundFill backgroundFill = new BackgroundFill(fill,
                            CornerRadii.EMPTY,
                            Insets.EMPTY);
                    Background background = new Background(backgroundFill);
                    setBackground(background);
                }
            }
        });

        mainStatusTable.setItems(statusDataList);
    }

    private void refreshData() {
        if (!watcherDataList.isEmpty()) {
            watcherDataList.clear();
        }

        if (!statusDataList.isEmpty()) {
            statusDataList.clear();
        }

        watcherDataList.addAll(watcherDataMapper.mapToData(watcherService.getAll()));
        statusDataList.addAll(statusDataMapper.mapToData(statusService.getAll()));

        mainWatcherTable.refresh();
        mainStatusTable.refresh();
        mainWatcherTable.getSelectionModel().select(0);
        mainStatusTable.getSelectionModel().select(0);
    }

    public Optional<ButtonType> displayAlert(String title, String header){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);

        return alert.showAndWait();
    }

    @Override
    public void addObservables() {
        statusService.addObserver(this);
        watcherService.addObserver(this);
    }

    @Override
    public void update() {
        refreshData();
    }

    @Override
    public void removeObservables() {
        statusService.removeObserver(this);
        watcherService.removeObserver(this);
    }

}

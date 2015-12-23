package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.ApplicationCore;
import app.moviedoggytranscribe.FxmlElement;
import app.moviedoggytranscribe.SpringFxmlLoader;
import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;


@Component
@Scope(value = "prototype")
public class AdminViewController implements DataController {
    @FXML
    private Button addWatcher;
    @FXML
    private Button deleteWatcher;
    @FXML
    private Button addStatus;
    @FXML
    private Button deleteStatus;

    @Override
    public void setData(Object data) {

    }

    @FXML
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {

        // mouseEvent - click on AddWatcher button
        addWatcher.setOnAction((event) -> {
            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, MovieAddViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + ViewConstants.ADMIN_VIEW_ADD_WATCHER_FILE_NAME, AdminWatcherViewController.class);

            Scene scene = new Scene(fxmlElement.getRoot(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle(ViewConstants.ADMIN_WATCHERS_VIEW_TITLE);
            stage.setScene(scene);
            stage.show();
        });

        // mouseEvent - click on AddStatus button
        addStatus.setOnAction((event) -> {
            SpringFxmlLoader loader = ApplicationCore.getLoader();
            FxmlElement<AnchorPane, MovieAddViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                    + File.separator + ViewConstants.ADMIN_VIEW_ADD_STATUS_FILE_NAME, AdminStatusViewController.class);

            Scene scene = new Scene(fxmlElement.getRoot(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle(ViewConstants.ADMIN_STATUSES_VIEW_TITLE);
            stage.setScene(scene);
            stage.show();
        });

    }

    @Override
    public void init() {

    }

    @Override
    public void addObservables() {

    }

    @Override
    public void update() {

    }

    @Override
    public void removeObservables() {

    }
}

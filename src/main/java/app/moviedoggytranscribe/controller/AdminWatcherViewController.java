package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.entity.Watcher;
import app.moviedoggytranscribe.service.WatcherService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Component
@Scope(value = "prototype")
public class AdminWatcherViewController implements DataController{

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField nick;
    @FXML
    private Button addWatcher;
    @FXML
    private Button cancelWatcher;

    @Autowired
    private WatcherService watcherService;

    @PostConstruct
    public void init() {
        watcherService.addObserver(this);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void initialize() {

        // mouseEvent - click on AddWatcher button

        addWatcher.setOnAction((event) -> {

            if(name.getText().isEmpty() || surname.getText().isEmpty() || nick.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("BŁĄD");
                alert.setHeaderText("Przed zapisaniem wypełnij wszystkie pola!");
                alert.showAndWait();

                throw new NoSuchElementException("Puste miejsca");
            } else {
                Watcher watcher = new Watcher();
                watcher.setName(name.getText());
                watcher.setSurname(surname.getText());
                watcher.setNick(nick.getText());

                watcherService.add(watcher);

                Stage stage = (Stage) addWatcher.getScene().getWindow();
                stage.close();
            }
        });

        cancelWatcher.setOnAction((event) -> {
            Stage stage = (Stage) cancelWatcher.getScene().getWindow();
            stage.close();
        });
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

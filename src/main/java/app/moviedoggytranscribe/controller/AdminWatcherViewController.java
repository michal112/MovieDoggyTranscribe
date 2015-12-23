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
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(value = "prototype")
public class AdminWatcherViewController implements ControllerObserver {

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

    private List<Watcher> watchers;

    @PostConstruct
    public void init() {
        watchers = watcherService.getAll();

        addObservables();
    }

    @Override
    public void initialize() {

        // mouseEvent - click on AddWatcher button

        addWatcher.setOnAction((event) -> {

            if (name.getText().isEmpty()) {
                showAlert("Przed zapisaniem wypełnij wszystkie pola!", "Brak imienia użytkownika");
                return;
            } else if (surname.getText().isEmpty()) {
                showAlert("Przed zapisaniem wypełnij wszystkie pola!", "Brak nazwiska użytkownika");
                return;
            } else if (nick.getText().isEmpty()) {
                showAlert("Przed zapisaniem wypełnij wszystkie pola!", "Brak wybranego nicku dla użytkownika");
                return;
            } else if (watchers.stream().map(watcher -> watcher.getNick()).collect(Collectors.toList()).contains(nick.getText())) {
                showAlert("Nieprawidłowy nick dodawanego użytkownika!", "Podany nick już istnieje");
                return;
            }

            Watcher watcher = new Watcher();
            watcher.setName(name.getText());
            watcher.setSurname(surname.getText());
            watcher.setNick(nick.getText());

            watcherService.add(watcher);

            Stage stage = (Stage) addWatcher.getScene().getWindow();
            stage.close();
        });

        cancelWatcher.setOnAction((event) -> {
            Stage stage = (Stage) cancelWatcher.getScene().getWindow();
            stage.close();
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("BŁĄD");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void addObservables() {
        watcherService.addObserver(this);
    }

    @Override
    public void update() {
        watchers = watcherService.getAll();
    }

    @Override
    public void removeObservables() {
        watcherService.removeObserver(this);
    }

}

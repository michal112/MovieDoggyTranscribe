package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.service.SimpleStatusService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
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
public class AdminStatusViewController implements ControllerObserver {

    @FXML
    private TextField name;
    @FXML
    private ColorPicker color;
    @FXML
    private Button addStatus;
    @FXML
    private Button cancelStatus;

    @Autowired
    private SimpleStatusService simpleStatusService;

    private List<Status> statuses;

    @PostConstruct
    public void init() {
        statuses = simpleStatusService.getAll();

        addObservables();
    }

    @Override
    public void initialize() {

        // mouseEvent - click on AddStatus button

        addStatus.setOnAction((event) -> {

            if (name.getText().isEmpty()) {
                showAlert("Przed zapisaniem wypełnij wszystkie pola!", "Brak nazwy statusu");
                return;
            } else if (statuses.stream().map(status -> status.getName()).collect(Collectors.toList()).contains(name.getText())) {
                showAlert("Nieprawidłowa nazwa statusu!", "Status o tej nazwie już istnieje");
                return;
            } else if (statuses.stream().map(status -> status.getColour()).collect(Collectors.toList()).contains(color.getValue().toString())) {
                showAlert("Nieprawidłowy kolor statusu!", "Status o wybranym kolorze już istnieje");
                return;
            }

            Status status = new Status();
            status.setName(name.getText());
            status.setColour(color.getValue().toString());

            simpleStatusService.add(status);

            Stage stage = (Stage) addStatus.getScene().getWindow();
            stage.close();
        });

        // mouseEvent - click on CancelStatus button

        cancelStatus.setOnAction((event) -> {
            Stage stage = (Stage) cancelStatus.getScene().getWindow();
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
        simpleStatusService.addObserver(this);
    }

    @Override
    public void update() {
        statuses = simpleStatusService.getAll();
    }

    @Override
    public void removeObservables() {
        simpleStatusService.removeObserver(this);
    }

}

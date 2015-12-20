package app.moviedoggytranscribe.controller;

import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.service.StatusService;
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
import java.util.NoSuchElementException;

@Component
@Scope(value = "prototype")
public class AdminStatusViewController implements DataController {

    @FXML
    private TextField name;
    @FXML
    private ColorPicker color;
    @FXML
    private Button addStatus;
    @FXML
    private Button cancelStatus;

    @Autowired
    private StatusService statusService;

    @PostConstruct
    public void init() {
        statusService.addObserver(this);
    }

    @Override
    public void setData(Object data) {
    }

    @Override
    public void initialize() {

        // mouseEvent - click on AddStatus button

        addStatus.setOnAction((event) -> {

            if(name.getText().isEmpty() || color.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("BŁĄD");
                alert.setHeaderText("Przed zapisaniem wypełnij wszystkie pola!");
                alert.showAndWait();

                throw new NoSuchElementException();
            }

            Status status = new Status();
            status.setName(name.getText());
            status.setColour(color.getValue().toString());

            statusService.add(status);

            Stage stage = (Stage) addStatus.getScene().getWindow();
            stage.close();
        });

        // mouseEvent - click on CancelStatus button

        cancelStatus.setOnAction((event) -> {
            Stage stage = (Stage) cancelStatus.getScene().getWindow();
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

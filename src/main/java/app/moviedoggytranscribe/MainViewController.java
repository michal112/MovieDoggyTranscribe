package app.moviedoggytranscribe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private TextField searchField;

    public void initialize(URL arg0, ResourceBundle arg1) {
        searchField.setText("Witam serdecznie kurwa");
    }
}

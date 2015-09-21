package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(File.separator + AppConstants.VIEWS_FOLDER_NAME
                + File.separator + ViewConstants.MAIN_VIEW_FILE_NAME));
        primaryStage.setTitle(ViewConstants.MAIN_VIEW_TITLE);
        primaryStage.setScene(new Scene(root, 857, 560));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

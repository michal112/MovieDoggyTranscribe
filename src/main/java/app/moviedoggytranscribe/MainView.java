package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.controller.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class MainView extends Application {

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage primaryStage)  {
        SpringFxmlLoader loader = ApplicationCore.getLoader();
        FxmlElement<VBox, MainViewController> fxmlElement = loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                + File.separator + ViewConstants.MAIN_VIEW_FILE_NAME, MainViewController.class);

        primaryStage.setTitle(ViewConstants.MAIN_VIEW_TITLE);
        primaryStage.setScene(new Scene(fxmlElement.getRoot(), 1000, 570));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        ApplicationCore.setContext(new ClassPathXmlApplicationContext(AppConstants.APPLICATION_CONTEXT_XML));

        launch(args);
    }
}

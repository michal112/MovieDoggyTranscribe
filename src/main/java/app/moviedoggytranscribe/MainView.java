package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.controller.MainViewController;
import javafx.application.Application;
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

        ApplicationCore.getInstance().displayFxmlElement(fxmlElement, ViewConstants.MAIN_VIEW_TITLE, 570, 1000);
    }


    public static void main(String[] args) {
        ApplicationCore.getInstance().setContext(new ClassPathXmlApplicationContext(AppConstants.APPLICATION_CONTEXT_XML));

        launch(args);
    }
}

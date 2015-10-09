package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.controller.MainViewController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SpringFxmlLoader loader = new SpringFxmlLoader(ApplicationCore.getContext());
        Parent root = (Parent) loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                + File.separator + ViewConstants.MAIN_VIEW_FILE_NAME, MainViewController.class);
        primaryStage.setTitle(ViewConstants.MAIN_VIEW_TITLE);
        primaryStage.setScene(new Scene(root, ViewConstants.APP_WINDOW_WIDTH, ViewConstants.APP_WINDOW_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        ApplicationCore.setContext(new ClassPathXmlApplicationContext(AppConstants.APPLICATION_CONTEXT_XML));

        launch(args);
    }
}

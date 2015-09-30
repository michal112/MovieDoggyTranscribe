package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.model.dao.MovieDao;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Main extends Application {

    @Autowired
    @Qualifier("movieService")
    Service<Movie> movieService;

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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(AppConstants.APPLICATION_CONTEXT_XML);
        Main mainBean = applicationContext.getBean(Main.class);
        mainBean.movieService.add(new Movie("title", "description", "year", "imageUrl", "movieUrl", "genre", "rating"));
        launch(args);
    }
}

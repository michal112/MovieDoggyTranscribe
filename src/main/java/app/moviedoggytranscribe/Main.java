package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.constants.ViewConstants;
import app.moviedoggytranscribe.controller.MainViewController;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
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
    private Service<Movie, NoSuchMovieException> movieService;
    private static ApplicationContext applicationContext;
    @Override
    public void start(Stage primaryStage) throws Exception {
        SpringFxmlLoader loader = new SpringFxmlLoader(applicationContext);
        Parent root = (Parent) loader.load(File.separator + AppConstants.VIEWS_FOLDER_NAME
                + File.separator + ViewConstants.MAIN_VIEW_FILE_NAME, MainViewController.class);
        primaryStage.setTitle(ViewConstants.MAIN_VIEW_TITLE);
        primaryStage.setScene(new Scene(root, ViewConstants.APP_WINDOW_WIDTH, 560));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext(AppConstants.APPLICATION_CONTEXT_XML);
        Main mainBean = applicationContext.getBean(Main.class);
        mainBean.movieService.add(new Movie("Ojciec chrzestny",
                "Opowieść o nowojorskiej rodzinie mafijnej",
                "http://1.fwcdn.pl/po/10/89/1089/7196615.3.jpg",
                "http://www.filmweb.pl/Ojciec.Chrzestny",
                "Dramat, Gangsterski",
                "1972",
                "8.7"));
        launch(args);
    }
}

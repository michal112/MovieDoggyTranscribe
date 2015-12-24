package app.moviedoggytranscribe;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.controller.Controller;
import app.moviedoggytranscribe.controller.DataController;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class SpringFxmlLoader extends FXMLLoader{

    private static final SpringFxmlLoader springFxmlLoader = new SpringFxmlLoader();

    private static ApplicationContext context;

    private SpringFxmlLoader() {}

    public void setApplicationContext(ApplicationContext context) {
        SpringFxmlLoader.context = context;
    }

    public static SpringFxmlLoader getInstance()  {
        return springFxmlLoader;
    }

    public <T extends Controller> FxmlElement load(String resourcePath, Class<T> controllerClass) {
        return load(resourcePath, controllerClass, null);
    }

    public <T extends Controller> FxmlElement load(String resourcePath, Class<T> controllerClass, Object data) {
        try {
            T controller = context.getBean(controllerClass);

            URL url = new URL(controllerClass.getClassLoader().getResource(resourcePath).toString());
            FXMLLoader loader = new FXMLLoader(url);

            loader.setController(controller);

            if (data != null && DataController.class.isAssignableFrom(controllerClass)) {
                ((DataController) controller).setData(data);
            }

            loader.setRoot(loader.load());

            return new FxmlElement(loader.getController(), loader.getRoot());
        } catch (IOException e) {e.printStackTrace();
            Logger.getAnonymousLogger().severe("An error occurred while loading .fxml file");

            return null;
        }
    }
}
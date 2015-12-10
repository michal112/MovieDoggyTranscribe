package app.moviedoggytranscribe;

import app.moviedoggytranscribe.controller.Controller;
import app.moviedoggytranscribe.controller.DataController;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.logging.Logger;

public class SpringFxmlLoader extends FXMLLoader{

    private final static SpringFxmlLoader LOADER = new SpringFxmlLoader();

    private ApplicationContext context;

    private SpringFxmlLoader() {}

    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    public static SpringFxmlLoader getInstance()  {
        return LOADER;
    }

    public <T extends Controller> FxmlElement load(String resourcePath, Class<T> controllerClass) {
        return load(resourcePath, controllerClass, null);
    }

    public <T extends Controller> FxmlElement load(String resourcePath, Class<T> controllerClass, Object data) {
        try {
            T controller = context.getBean(controllerClass);

            FXMLLoader loader = new FXMLLoader(controllerClass.getResource(resourcePath));
            loader.setController(controller);

            if (data != null && DataController.class.isAssignableFrom(controllerClass)) {
                ((DataController) controller).setData(data);
            }

            loader.setRoot(loader.load());

            return new FxmlElement(loader.getController(), loader.getRoot());
        } catch (IOException e) {
            Logger.getAnonymousLogger().severe("An error occurred while loading .fxml file");

            return null;
        }
    }
}
package app.moviedoggytranscribe;

import app.moviedoggytranscribe.controller.Controller;
import app.moviedoggytranscribe.controller.MovieEditViewController;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.logging.Logger;

public class SpringFxmlLoader {

    private final static SpringFxmlLoader LOADER = new SpringFxmlLoader();

    private ApplicationContext context;

    private SpringFxmlLoader() {}

    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    public static SpringFxmlLoader getInstance()  {
        return LOADER;
    }

    @SuppressWarnings("unchecked")
    public <T extends Controller> FxmlElement load(String resourcePath, Class<T> controllerClass) {
        try {
            T controller = context.getBean(controllerClass);

            FXMLLoader loader = new FXMLLoader(controllerClass.getResource(resourcePath));
            loader.setController(controller);
            loader.setRoot(loader.load());

            return new FxmlElement(loader.getController(), loader.getRoot());
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().severe(e.getMessage());
            return null;
        }
    }

    public <T extends Controller> FxmlElement load(String resourcePath, Class<T> controllerClass, Object data) {
        FxmlElement fxmlElement = load(resourcePath, controllerClass);

        fxmlElement.getController().setData(data);

        return fxmlElement;
    }

}
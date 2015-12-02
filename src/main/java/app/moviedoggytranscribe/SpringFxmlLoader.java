package app.moviedoggytranscribe;

import app.moviedoggytranscribe.controller.Controller;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

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
    public <T extends Controller> FxmlElement load(String url, Class<T> controllerClass) {
        try (InputStream fxmlStream = controllerClass.getResourceAsStream(url)) {
            Object controller = context.getBean(controllerClass);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            loader.setRoot(loader.load(fxmlStream));

            FxmlElement<T> fxmlElement = new FxmlElement(loader.getController(), loader.getRoot());

            return fxmlElement;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
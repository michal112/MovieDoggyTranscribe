package app.moviedoggytranscribe;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFxmlLoader {

    private final ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public Object load(String url, Class<?> controllerClass) throws IOException {
        try (InputStream fxmlStream = controllerClass.getResourceAsStream(url)) {
            Object controller = context.getBean(controllerClass);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            return loader.load(fxmlStream);
        }
    }

}
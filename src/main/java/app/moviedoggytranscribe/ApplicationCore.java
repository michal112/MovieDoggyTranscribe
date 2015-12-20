package app.moviedoggytranscribe;

import app.moviedoggytranscribe.controller.ControllerObserver;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public class ApplicationCore {

    private static final ApplicationCore applicationCore = new ApplicationCore();

    private ApplicationCore() {}

    private static SpringFxmlLoader springFxmlLoader = SpringFxmlLoader.getInstance();

    public void setContext(ApplicationContext context) {
        springFxmlLoader.setApplicationContext(context);
    }

    public static ApplicationCore getInstance() {
        return applicationCore;
    }

    public static SpringFxmlLoader getLoader() {
        return springFxmlLoader;
    }

    public void displayFxmlElement(FxmlElement fxmlElement, String windowTitle, int windowHeight, int windowWidth) {
        Scene scene = new Scene(fxmlElement.getRoot(), windowWidth, windowHeight);

        Stage stage = new Stage();
        stage.setResizable(Boolean.FALSE);
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.show();

        if (ControllerObserver.class.isAssignableFrom(fxmlElement.getController().getClass())) {
            stage.setOnCloseRequest(event -> {
                ((ControllerObserver) fxmlElement.getController()).removeObservables();
            });
        }
    }

}

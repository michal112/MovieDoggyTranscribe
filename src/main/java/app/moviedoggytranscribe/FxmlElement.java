package app.moviedoggytranscribe;

import app.moviedoggytranscribe.controller.Controller;
import javafx.scene.Parent;

public class FxmlElement<T extends Controller> {

    private final T controller;

    private final Parent root;

    public T getController() {
        return controller;
    }

    public Parent getRoot() {
        return root;
    }

    public FxmlElement(T controller, Parent root) {
        this.controller = controller;
        this.root = root;
    }
}

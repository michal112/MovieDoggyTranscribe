package app.moviedoggytranscribe;

import app.moviedoggytranscribe.controller.Controller;
import javafx.scene.Parent;

public class FxmlElement<R extends Parent, C extends Controller> {

    private final C controller;

    private final R root;

    public C getController() {
        return controller;
    }

    public R getRoot() {
        return root;
    }

    public FxmlElement(C controller, R root) {
        this.controller = controller;
        this.root = root;
    }
}

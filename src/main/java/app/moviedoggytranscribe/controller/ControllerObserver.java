package app.moviedoggytranscribe.controller;

public interface ControllerObserver extends Controller {

    void addObservables();
    void update();
    void removeObservables();

}

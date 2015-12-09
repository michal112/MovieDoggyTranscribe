package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.controller.ControllerObserver;

public interface ObservableService {

    void addObserver(ControllerObserver controllerObserver);
    void notifyObservers();

}

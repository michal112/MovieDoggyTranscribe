package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.controller.ControllerObserver;

public interface ServiceObservable {

    void addObserver(ControllerObserver controllerObserver);
    void notifyObservers();
    void removeObserver(ControllerObserver controllerObserver);

}

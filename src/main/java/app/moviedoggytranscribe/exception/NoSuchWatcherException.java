package app.moviedoggytranscribe.exception;

public class NoSuchWatcherException extends NoSuchEntityException {

    public NoSuchWatcherException(Integer id) {
        super("watcher with id: " + id + ", not exist");
    }

}

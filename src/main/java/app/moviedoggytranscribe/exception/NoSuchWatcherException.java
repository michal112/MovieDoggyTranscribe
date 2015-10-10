package app.moviedoggytranscribe.exception;

public class NoSuchWatcherException extends NoSuchEntityException {

    public NoSuchWatcherException(Integer id) {
        super("oglądający o id: " + id + ", nie istnieje");
    }

}

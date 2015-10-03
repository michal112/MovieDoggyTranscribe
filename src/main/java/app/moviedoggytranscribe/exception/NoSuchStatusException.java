package app.moviedoggytranscribe.exception;

public class NoSuchStatusException extends NoSuchEntityException {

    public NoSuchStatusException(Integer id) {
        super("status o id: " + id + ", nie istnieje");
    }

}

package app.moviedoggytranscribe.exception;

public class NoSuchStatusException extends NoSuchEntityException {

    public NoSuchStatusException(Integer id) {
        super("status with id: " + id + ", not exist");
    }

}

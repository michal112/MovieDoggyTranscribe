package app.moviedoggytranscribe.exception;

public class NoSuchEntityException extends Exception {

    private final String message;

    protected NoSuchEntityException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

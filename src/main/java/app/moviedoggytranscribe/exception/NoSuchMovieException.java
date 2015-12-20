package app.moviedoggytranscribe.exception;

public class NoSuchMovieException extends NoSuchEntityException {

    public NoSuchMovieException(Integer id) {
        super("movie with id: " + id + ", not exist");
    }

}

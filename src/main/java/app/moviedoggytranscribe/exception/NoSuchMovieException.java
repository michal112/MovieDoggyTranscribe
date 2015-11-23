package app.moviedoggytranscribe.exception;

public class NoSuchMovieException extends NoSuchEntityException {

    public NoSuchMovieException(Integer id) {
        super("film o id: " + id + ", nie istnieje");
    }

}

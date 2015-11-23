package app.moviedoggytranscribe.exception.factory;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import org.springframework.stereotype.Component;

@Component
public class MovieExceptionFactory implements ExceptionFactory<NoSuchMovieException> {

    @Override
    public NoSuchMovieException getNoSuchEntityException(Integer id) {
        return new NoSuchMovieException(id);
    }

}

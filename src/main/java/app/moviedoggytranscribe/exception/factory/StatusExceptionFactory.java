package app.moviedoggytranscribe.exception.factory;

import app.moviedoggytranscribe.exception.NoSuchStatusException;
import org.springframework.stereotype.Component;

@Component
public class StatusExceptionFactory implements ExceptionFactory<NoSuchStatusException> {

    @Override
    public NoSuchStatusException getNoSuchEntityException(Integer id) {
        return new NoSuchStatusException(id);
    }

}

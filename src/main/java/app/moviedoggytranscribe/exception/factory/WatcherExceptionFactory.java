package app.moviedoggytranscribe.exception.factory;

import app.moviedoggytranscribe.exception.NoSuchWatcherException;
import org.springframework.stereotype.Component;

@Component
public class WatcherExceptionFactory implements ExceptionFactory<NoSuchWatcherException> {

    @Override
    public NoSuchWatcherException getNoSuchEntityException(Integer id) {
        return new NoSuchWatcherException(id);
    }

}

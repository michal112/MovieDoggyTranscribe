package app.moviedoggytranscribe.exception.factory;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class ConnectionExceptionFactory implements ExceptionFactory<NoSuchConnectionException> {

    private Class entityClass;

    @Override
    public NoSuchConnectionException getNoSuchEntityException(Integer id) {
        return new NoSuchConnectionException("connection with id " + id + "," +
                " not exist (" + entityClass.getCanonicalName() + ")");
    }

    @Override
    public void setServiceClass(Class entityClass) {
        this.entityClass = entityClass;
    }

}

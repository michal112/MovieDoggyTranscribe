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
        return new NoSuchConnectionException("połączenie o id " + id + "," +
                " nie istnieje (" + entityClass.getCanonicalName() + ")");
    }

    @Override
    public Class getEntityClass() {
        return entityClass;
    }

    @Override
    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

}

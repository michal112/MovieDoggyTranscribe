package app.moviedoggytranscribe.exception.factory;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.model.entity.Entity;

public interface ExceptionFactory<E extends NoSuchEntityException> {

    E getNoSuchEntityException(Integer id);
    default void setEntityClass(Class clazz) {}
    default Class getEntityClass() {
        return Entity.class;
    }

}

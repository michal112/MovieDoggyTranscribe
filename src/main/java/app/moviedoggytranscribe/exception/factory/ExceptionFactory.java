package app.moviedoggytranscribe.exception.factory;

import app.moviedoggytranscribe.exception.NoSuchEntityException;

public interface ExceptionFactory<E extends NoSuchEntityException> {

    E getNoSuchEntityException(Integer id);
    default void setServiceClass(Class clazz) {}

}

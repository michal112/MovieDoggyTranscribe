package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.exception.factory.ExceptionFactory;
import app.moviedoggytranscribe.model.dao.Dao;
import app.moviedoggytranscribe.model.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public abstract class AbstractService<T extends Entity, E extends NoSuchEntityException> implements Service<T, E> {

    @Autowired
    private Dao<T> dao;
    @Autowired
    private ExceptionFactory<E> exceptionFactory;

    public List<T> entities;

    protected AbstractService() {
        this.entities = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        exceptionFactory.setEntityClass(getClass());
    }

    @Override
    public List<T> getAll() {
        initEntities();
        return this.entities;
    }

    @Override
    public T get(Integer id) throws E {
        initEntities();
        if (entities.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return entities.stream().filter(entity -> entity.getId().equals(id)).collect(Collectors.toList()).get(0);
        } else {
            throw exceptionFactory.getNoSuchEntityException(id);
        }
    }

    @Override
    public Integer add(T entity) {
        Integer entityId = dao.add(entity);
        entities.add(entity);
        return entityId;
    }

    @Override
    public void delete(Integer id) throws E {
        entities.remove(get(id));
        dao.delete(id);
    }

    @Override
    public void update(T entity) throws E {
        initEntities();
        if (entities.stream().anyMatch(e -> e.getId().equals(entity.getId()))) {
            Entity en = entities.stream().filter(e -> e.getId().equals(entity.getId()))
                    .collect(Collectors.toList()).get(0);
            entities.remove(en);
            entities.add(entity);
            dao.update(entity);
        } else {
            throw exceptionFactory.getNoSuchEntityException(entity.getId());
        }
    }

    @Override
    public void clearEntities() {
        this.entities.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        clearEntities();
    }

    protected void initEntities() {
        if (this.entities.isEmpty()) {
            this.entities = dao.getAll();
        }
    }

}

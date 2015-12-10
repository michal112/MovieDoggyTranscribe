package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.controller.ControllerObserver;
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
public abstract class AbstractService<T extends Entity, E extends NoSuchEntityException>
        implements Service<T, E> {

    @Autowired
    private Dao<T> dao;
    @Autowired
    private ExceptionFactory<E> exceptionFactory;

    private List<ControllerObserver> observers;

    protected List<T> rows;

    protected AbstractService() {
        this.observers = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        exceptionFactory.setServiceClass(getClass());
    }

    @Override
    public Dao<T> getDao() {
        return dao;
    }

    @Override
    public List<T> getAll() {
        initEntity();
        return this.rows;
    }

    @Override
    public T get(Integer id) throws E {
        initEntity();
        if (rows.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return rows.stream().filter(entity -> entity.getId().equals(id)).collect(Collectors.toList()).get(0);
        } else {
            throw exceptionFactory.getNoSuchEntityException(id);
        }
    }

    @Override
    public Integer add(T entity) {
        initEntity();
        Integer entityId = dao.add(entity);
        rows.add(entity);

        notifyObservers();

        return entityId;
    }

    @Override
    public void delete(Integer id) throws E {
        initEntity();
        rows.remove(get(id));
        dao.delete(id);

        notifyObservers();
    }

    @Override
    public void update(T entity) throws E {
        initEntity();
        if (rows.stream().anyMatch(e -> e.getId().equals(entity.getId()))) {
            Entity en = rows.stream().filter(e -> e.getId().equals(entity.getId()))
                    .collect(Collectors.toList()).get(0);
            rows.remove(en);
            rows.add(entity);
            dao.update(entity);

            notifyObservers();
        } else {
            throw exceptionFactory.getNoSuchEntityException(entity.getId());
        }
    }

    @Override
    public void clearEntities() {
        this.rows.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        clearEntities();
    }

    protected void initEntity() {
        if (this.rows.isEmpty()) {
            this.rows = dao.getAll();
        }
    }

    @Override
    public void addObserver(ControllerObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(ControllerObserver::update);
    }

}

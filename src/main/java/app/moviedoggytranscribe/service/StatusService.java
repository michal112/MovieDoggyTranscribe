package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.dao.Dao;
import app.moviedoggytranscribe.model.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StatusService implements Service<Status> {

    @Autowired
    private Dao<Status> statusDao;

    private List<Status> statuses;

    public StatusService() {
        this.statuses = new ArrayList<>();
    }

    @Override
    public void clearData() {
        this.statuses.clear();
    }

    @Override
    public List<Status> getAll() {
        initStatuses();
        return statuses;
    }

    @Override
    public Status get(Integer id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public Integer add(Status entity) {
        return null;
    }

    @Override
    public void delete(Integer id) throws NoSuchMovieException {

    }

    @Override
    public void update(Status entity) throws NoSuchMovieException {

    }

    private void initStatuses() {
        if (this.statuses.isEmpty()) {
            statuses = statusDao.getAll();
        }
    }

}

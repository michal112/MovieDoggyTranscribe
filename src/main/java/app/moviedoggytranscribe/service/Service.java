package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.model.dao.Dao;
import app.moviedoggytranscribe.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    @Autowired
    Dao<Movie> movieDao;


}

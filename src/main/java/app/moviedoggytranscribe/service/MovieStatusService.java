package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchConnectionException;
import app.moviedoggytranscribe.model.entity.MovieStatus;

@org.springframework.stereotype.Service
public class MovieStatusService extends AbstractService<MovieStatus, NoSuchConnectionException> {}

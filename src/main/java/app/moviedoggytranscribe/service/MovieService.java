package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.entity.Movie;

@org.springframework.stereotype.Service
public class MovieService extends AbstractService<Movie, NoSuchMovieException> {}

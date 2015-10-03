package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Entity;
import app.moviedoggytranscribe.model.entity.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class ToMovieDataMapper implements Mapper<MovieData> {

    @Override
    public MovieData mapToData(List<? extends Entity> entities) {
        List<? extends Entity> movies = entities.stream().filter(entity -> entity.getClass().equals(Movie.class))
                .collect(Collectors.toList());

        MovieData movieData = new MovieData();

        movies.forEach(movie -> {
            movieData.setMovieName(((Movie) movie).getTitle());
        });

        return movieData;
    }

}

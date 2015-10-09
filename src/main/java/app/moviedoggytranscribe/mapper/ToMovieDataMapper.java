package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.MovieData;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.service.SimpleMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToMovieDataMapper implements Mapper<Movie, MovieData> {

    @Autowired
    private SimpleMovieService movieService;

    @Override
    public List<MovieData> mapToData(List<Movie> movies) {
        List<MovieData> movieDataList = new ArrayList<>();

        for (Movie movie : movies) {
            MovieData movieData = new MovieData();
            movieData.setMovie(movie);
            movieData.setStatuses(movieService.getMovieStasuses(movie));
            movieData.setWatchers(movieService.getMovieWatchers(movie));
            movieDataList.add(movieData);
        }

        return movieDataList;
    }

}

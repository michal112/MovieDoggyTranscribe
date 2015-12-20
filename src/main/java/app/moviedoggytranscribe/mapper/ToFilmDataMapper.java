package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.FilmData;
import info.talacha.filmweb.models.Film;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToFilmDataMapper implements FilmMapper {

    @Override
    public List<FilmData> mapToData(List<Film> films) {
        List<FilmData> filmDataList = new ArrayList<>();

        for (Film film : films) {
            FilmData filmData = new FilmData();
            filmData.setFilm(film);
            filmData.setTitle(film.getTitle());
            filmData.setGenre(film.getGenre());
            filmData.setYear(film.getYear());
            filmDataList.add(filmData);
        }

        return filmDataList;
    }

}

package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.FilmData;
import info.talacha.filmweb.models.Film;

import java.util.List;

public interface FilmMapper {

    List<FilmData> mapToData(List<Film> films);

}

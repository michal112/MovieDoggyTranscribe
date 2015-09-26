package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MovieDao implements Dao<Movie> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(AppConstants.MOVIE_TABLE_NAME).usingGeneratedKeyColumns(AppConstants.KEY_COLUMN_NAME);
    }

    @Override
    public Integer add(Movie movie) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(movie);
        movie.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return movie.getId();
    }

    @Override
    public List<Movie> getAll() {
       return jdbcTemplate.query(AppConstants.GET_ALL_MOVIES_QUERY, BeanPropertyRowMapper.newInstance(Movie.class));
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(AppConstants.DELETE_MOVIE_QUERY, id);
    }

    @Override
    public void update(Movie entity) {
        jdbcTemplate.update(AppConstants.UPDATE_MOVIE_QUERY, entity.getTitle(),
            entity.getDescription(), entity.getImageUrl(), entity.getMovieUrl(),
                entity.getGenre(), entity.getYear(), entity.getRating(), entity.getId());
    }

}

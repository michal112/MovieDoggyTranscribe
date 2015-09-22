package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class MovieDao implements Dao<Movie> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("MOVIE").usingGeneratedKeyColumns("id");
    }

    public Integer add(Movie movie) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(movie);
        movie.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return movie.getId();
    }

    public List<Movie> getAll() {
       return jdbcTemplate.query(AppConstants.GET_ALL_MOVIES_QUERY, BeanPropertyRowMapper.newInstance(Movie.class));
    }

}

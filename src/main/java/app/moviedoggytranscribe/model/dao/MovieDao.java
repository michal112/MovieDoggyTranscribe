package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MovieDao implements Dao<Movie> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MOVIE").usingGeneratedKeyColumns("id");
    }

    public Integer add(Movie movie) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(movie);
        return (Integer) simpleJdbcInsert.executeAndReturnKey(parameters);
    }

}

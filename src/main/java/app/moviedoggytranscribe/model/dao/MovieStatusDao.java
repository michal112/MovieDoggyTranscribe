package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.MovieStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MovieStatusDao implements Dao<MovieStatus> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(AppConstants.MOVIE_STATUS_TABLE_NAME)
                        .usingGeneratedKeyColumns(AppConstants.KEY_COLUMN_NAME);
    }

    @Override
    @Transactional
    public Integer add(MovieStatus movieStatus) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(movieStatus);
        movieStatus.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return movieStatus.getId();
    }

    @Override
    @Transactional
    public List<MovieStatus> getAll() {
        return jdbcTemplate.query(AppConstants.GET_ALL_MOVIE_STATUS_QUERY,
                BeanPropertyRowMapper.newInstance(MovieStatus.class));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jdbcTemplate.update(AppConstants.DELETE_MOVIE_STATUS_QUERY, id);
    }

    @Override
    @Transactional
    public void update(MovieStatus movieStatus) {
        throw new UnsupportedOperationException();
    }

}

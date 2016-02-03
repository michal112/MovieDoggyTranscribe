package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.MovieWatcher;
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
public class MovieWatcherDao implements SimpleMovieWatcherDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(AppConstants.MOVIE_WATCHER_TABLE_NAME)
                .usingGeneratedKeyColumns(AppConstants.KEY_COLUMN_NAME);
    }

    @Override
    @Transactional
    public Integer add(MovieWatcher movieWatcher) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(movieWatcher);
        movieWatcher.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return movieWatcher.getId();
    }

    @Override
    @Transactional
    public List<MovieWatcher> getAll() {
        return jdbcTemplate.query(AppConstants.GET_ALL_MOVIE_WATCHER_QUERY,
                BeanPropertyRowMapper.newInstance(MovieWatcher.class));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jdbcTemplate.update(AppConstants.DELETE_MOVIE_WATCHER_QUERY, id);
    }

    @Override
    @Transactional
    public void update(MovieWatcher movieWatcher) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public MovieWatcher getByMovieIdAndWatcherId(Integer movieId, Integer watcherId) {
        return jdbcTemplate.queryForObject(AppConstants.GET_MOVIE_WATCHER_BY_MOVIE_ID_AND_WATCHER_ID, new Integer[]{
                movieId, watcherId }, BeanPropertyRowMapper.newInstance(MovieWatcher.class));
    }

}

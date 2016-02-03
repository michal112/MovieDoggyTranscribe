package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.Movie;
import app.moviedoggytranscribe.model.entity.Status;
import app.moviedoggytranscribe.model.entity.Watcher;
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
public class MovieDao implements SimpleMovieDao {

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
    @Transactional
    public Integer add(Movie movie) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(movie);
        movie.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return movie.getId();
    }

    @Override
    @Transactional
    public List<Movie> getAll() {
       return jdbcTemplate.query(AppConstants.GET_ALL_MOVIES_QUERY, BeanPropertyRowMapper.newInstance(Movie.class));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jdbcTemplate.update(AppConstants.DELETE_MOVIE_QUERY, id);
    }

    @Override
    @Transactional
    public void update(Movie movie) {
        jdbcTemplate.update(AppConstants.UPDATE_MOVIE_QUERY, movie.getTitle(),
            movie.getDescription(), movie.getImageUrl(), movie.getMovieUrl(),
                movie.getGenre(), movie.getYear(), movie.getRating(), movie.getId());
    }

    @Override
    @Transactional
    public List<Status> getStatuses(Integer movieId) {
        return jdbcTemplate.query(AppConstants.GET_ALL_MOVIE_STASUSES, new Integer[]{ movieId },
                BeanPropertyRowMapper.newInstance(Status.class));
    }

    @Override
    @Transactional
    public List<Watcher> getWatchers(Integer movieId) {
        return jdbcTemplate.query(AppConstants.GET_ALL_MOVIE_WATCHERS, new Integer[]{ movieId },
                BeanPropertyRowMapper.newInstance(Watcher.class));
    }

}

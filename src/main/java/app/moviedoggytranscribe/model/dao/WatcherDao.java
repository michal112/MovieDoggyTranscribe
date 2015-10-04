package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.Watcher;
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
public class WatcherDao implements Dao<Watcher> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(AppConstants.WATCHER_TABLE_NAME).usingGeneratedKeyColumns(AppConstants.KEY_COLUMN_NAME);
    }

    @Override
    public Integer add(Watcher watcher) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(watcher);
        watcher.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return watcher.getId();
    }

    @Override
    public List<Watcher> getAll() {
        return jdbcTemplate.query(AppConstants.GET_ALL_WATCHERS_QUERY, BeanPropertyRowMapper.newInstance(Watcher.class));
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(AppConstants.DELETE_WATCHER_QUERY, id);
    }

    @Override
    public void update(Watcher watcher) {
        jdbcTemplate.update(AppConstants.UPDATE_WATCHER_QUERY, watcher.getNick(), watcher.getName(),
                watcher.getSurname(), watcher.getId());
    }

}
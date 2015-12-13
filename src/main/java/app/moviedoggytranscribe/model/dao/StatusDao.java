package app.moviedoggytranscribe.model.dao;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.entity.Status;
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
public class StatusDao implements SimpleStatusDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(AppConstants.STATUS_TABLE_NAME).usingGeneratedKeyColumns(AppConstants.KEY_COLUMN_NAME);
    }

    @Override
    @Transactional
    public Integer add(Status status) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(status);
        status.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
        return status.getId();
    }

    @Override
    @Transactional
    public List<Status> getAll() {
        return jdbcTemplate.query(AppConstants.GET_ALL_STATUSES_QUERY, BeanPropertyRowMapper.newInstance(Status.class));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jdbcTemplate.update(AppConstants.DELETE_STATUS_QUERY, id);
    }

    @Override
    @Transactional
    public void update(Status status) {
        jdbcTemplate.update(AppConstants.UPDATE_STATUS_QUERY, status.getName(), status.getColour(), status.getId());
    }

}

package app.moviedoggytranscribe.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Autowired
    private DataSourceHolder dataSourceHolder;

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceHolder.getDataSourceType();
    }

}

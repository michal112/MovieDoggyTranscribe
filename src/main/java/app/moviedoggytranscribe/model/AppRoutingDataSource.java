package app.moviedoggytranscribe.model;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class AppRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSourceType();
    }

}

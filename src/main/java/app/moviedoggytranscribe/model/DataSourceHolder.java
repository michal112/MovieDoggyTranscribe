package app.moviedoggytranscribe.model;

public class DataSourceHolder {

    private static DataSourceType DATA_SOURCE_TYPE;

    public static void setDataSourceType(DataSourceType dataSourceType) {
        DATA_SOURCE_TYPE = dataSourceType;
    }

    public static DataSourceType getDataSourceType() {
        return DATA_SOURCE_TYPE;
    }

}

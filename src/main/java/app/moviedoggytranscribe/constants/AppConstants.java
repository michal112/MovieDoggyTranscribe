package app.moviedoggytranscribe.constants;

public class AppConstants {

    public static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
    public static final String VIEWS_FOLDER_NAME = "views";

    public static final String KEY_COLUMN_NAME = "id";
    public static final String WATCHER_KEY_COLUMN_NAME = "idWatcher";

    public static final String STATUS_TABLE_NAME = "status";
    public static final String GET_ALL_STATUSES_QUERY = "SELECT * FROM " + STATUS_TABLE_NAME;
    public static final String DELETE_STATUS_QUERY = "DELETE FROM " + STATUS_TABLE_NAME +
            " WHERE " + KEY_COLUMN_NAME +  " = ?";
    public static final String UPDATE_STATUS_QUERY = "UPDATE " + STATUS_TABLE_NAME + " SET name = ?," +
            " colour = ? WHERE " + KEY_COLUMN_NAME + " = ?";

    public static final String WATCHER_TABLE_NAME = "watcher";
    public static final String GET_ALL_WATCHERS_QUERY = "SELECT * FROM " + WATCHER_TABLE_NAME;
    public static final String DELETE_WATCHER_QUERY = "DELETE FROM " + WATCHER_TABLE_NAME +
            " WHERE " + KEY_COLUMN_NAME +  " = ?";
    public static final String UPDATE_WATCHER_QUERY = "UPDATE " + WATCHER_TABLE_NAME + " SET nick = ?," +
            " name = ?, surname = ? WHERE " + KEY_COLUMN_NAME + " = ?";

    public static final String MOVIE_TABLE_NAME = "movie";
    public static final String GET_ALL_MOVIES_QUERY = "SELECT * FROM " + MOVIE_TABLE_NAME;
    public static final String DELETE_MOVIE_QUERY = "DELETE FROM " + MOVIE_TABLE_NAME +
            " WHERE " + KEY_COLUMN_NAME +  " = ?";
    public static final String UPDATE_MOVIE_QUERY = "UPDATE " + MOVIE_TABLE_NAME + " SET title = ?, description = ?," +
            " imageUrl = ?, movieUrl = ?, genre = ?, year = ?, rating = ? WHERE " + KEY_COLUMN_NAME + " = ?";

    public static final String MOVIE_STATUS_TABLE_NAME = "movie_status";
    public static final String GET_ALL_MOVIE_STATUS_QUERY = "SELECT * FROM " + MOVIE_STATUS_TABLE_NAME;
    public static final String DELETE_MOVIE_STATUS_QUERY = "DELETE FROM " + MOVIE_STATUS_TABLE_NAME +
            " WHERE " + KEY_COLUMN_NAME +  " = ?";

    public static final String MOVIE_WATCHER_TABLE_NAME = "movie_watcher";
    public static final String GET_ALL_MOVIE_WATCHER_QUERY = "SELECT * FROM " + MOVIE_WATCHER_TABLE_NAME;
    public static final String DELETE_MOVIE_WATCHER_QUERY = "DELETE FROM " + MOVIE_WATCHER_TABLE_NAME +
            " WHERE " + KEY_COLUMN_NAME +  " = ?";
    public static final String DELETE_MOVIE_WATCHER_BY_WATCHER_ID = "DELETE FROM" + MOVIE_WATCHER_TABLE_NAME +
            " WHERE " + WATCHER_KEY_COLUMN_NAME +  " = ?";

    public static final String GET_ALL_MOVIE_STASUSES = "SELECT A.* FROM " + STATUS_TABLE_NAME + " AS A" +
            " JOIN " + MOVIE_STATUS_TABLE_NAME + " AS B ON A." + KEY_COLUMN_NAME + " = B.idStatus AND" +
            " B.idMovie = ?";
    public static final String GET_ALL_MOVIE_WATCHERS = "SELECT A.* FROM " + WATCHER_TABLE_NAME + " AS A" +
            " JOIN " + MOVIE_WATCHER_TABLE_NAME + " AS B ON A." + KEY_COLUMN_NAME + " = B.idWatcher AND" +
            " B.idMovie = ?";

}

package app.moviedoggytranscribe.constants;

public class AppConstants {

    public static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
    public static final String VIEWS_FOLDER_NAME = "views";

    public static final String KEY_COLUMN_NAME = "id";
    public static final String MOVIE_TABLE_NAME = "movie";
    public static final String GET_ALL_MOVIES_QUERY = "SELECT * FROM "+ MOVIE_TABLE_NAME;
    public static final String DELETE_MOVIE_QUERY = "DELETE FROM " + MOVIE_TABLE_NAME
            + " WHERE " + KEY_COLUMN_NAME +  " = ?";
    public static final String UPDATE_MOVIE_QUERY = "UPDATE " + MOVIE_TABLE_NAME + " SET title = ?, description = ?," +
            " imageUrl = ?, movieUrl = ?, genre = ?, year = ?, rating = ? WHERE " + KEY_COLUMN_NAME + " = ?";

}

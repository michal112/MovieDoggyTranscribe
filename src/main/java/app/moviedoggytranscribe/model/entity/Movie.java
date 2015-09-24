package app.moviedoggytranscribe.model.entity;

public class Movie {

    private String title;
    private String description;
    private String year;
    private String imageUrl;
    private String movieUrl;
    private String genre;
    private String rating;
    private Integer id;

    public Movie(String title, String description, String year, String imageUrl, String movieUrl, String genre, String rating) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.imageUrl = imageUrl;
        this.movieUrl = movieUrl;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }
}

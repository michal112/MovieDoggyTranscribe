package app.moviedoggytranscribe.model.entity;

public class Movie implements Entity {

    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private String movieUrl;
    private String genre;
    private String year;
    private String rating;

    public Movie() {}

    public Movie(String title, String description, String imageUrl,
                    String movieUrl, String genre, String year, String rating) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.movieUrl = movieUrl;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}

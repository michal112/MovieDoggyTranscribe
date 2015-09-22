package app.moviedoggytranscribe.model.entity;

public class Movie {

    private String title;
    private String description;
    private String year;
    private String imageUrl;
    private String filmUrl;
    private String genre;
    private String rating;

    public Movie(String title, String description, String year, String imageUrl, String filmUrl, String genre, String rating) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.imageUrl = imageUrl;
        this.filmUrl = filmUrl;
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

    public String getFilmUrl() {
        return filmUrl;
    }

    public void setFilmUrl(String filmUrl) {
        this.filmUrl = filmUrl;
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

}

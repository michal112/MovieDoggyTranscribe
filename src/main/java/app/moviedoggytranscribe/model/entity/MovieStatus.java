package app.moviedoggytranscribe.model.entity;

public class MovieStatus implements Entity {

    private Integer id;
    private Integer idMovie;
    private Integer idStatus;

    public MovieStatus() {}

    public MovieStatus(Integer idMovie, Integer idStatus) {
        this.idMovie = idMovie;
        this.idStatus = idStatus;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

}

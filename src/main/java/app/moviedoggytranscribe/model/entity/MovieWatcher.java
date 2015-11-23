package app.moviedoggytranscribe.model.entity;

public class MovieWatcher implements Entity {

    private Integer id;
    private Integer idMovie;
    private Integer idWatcher;

    public MovieWatcher() {}

    public MovieWatcher(Integer idMovie, Integer idWatcher) {
        this.idMovie = idMovie;
        this.idWatcher = idWatcher;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id  = id;
    }
    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public Integer getIdWatcher() {
        return idWatcher;
    }

    public void setIdWatcher(Integer idWatcher) {
        this.idWatcher = idWatcher;
    }

}

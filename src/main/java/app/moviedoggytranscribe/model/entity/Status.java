package app.moviedoggytranscribe.model.entity;

public class Status {

    private Integer id;
    private String name;
    private String colour;

    public Status() {}

    public Status(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}

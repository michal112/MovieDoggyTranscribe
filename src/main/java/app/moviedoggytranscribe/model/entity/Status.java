package app.moviedoggytranscribe.model.entity;

public class Status implements Entity {

    private Integer id;
    private String name;
    private String colour;

    public Status() {}

    public Status(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}

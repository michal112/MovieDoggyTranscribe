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

    public Color getColor() {
        return getColor(this.colour);
    }

    public enum Color {
        RED("red"), YELLOW("yellow"), GREEN("green"), BLUE("blue"), PURPLE("purple");

        private String color;

        Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    private static Color getColor(String colour) {
        for (Color color : Color.values()) {
            if (color.getColor().equals(colour)) {
                return color;
            }
        }

        return null;
    }

}

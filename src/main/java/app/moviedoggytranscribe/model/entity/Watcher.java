package app.moviedoggytranscribe.model.entity;

public class Watcher implements Entity {

    private Integer id;
    private String nick;
    private String name;
    private String surname;

    public Watcher() {}

    public Watcher(String nick, String name, String surname) {
        this.nick = nick;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}

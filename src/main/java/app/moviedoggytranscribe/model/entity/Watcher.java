package app.moviedoggytranscribe.model.entity;

public class Watcher {

    private Integer id;
    private String nick;
    private String name;
    private String surname;

    public Watcher(String nick, String surname, String name) {
        this.nick = nick;
        this.surname = surname;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

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

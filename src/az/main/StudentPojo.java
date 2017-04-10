package az.main;

import java.io.Serializable;

public class StudentPojo implements Serializable {

    private int id;
    private String nick;
    private int isAdmin;
    private String email;

    public StudentPojo() {
    }

    public StudentPojo(int id, String name, String surname, int isAdmin, String email) {
        this.id = id;
        this.nick = name;
        this.isAdmin = isAdmin;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

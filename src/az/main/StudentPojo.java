package az.main;

import java.io.Serializable;

public class StudentPojo implements Serializable {

    private int id;
    private String idDzialy;
    private int idUzytkownicy;
    private String nick;
    private String nazwa;
    private int isAdmin;
    private String email;

    public String getIdDzialy() {
        return idDzialy;
    }

    public int getIdUzytkownicy() {
        return idUzytkownicy;
    }

    public String getNazwa() {
        return nazwa;
    }



    public StudentPojo() {
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

    public void setIdDzialy(String idDzialy) {
        this.idDzialy = idDzialy;
    }

    public void setIdUzytkownicy(int idUzytkownicy) {
        this.idUzytkownicy = idUzytkownicy;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}

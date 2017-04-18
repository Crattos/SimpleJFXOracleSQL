package az.LogikaSQL.Dane;

import java.io.Serializable;

public class Pilkarze implements Serializable {


    private String idDzialy;
    private int idUzytkownicy;
    private String nazwa;


    public String getIdDzialy() {
        return idDzialy;
    }

    public int getIdUzytkownicy() {
        return idUzytkownicy;
    }

    public String getNazwa() {
        return nazwa;
    }


    public Pilkarze() {
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

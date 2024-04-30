package ispit2024jan2;

public class Direktiva {

    String ime;
    String naredba;


    public Direktiva(String ime, String naredba) {
        this.ime = ime;
        this.naredba = naredba;
    }


    public String getIme() {
        return ime;
    }

    public String getNaredba() {
        return naredba;
    }




    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setNaredba(String naredba) {
        this.naredba = naredba;
    }

    @Override
    public String toString() {
        return ime + " " + naredba;
    }
}

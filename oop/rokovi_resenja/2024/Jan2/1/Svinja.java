package ispit2024jan2;

public class Svinja extends Zivotinja{

    String parola;

    public Svinja(String nazivZivotinje, String parola) {
        super(nazivZivotinje);
        this.parola = parola;
    }

    public Svinja (String nazivZivotinje) {
        super(nazivZivotinje);
        this.parola = "Sve su zivotinje jednake";
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Svinja " + nazivZivotinje + " kaze: " + parola;
    }
}

package ispit2024jan2;

public class Ovca extends Zivotinja{

    static int brojac = 0;
    static int brojOvaca = 0;
    String[] parole;

    public Ovca(String nazivZivotinje, String[] parole) {
        super(nazivZivotinje);
        this.parole = parole;
        ++brojOvaca;
    }

    public String[] getParole() {
        return parole;
    }

    public void setParole(String[] parole) {
        this.parole = parole;
    }

    @Override
    public String toString() {
        brojac = brojac % parole.length;
        StringBuilder niska = new StringBuilder();
        niska.append("Ovca kaze: ").append(parole[brojac]);
        brojac++;
        brojac = brojac % parole.length;
        return niska.toString();
    }

    public void uglas() {
        brojac = brojac % parole.length;
        for (int i = 0; i < brojOvaca; i++)
            System.out.println(parole[brojac]);
        brojac++;
        brojac = brojac % parole.length;
    }

    public void naredi(Direktiva d) {
        if (this.nazivZivotinje.equals(d.ime)) {
            this.parole = new String[1];
            this.parole[0] = d.naredba;
        }

    }

}

package ispit2024jan2;


public class Osoba implements Comparable<Osoba>{

    String ime;
    String prezime;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }


    @Override
    public int compareTo(Osoba o) {
        Osoba osoba2 = (Osoba) o;
        int rez = this.ime.compareTo(osoba2.ime);
        if (rez != 0)
            return rez;
        else
            return this.prezime.compareTo(osoba2.prezime);
    }
}

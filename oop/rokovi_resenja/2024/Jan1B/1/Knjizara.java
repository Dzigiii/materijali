package ispit2024jan1B;

import java.util.ArrayList;

public class Knjizara {

    ArrayList<Knjiga> knjige;

    void ispisiKomentare(int indeks) {
        System.out.println(knjige.get(indeks).vratiKomentare());
    }

    public Knjizara(ArrayList<Knjiga> knjige) {
        this.knjige = knjige;
    }
}

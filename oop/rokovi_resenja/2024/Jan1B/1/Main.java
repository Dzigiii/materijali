package ispit2024jan1B;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Knjizara knjizara = new Knjizara(new ArrayList<Knjiga>());
        knjizara.knjige.add(new Roman("Mort", "Teri Pracet", 5, "fantazija",
                "Smrt dolazi po svakog od nas.", "Kada dođe po Morta, ponudiće mu posao."));
        knjizara.knjige.add(new NaucnaKnjiga("Geometrija za informaticare", "Tijana Sukilovic", 1500, "geometrija", "Formula bez dokaza.\n", "Zadaci bez resenja.\n"));



        for (Knjiga knjiga : knjizara.knjige) {
            knjiga.prikaziInformacije();
        }

        knjizara.ispisiKomentare(0);
    }
}

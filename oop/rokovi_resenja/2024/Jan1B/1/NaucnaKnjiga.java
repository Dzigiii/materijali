package ispit2024jan1B;

import java.util.Arrays;

public class NaucnaKnjiga extends Knjiga{

    String naucnaOblast;


    public NaucnaKnjiga(String naslov, String autor, int brNaStanju, String naucnaOblast, String... komentari) {
        super(naslov, autor, brNaStanju, komentari);
        this.naucnaOblast = naucnaOblast;
    }


    @Override
    void prikaziInformacije() {
        System.out.println("Naucna knjiga: " + naslov + "\nAutor: " + autor + "\nBroj na stanju: " + brNaStanju + "\nNaucna oblast: " + naucnaOblast + "\n");
    }
}

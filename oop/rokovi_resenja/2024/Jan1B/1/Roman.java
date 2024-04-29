package ispit2024jan1B;

import java.util.Arrays;

public class Roman extends Knjiga{

    String zanr;

    public Roman(String naslov, String autor, int brNaStanju,String zanr, String... komentari) {
        super(naslov, autor, brNaStanju, komentari);
        this.zanr = zanr;
    }

    @Override
    void prikaziInformacije() {
        System.out.println("Roman: " + naslov + "\nAutor: " + autor + "\nBroj na stanju: " + brNaStanju + "\nZanr: " + zanr + "\n");
    }
}

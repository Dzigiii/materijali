package ispit2024jan1A;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NaucnaKnjiga extends Knjiga{

    String naucnaOblast;

    void prikaziInformacije() {
        System.out.println("Naucna knjiga pod nazivom " + naslov + ", autora: " + autor + ", oblasti knjige: " + naucnaOblast + "\n" + tekst);
    }

    @Override
    public void iznajmi() throws IOException {
        Path putanja = Paths.get(naslov+"-"+naucnaOblast+".txt");

        File fajl = new File(String.valueOf(putanja));
        Files.writeString(putanja, tekst);
    }

    public NaucnaKnjiga(String naslov, String autor, String tekst, String naucnaOblast) {
        super(naslov, autor, tekst);
        this.naucnaOblast = naucnaOblast;
    }
}

package ispit2024jan1A;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Roman extends Knjiga implements Iznajmljivanje{

    String zanr;

    @Override
    void prikaziInformacije() {
        System.out.println("Roman pod nazivom " + naslov + ", autora: " + autor + ", zanra: " + zanr + "\n" + tekst);
    }

    @Override
    public void iznajmi() throws IOException {
        Path putanja = Paths.get(naslov+"-"+zanr+".txt");
        Files.writeString(putanja, tekst);
    }

    public Roman(String naslov, String autor, String tekst, String zanr) {
        super(naslov, autor, tekst);
        this.zanr = zanr;
    }



}

package ispit2024jan1A;

public abstract class Knjiga implements Iznajmljivanje {

    String naslov;
    String autor;
    String tekst;


    abstract void prikaziInformacije();

    public Knjiga(String naslov, String autor, String tekst) {
        this.naslov = naslov;
        this.autor = autor;
        this.tekst = tekst;
    }
}

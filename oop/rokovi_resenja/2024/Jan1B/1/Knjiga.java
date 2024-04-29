package ispit2024jan1B;


public abstract class Knjiga implements PrikazKomentara{

    String naslov;
    String autor;
    int brNaStanju;
    String[] komentari;

    public Knjiga(String naslov, String autor, int brNaStanju, String[] komentari) {
        this.naslov = naslov;
        this.autor = autor;
        this.brNaStanju = brNaStanju;
        this.komentari = komentari;
    }

    abstract void prikaziInformacije();

    @Override
    public String vratiKomentare() {
        StringBuilder niska = new StringBuilder();
        for (String komentar : komentari) {
            niska.append(komentar).append("\n");
        }
        return niska.toString();
    }
}

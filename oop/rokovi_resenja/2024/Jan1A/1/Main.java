package ispit2024jan1A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        Biblioteka biblioteka = new Biblioteka(new ArrayList<>());
        biblioteka.knjige.add(new Roman("Mort", "Teri Pracet", "Roman o Disksvetu.\n" +
                "\n" +
                "Smrt dolazi po svakog od nas.\n" +
                "Kada dođe po Morta, ponudiće mu posao.\n" +
                "Pošto je dobio uveravanja da nije neophodno da bude mrtav, Mort prihvata. Medutim, uskoro će otkriti da romantične čežnje ne idu uz odgovornosti koje nosi šegrtovanje kod Smrti.\n" +
                "\n" +
                "„Priča o tome šta se dešava kada Smrt odluči da uzme šegrta pretvara se u nezadrživu bujicu komedije, satire, ironije i neodoljivo humorističko delo koje kombinuje ledene komadiće cinizma s iskricama istinske osećajnosti. Izranjajući zadihani iz dubina ove preširoke metafore, ponizno proglašavamo ovu knjigu vrlo inteligentnom i duhovitom.“ British Fantasy Newsletter\n" +
                "\n" +
                "„Mort je mladić čije srce možda jeste na mestu, ali ne i pamet. Njegovo pojavljivanje na lokalnoj berzi rada predstavlja potpuni promašaj sve dok se ne pojavi određeni mitski arhetip… Naravno, stvari kreću naopako na impozantan način. Otkačenost i šarm leže u pripovedanju. Pračet daje svojim likovima (čak i Smrti) prostora da se razvijaju. On previše voli svoje junake da bi zapao u lakorečivost putujućeg komedijaša. Takav entuzijazam održava Disksvet svežim u njegovoj vanvremenskoj apsurdnosti.“ Locus\n", "fantastika"));
        biblioteka.knjige.add(new NaucnaKnjiga("Geometrija za informaticare", "Tijana Sukilovic", "Distanca od tacke do ravni u prostoru (bez dokaza)", "geometrija"));

        for (Knjiga knjiga : biblioteka.knjige) {
            knjiga.prikaziInformacije();
        }
        biblioteka.iznajmiKnjigu(0);
        biblioteka.iznajmiKnjigu(1);

    }

}

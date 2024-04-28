package ispit2024jan1A;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Biblioteka {

    ArrayList<Knjiga> knjige;

    public Biblioteka(ArrayList<Knjiga> knjige) {
        this.knjige = knjige;
    }

    void iznajmiKnjigu(int indeks) throws IOException {
        knjige.get(indeks).iznajmi();
    }

}

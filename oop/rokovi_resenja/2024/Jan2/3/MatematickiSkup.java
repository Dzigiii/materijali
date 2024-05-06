package ispit2024jan2;

import java.util.ArrayList;
import java.util.Optional;

public class MatematickiSkup <T extends Comparable<T>> {

    String ime;
    ArrayList<T> elementi;
    int kapacitet;

    public MatematickiSkup(String ime, int kapacitet) {
        this.ime = ime;
        this.elementi = new ArrayList<>(kapacitet);
        this.kapacitet = kapacitet;
    }

    public boolean postoji(T element) {
        return elementi.contains(element);
    }
    public void dodaj(T element) {
        if (elementi.contains(element))
            return;
        else {
            if (elementi.size() == kapacitet) {
                kapacitet *= 2;
                ArrayList<T> noviNiz = new ArrayList<>(kapacitet);
                noviNiz.addAll(elementi);
                elementi = noviNiz;
            }
            elementi.add(element);
        }
    }

    public MatematickiSkup<T> unija (MatematickiSkup<T> s) {
        MatematickiSkup<T> noviSkup = new MatematickiSkup<>(this.ime + " u " + s.ime,this.kapacitet + s.kapacitet);
        noviSkup.elementi.addAll(this.elementi);
        for ( T element : s.elementi) {
            if (!this.elementi.contains(element)) {
                noviSkup.elementi.add(element);
            }
        }
        return noviSkup;
    }

    public Optional<T> nadjiMaksimum() {
        if (elementi.isEmpty()) {
            return Optional.empty();
        }
        T max = elementi.get(0);

        for (T element : elementi) {
            if (element.compareTo(max) > 0)
                max = element;
        }
        return Optional.of(max);
    }



    @Override
    public String toString() {
        StringBuilder niska = new StringBuilder();
        niska.append(this.ime + " = { ");
        for (T element : this.elementi) {
            niska.append(element.toString() + ",");
        }
        niska.deleteCharAt(niska.length()-1);
        niska.append(" }");

        return niska.toString();
    }


}

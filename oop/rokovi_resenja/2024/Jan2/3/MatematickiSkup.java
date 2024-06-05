package ispit2024jan2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class MatematickiSkup2 <T extends Comparable<T>> {

    String imeSkupa;
    T[] elementi;
    int kapacitet;


    public MatematickiSkup2(String imeSkupa, int kapacitet) {
        this.imeSkupa = imeSkupa;
        this.elementi = (T[]) new Comparable[kapacitet];
        this.kapacitet = kapacitet;
    }



    public boolean postoji(T element) {
        for (T primerak : elementi) {
            if (primerak == null)
                break;
            if (primerak.equals(element))
                return true;
        }
        return false;
    }

    public void dodaj(T element) {
        if (postoji(element)) {
            return;
        }
        if (elementi.length == kapacitet) {
            kapacitet *= 2;
            elementi = Arrays.copyOf(elementi, kapacitet);
        }

        int brojac = 0;
        int i = 0;
        for (T t : elementi) {
            if (t == null) {
                i = brojac;
                break;
            }
            brojac++;
        }

        elementi[i] = element;
    }

    public Optional<T> nadjiMaksimum() {
        if (elementi.length == 0)
            return Optional.empty();

        T maks = elementi[0];
        for (T primerak : elementi) {
            if (primerak == null)
                break;
            if (primerak.compareTo(maks) > 0)  {
                maks = primerak;
            }
        }
        return Optional.ofNullable(maks);
    }

    public MatematickiSkup2<T> unija (MatematickiSkup2<T> skup) {
        MatematickiSkup2<T> noviSkup = new MatematickiSkup2<>(imeSkupa + " u " + skup.imeSkupa, elementi.length + skup.elementi.length);
        noviSkup.elementi = Arrays.copyOf(elementi, noviSkup.kapacitet);

        for (int i = 0; i < skup.elementi.length; i++) {
            if (skup.elementi[i] == null)
                break;
            if (!noviSkup.postoji(skup.elementi[i]))
                noviSkup.dodaj(skup.elementi[i]);
        }
        return noviSkup;
    }

    @Override
    public String toString() {
        StringBuilder niska = new StringBuilder();
        niska.append(imeSkupa + " = { ");
        for (T primerak : elementi) {
            if (primerak == null)
                break;
            niska.append(primerak.toString()).append(",");
        }
        niska.deleteCharAt(niska.length()-1);
        niska.append(" }");

        return niska.toString();
    }
}

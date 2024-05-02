package ispit2024jan1A;

import java.util.Objects;

public class GenericList <T> {

    private Cvor glava;

    public GenericList () {
        this.glava = null;
    }

    public Cvor getGlava() {
        return glava;
    }

    private class Cvor {

        private T element;
        Cvor sledeciElement;

        public Cvor (T element) {
            this.element = element;
            this.sledeciElement = null;
        }

    }

    public void dodajCvor(T element) {
        Cvor noviCvor = new Cvor (element);
        if (glava == null) {
            glava = noviCvor;
        }
        else {
            Cvor tempCvor = glava;
            while (tempCvor.sledeciElement != null) {
                tempCvor = tempCvor.sledeciElement;
            }
            tempCvor.sledeciElement = noviCvor;
        }
    }

    public void ukloniCvor() {
        if (glava == null) {
            return;
        }
        Cvor tempCvor = glava;
        while (tempCvor.sledeciElement != null) {
            tempCvor = tempCvor.sledeciElement;
        }
        tempCvor = null;        // .sledeciElement od poslednjeg Cvora je null, ako stavimo njega na null, onda ce Cvor
                                // pre njega da pokazuje na null, tako bi trebalo da se dobije to sto zelimo?
                                // garbage collector ce svoje da odradi po pitanju ostavljene memorije od objekata
    }

    @Override
    public String toString () {
        Cvor tempCvor = glava;
        if (tempCvor == null)
            return "{ }";
        else {
            StringBuilder niska = new StringBuilder();
            niska.append("{ ");
            while (tempCvor != null) {
                niska.append(tempCvor.element);
                niska.append(" ");
                tempCvor = tempCvor.sledeciElement;
            }
            niska.append("}");
            return niska.toString();
        }
    }

    public void ispisi () {
        System.out.println(this.toString());
    }

    public boolean equals (Object element) {
        GenericList<?> tempLista = (GenericList<?>) element;

        Cvor tempCvor = this.glava;
        Cvor trazeniCvor = (Cvor) tempLista.glava;

        while (tempCvor != null && trazeniCvor != null) {
            if (!tempCvor.element.equals(trazeniCvor.element)) {
                return false;
            }
            trazeniCvor = trazeniCvor.sledeciElement;
            tempCvor = tempCvor.sledeciElement;
        }
        return true;
    }


    public int findIndex (T element) {
        int brojac = 0;
        Cvor tempCvor = glava;
        int index = -1;
        while (tempCvor != null) {
            if (Objects.equals(tempCvor.element, element)) {
                index = brojac;
            }
            brojac++;
            tempCvor = tempCvor.sledeciElement;
        }
        return index;
    }
}

package ispit2024jan2;


import java.util.Optional;

public class Test {

    public static void main(String[] args) {

    Osoba osoba1 = new Osoba("Marko","Markovic");
    Osoba osoba2 = new Osoba("Pera","Peric");
    Osoba osoba3 = new Osoba("Djura","Djuric");
    Osoba osoba4 = new Osoba("Mika","Mikic");
    Osoba osoba5 = new Osoba("Marija","Marijic");
    Osoba osoba6 = new Osoba("Sonja","Sonjic");
    Osoba osoba7 = new Osoba("Milica","Milicic");

    MatematickiSkup<Osoba> s1 = new MatematickiSkup<>("skup1", 2);
    MatematickiSkup<Osoba> s2 = new MatematickiSkup<>("skup2", 3);

        Optional<Osoba> maksimum = s1.nadjiMaksimum();
        if (maksimum.isPresent()) {
            System.out.println("Leksikografski max osoba u skupu s1 je = " + maksimum.get());
        }
        else  {
            System.out.println("Skup s1 je prazan!");
        }
    System.out.println();

    System.out.println(s1.toString());
    s1.dodaj(osoba1);
    System.out.println("Kapacitet skupa s1 je " + s1.kapacitet);
    System.out.println(s1.toString());
    s1.dodaj(osoba2);
    s1.dodaj(osoba3);
    System.out.println("Kapacitet skupa s1 je " + s1.kapacitet);
    System.out.println(s1.toString());
    System.out.println(s2.toString());
    System.out.println();
    s2.dodaj(osoba4);
    System.out.println("Kapacitet skupa s2 je " + s2.kapacitet);
    System.out.println(s2.toString());
    s2.dodaj(osoba5);
    s2.dodaj(osoba6);
    s2.dodaj(osoba7);
    System.out.println("Kapacitet skupa s2 je " + s2.kapacitet);
    System.out.println(s2.toString());

    MatematickiSkup<Osoba> unijas1s2 = s1.unija(s2);
    System.out.println(unijas1s2.toString());
    System.out.println("Kapacitet unije skupa s1 i s2 je " + unijas1s2.kapacitet);

    maksimum = s1.nadjiMaksimum();
    if (maksimum.isPresent()) {
        System.out.println("Leksikografski max osoba u skupu s1 je = " + maksimum.get());
    }
    else  {
        System.out.println("Skup s1 je prazan!");
    }

    maksimum = s2.nadjiMaksimum();
        if (maksimum.isPresent()) {
            System.out.println("Leksikografski max osoba u skupu s1 je = " + maksimum.get());
        }
        else  {
            System.out.println("Skup s1 je prazan!");
        }

    }
}

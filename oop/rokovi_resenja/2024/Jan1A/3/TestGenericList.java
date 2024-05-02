package ispit2024jan1A;

public class TestGenericList {

    public static void main(String[] args) {

        GenericList<GenericList<Integer>> vektori = new GenericList<>();

        GenericList<Integer> vektor1 = new GenericList<>();
        vektor1.dodajCvor(1);
        vektor1.dodajCvor(2);
        vektor1.dodajCvor(3);

        vektori.dodajCvor(vektor1);


        GenericList<Integer> vektor2 = new GenericList<>();
        vektor2.dodajCvor(-4);
        vektor2.dodajCvor(5);
        vektor2.dodajCvor(7);

        vektori.dodajCvor(vektor2);


        GenericList<Integer> vektor3 = new GenericList<>();
        vektor3.dodajCvor(5);
        vektor3.dodajCvor(-10);
        vektor3.dodajCvor(3);

        vektori.dodajCvor(vektor3);

        vektori.ispisi();

        //isti kao vektor3
        GenericList<Integer> vektor4 = new GenericList<>();
        vektor4.dodajCvor(5);
        vektor4.dodajCvor(-10);
        vektor4.dodajCvor(3);

        GenericList<Integer> vektor5 = new GenericList<>();
        vektor5.dodajCvor(3);
        vektor5.dodajCvor(2);
        vektor5.dodajCvor(1);

        System.out.println("Indeks vektora " + vektor4.toString() + " ako postoji (inace -1) u listi --> " + vektori.findIndex(vektor4));
        System.out.println("Indeks vektora " + vektor5.toString() + " ako postoji (inace -1) u listi --> " + vektori.findIndex(vektor5));


    }
}

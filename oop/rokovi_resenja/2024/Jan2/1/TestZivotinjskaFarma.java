package ispit2024jan2;

import java.util.Scanner;

public class TestZivotinjskaFarma {

    public static void main(String[] args) {

        Scanner sc1 = new Scanner(System.in);
        int brojRecenica = sc1.nextInt();
        String zaNapoleona = sc1.next().replaceAll("_", " ");;
        Svinja napoleon = new Svinja("Napoleon", zaNapoleona);

        String[] paroleZaOvce = new String[brojRecenica-1];
        for (int i = 0; i < brojRecenica-1; i++) {
            paroleZaOvce[i] = sc1.next().replaceAll("_"," ");
        }

        Ovca moli = new Ovca ("Moli", paroleZaOvce);
        Ovca poli = new Ovca ("Poli", paroleZaOvce);

        System.out.println(napoleon.toString());
        moli.uglas();
        poli.uglas();

        Direktiva d1 = new Direktiva("Poli", "CETIRI NOGE DOBRE, DVE BOLJE");
        poli.naredi(d1);

        System.out.println(poli.toString());

        napoleon.setParola("Sve su zivotinje jednake, ali su neke jednakije od drugih");
        System.out.println(napoleon.toString());


    }


}

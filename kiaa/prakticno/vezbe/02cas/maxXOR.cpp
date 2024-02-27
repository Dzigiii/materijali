#include <iostream>
#include <map>
#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;

typedef unsigned long long ull; //da ne bismo ovo pisali sve vreme ovoliko

struct Cvor {
    Cvor* grane[2]{}; //dva pokazivaca, jedan ce na 1, drugi na 0 pokazivati ako bude trebalo
    //ako postoji grane[0], to znaci da trenutan cvor ima dete cifru 0, bez potrebe za dodatnim atributima u vidu cifre
};


void dodaj_broj(Cvor *koren, ull &x);

ull maxXOR(Cvor *koren, ull &x);

void oslobodi(Cvor *koren);

int maxCifra = 0;

int main () {

    int n; //broj binarnih brojeva
    cin >> n;

    Cvor* stablo = new Cvor();
    ull x;
    ull max_xor = 0;
    cin >> x;
    dodaj_broj(stablo, x);
    n--;
    while (n--) {
        cin >> x;
        ull rez = maxXOR (stablo, x);
        max_xor = max(max_xor, rez);
        dodaj_broj(stablo, x);
    }
    cout << max_xor << '\n';
    oslobodi(stablo);

    return 0;
}

void oslobodi(Cvor *koren) {
    if (koren != nullptr) {
        oslobodi(koren->grane[0]);
        oslobodi(koren->grane[1]);
    }
    delete koren;
}

ull maxXOR(Cvor *koren, ull &x) {

    ull mask = 1ull << (8*sizeof(ull) - 1);
    ull rez = 0;
    while (mask != 0) {
        int bit = (mask & x) != 0;
        if (koren->grane[!bit] != nullptr) {
            rez = rez | mask;
            koren = koren->grane[!bit];
        }
        else
            koren = koren->grane[bit];
        mask >>= 1;
    }
    return rez;
}

void dodaj_broj(Cvor *koren, ull &x) {

    Cvor* cvor = koren;
    ull mask = 1ull << (8*sizeof(ull) - 1); //00000001
    while (mask != 0) {
        int bit = (mask & x) != 0;
        if (cvor->grane[bit] == nullptr)
            cvor->grane[bit] = new Cvor();
        cvor = cvor->grane[bit];
        mask >>= 1;
    }
}



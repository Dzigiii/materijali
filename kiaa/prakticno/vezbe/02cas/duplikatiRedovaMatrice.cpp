#include <iostream>
#include <map>
#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;


struct Cvor {
    int broj_pojavljivanja = 0;
    Cvor* grane[2]{}; //dva pokazivaca, jedan ce na 1, drugi na 0 pokazivati ako bude trebalo
    //ako postoji grane[0], to znaci da trenutan cvor ima dete cifru 0, bez potrebe za dodatnim atributima u vidu cifre
};


void dodaj_broj(Cvor *koren, int &x);


void oslobodi(Cvor *koren);


int main () {

    int m, n;
    cin >> m >> n;

    int x;
    Cvor* stablo = new Cvor();
    for (int i = 0; i < m; ++i) {
        Cvor* koren = stablo;
        for (int j = 0; j < n; ++j) {
            cin >> x;
            dodaj_broj(koren, x);
            koren = koren->grane[x];
        }
        koren->broj_pojavljivanja++;
        if (koren->broj_pojavljivanja > 1)
            cout << i + 1 << "-ta vrsta je duplikat!" << '\n';
    }


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



void dodaj_broj(Cvor *koren, int& x) {

    Cvor* cvore = koren;
    if (koren->grane[x] == nullptr)
        koren->grane[x] = new Cvor();
}



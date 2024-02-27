#include <iostream>
#include <map>

using namespace std;


struct Cvor {
    map <char, Cvor*> prelaz;
    string rec;
    bool krajReci = false;
};


void ispisiReciLeksikografski(Cvor *koren);

void dodaj (Cvor* koren, string s) {
    for (char c : s) {
        //ako ne postoji karakter koji se nastavlja sa trenutnog cvora
        if (koren->prelaz.find(c) == koren->prelaz.end())
            koren->prelaz[c] = new Cvor();
        koren = koren->prelaz[c];
    }
    koren->rec = s;
    koren->krajReci = true;
}


void oslobodi (Cvor* koren) {
    for (auto podstablo : koren->prelaz)
        oslobodi(podstablo.second);

    delete koren;
}

void nadjiNajduziPrefiks(Cvor* koren) {
    while (koren->prelaz.size() == 1 && koren->krajReci == false) {
        cout << koren->prelaz.begin()->first;
        koren = koren->prelaz.begin()->second;
    }


}

int main () {

    Cvor* stablo = new Cvor();
    int n;
    cin >> n;
    string s;
    while (n--) {
        cin >> s;
        dodaj(stablo, s);
    }
    ispisiReciLeksikografski(stablo);


    oslobodi(stablo);
    return 0;
}

void ispisiReciLeksikografski(Cvor *koren) {
    if (koren->krajReci)
        cout << koren->rec << '\n';
    for (auto podstablo : koren->prelaz) {
        ispisiReciLeksikografski(podstablo.second);
    }

}

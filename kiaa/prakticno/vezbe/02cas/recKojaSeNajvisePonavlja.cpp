#include <iostream>
#include <map>

using namespace std;

struct Cvor {
    map<char, Cvor*> prelaz;
    int broj_pojavljivanja = 0;
};

void dodaj_proveri(Cvor *koren, string &s);

void oslobodiStablo(Cvor *koren);

int max_pojavljivanja = 0;
string max_pojavljivana_rec;

int main () {

    int n; //broj reci
    cin >> n;

    string niska;
    Cvor* stablo = new Cvor();

    while (n--) {
        cin >> niska;
        dodaj_proveri(stablo, niska);
    }

    cout << max_pojavljivana_rec << '\n';

    oslobodiStablo(stablo);

    return 0;
}

void oslobodiStablo(Cvor *koren) {

    for (auto m : koren->prelaz)
        oslobodiStablo(m.second);

    delete koren;
}

void dodaj_proveri(Cvor *koren, string &s) {
    for (char c : s) {
        if (koren->prelaz.find(c) == koren->prelaz.end())
            koren->prelaz[c] = new Cvor;
        koren = koren->prelaz[c];
    }
    koren->broj_pojavljivanja++;

    //sada provera
    if (koren->broj_pojavljivanja > max_pojavljivanja) {
        max_pojavljivanja = koren->broj_pojavljivanja;
        max_pojavljivana_rec = s;
    }
}

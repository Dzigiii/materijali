#include <iostream>
#include <map>

using namespace std;


struct Cvor {
    map <char, Cvor*> prelaz;
    bool krajReci = false;
};


void ispisiReciLeksikografski(Cvor *koren, string& rec);

void dodaj (Cvor* koren, string s) {
    for (char c : s) {
        //ako ne postoji karakter koji se nastavlja sa trenutnog cvora
        if (koren->prelaz.find(c) == koren->prelaz.end())
            koren->prelaz[c] = new Cvor();
        koren = koren->prelaz[c];
    }
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


void autocomplete(Cvor* koren, string& prefiks) {
    for (char c : prefiks) {
        if (koren->prelaz.find(c) == koren->prelaz.end())
            return;
        koren = koren->prelaz[c];
    }
    //u korenu nam je cvor odakle ispisujemo sve reci.
    ispisiReciLeksikografski(koren, prefiks);

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
    string niska;
    cin >> niska;
    autocomplete(stablo, niska);


    oslobodi(stablo);
    return 0;
}

void ispisiReciLeksikografski(Cvor *koren, string& rec) {
    if (koren->krajReci)
        cout << rec << '\n';
    for (auto podstablo : koren->prelaz) {
        rec.push_back(podstablo.first);
        ispisiReciLeksikografski(podstablo.second, rec);
        rec.pop_back();
    }

}

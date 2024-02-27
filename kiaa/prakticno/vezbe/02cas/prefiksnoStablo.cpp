#include <iostream>
#include <map>

using namespace std;


struct Cvor {
    map <char, Cvor*> prelaz;
    bool krajReci = false;
};


void dodaj (Cvor* koren, string s) {
    for (char c : s) {
        //ako ne postoji karakter koji se nastavlja sa trenutnog cvora
        if (koren->prelaz.find(c) == koren->prelaz.end())
            koren->prelaz[c] = new Cvor();
        koren = koren->prelaz[c];
    }
    koren->krajReci = true;
}

bool nadji (Cvor* koren, string& s) {
    for (char c : s) {
        if (koren->prelaz.find(c) == koren->prelaz.end())
            return false;
        koren = koren->prelaz[c];
    }
    return koren->krajReci;
}

void oslobodi (Cvor* koren) {
    for (auto podstablo : koren->prelaz)
        oslobodi(podstablo.second);

    delete koren;
}

int main () {

    Cvor* stablo = new Cvor();
    string naredba, argument;
    while (true) {
        cin >> naredba >> argument;
        if (naredba == "nadji")
            cout << nadji(stablo, argument) << '\n';
        else if(naredba == "dodaj")
            dodaj(stablo, argument);
        else
            break;
    }


    oslobodi(stablo);





    return 0;
}

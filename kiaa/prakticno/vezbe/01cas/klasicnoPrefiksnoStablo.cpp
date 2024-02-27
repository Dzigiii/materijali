#include <iostream>
#include <queue>

using namespace std;


//struktura cvor
struct Cvor {
    int vr;
    Cvor* levo = nullptr;
    Cvor* desno = nullptr;

    Cvor(int v) {
        vr = v;
    }
};

//dodaj (koren, cvor)
Cvor* dodaj(Cvor* koren, int vr) {
    if (koren == nullptr) {
        return new Cvor(vr);
    }
    if (vr < koren->vr)
        koren->levo = dodaj(koren->levo, vr);
    else
        koren->desno = dodaj(koren->desno, vr);
    return koren;

}

void oslobodi (Cvor* koren) {
    if (koren == nullptr)
        return;
    oslobodi(koren->levo);
    oslobodi(koren->desno);

    delete koren;
}

void ispisi (Cvor* koren) {

    bool ulevo = true;

    deque <Cvor*> red;
    red.push_front(koren);

    int n = 1;
    while (!red.empty()) {
        Cvor* t;
        if (ulevo) {
            t = red.front();
            red.pop_front();

            if (t->levo != nullptr)
                red.push_back(t->levo);
            if (t->desno != nullptr)
                red.push_back(t->desno);
        }
        else {
            t = red.back();
            red.pop_back();

            if (t->desno != nullptr)
                red.push_front(t->desno);
            if (t->levo != nullptr)
                red.push_front(t->levo);
        }
        cout << t->vr << " ";
        n--;
        if (n == 0) {
            cout << '\n';
            n = red.size();
            ulevo = !ulevo;
        }


    }
}



int main () {

    int n, x;
    cin >> n;


    Cvor* stablo = nullptr;
    while (n--) {
        cin >> x;
        stablo = dodaj(stablo, x);
    }

    ispisi(stablo);
    oslobodi(stablo);

    return 0;
}

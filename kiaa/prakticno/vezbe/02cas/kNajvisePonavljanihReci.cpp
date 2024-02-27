#include <iostream>
#include <map>
#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;

struct Cvor {
    string rec;
    int broj_pojavljivanja = 0;
    unordered_map <char, Cvor*> cvorovi;
};


struct comparator {

    bool operator()(Cvor* a, Cvor* b) {
        return a->broj_pojavljivanja < b->broj_pojavljivanja;
    }
};

void oslobodiStablo(Cvor *koren) {
    if (koren == nullptr)
        return;

    for (auto &m : koren->cvorovi)
        oslobodiStablo(m.second);

    delete koren;
}

void dodaj_rec (Cvor* koren, string& s) {
    for (char c : s) {
        if (koren->cvorovi.find(c) == koren->cvorovi.end())
            koren->cvorovi[c] = new Cvor();
        koren = koren->cvorovi[c];
    }
    koren->broj_pojavljivanja++;
    koren->rec = s;
}

void put_words_into_heap (Cvor* koren, priority_queue <Cvor*, vector <Cvor* >, comparator> &heap) {

    if (!koren->rec.empty()) {
        heap.push(koren);
    }

//    dve opcije, prva je sa Strahinjinog koda koja je sasvim razumljiva ali mislim da je for each intuitivniji
//    auto begin = koren->cvorovi.begin();
//    auto end = koren->cvorovi.end();
//
//    while (begin != end) {
//        put_words_into_heap(begin->second, heap);
//        begin++; //iteriramo po pokazivacima na cvorove u mapi
//    }

    for (auto m : koren->cvorovi)
        put_words_into_heap(m.second, heap);

}

int main () {

    int n; //broj reci
    cin >> n;

    string niska;
    Cvor* stablo = new Cvor();

    while (n--) {
        cin >> niska;
        dodaj_rec(stablo, niska);
    }

    priority_queue <Cvor*, vector <Cvor* >, comparator> heap;
    put_words_into_heap(stablo, heap);

    int k;
    cout << "Koliko najcescih reci:\n";
    cin >> k;

    Cvor* tmp;
    while (k && (int)heap.size()) { //ako imako  >= k razlicitih reci, ispisace k, ako imamo manje od k --> tu utice heap.size()
        tmp = heap.top();
        heap.pop();

        cout << tmp->rec << " se pojavljuje " << tmp->broj_pojavljivanja << " puta!" << '\n';
        k--;
    }



    oslobodiStablo(stablo);

    return 0;
}



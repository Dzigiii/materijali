/* macro guard je obavezan, jer na vise mesta ukljucujemo
 * nasu biblioteku
 */
#ifndef KOMPLEKS_H
#define KOMPLEKS_H

#include <iostream>
#include <cmath>

/* klasa koja implementira kompleksne brojeve */
class KompleksniBroj {
private:
    double _re;
    double _im;
public:
    KompleksniBroj(double re, double im = 0);

    double Re() const;
    void Re(double re);

    double Im() const;
    void Im(double im);

    /* radi laksee implementacije akcija, modifikujemo standarne operator
     * tako da rezultate ne vracaju po vrednosti, vec da ih alociraju na hipu
     *
     * u uniji koja definise tipove cuvamo pokazivace na kompleksne brojeve, pa je
     * lakse da odmah definisemo operatore tako da rezultate kreiraju na hipu i 
     * vracaju pokazivace na njih
     */
    KompleksniBroj* operator +(const KompleksniBroj& kb) const;
    KompleksniBroj* operator -(const KompleksniBroj& kb) const;
    KompleksniBroj* operator *(const KompleksniBroj& kb) const;
    KompleksniBroj* operator /(const KompleksniBroj& kb) const;

    /* ilustracije radi, unarne operatore definisemo na standardni nacin, 
     * tj. tako da vracaju rezultate po vrednosti
     */
    KompleksniBroj operator -() const;
    KompleksniBroj operator ~() const;

    bool operator ==(const KompleksniBroj& kb) const;
    bool operator !=(const KompleksniBroj& kb) const;

    double Moduo() const;
    double Arg() const;

    void show(std::ostream& s) const;
};

std::ostream&
operator <<(std::ostream& s, const KompleksniBroj& kb);

#endif
/* leksicki analizator na zahtev
 * jedina njegova uloga je da tokenizuje ulaz i da tokenima koji imaju
 * pridruzeni tip podataka dodeli odgovarajuce vrednosti
 *
 * Dokument je podeljen na tri dela parovima karaktera %%
 * Prvi deo predstavlja komande flex-u, definicije stanja i regex-a i kao i 
 * delove koda koji se direktno kopiraju u lex.yy.c fajl
 * Drugi deo dokumenta predstavlja spisak regularnih izraza (sablona koje trazimo
 * u tekstu) i njima pridruzenih akcija
 * Treci deo dokumenta je opcioni i predstavlja implementaciju glavnog programa i svih
 * pomocnih funkcija
 *
 * Leksicki analizator u C-u dobijamo komandom:
 * flex -l lexer.l
 */
%option noyywrap

%{
    #include <iostream>
    #include <cstdlib>

    /* parser.tab.hpp uvek ukljucujemo na kraju 
     * BITNO: 
     * redosled include naredbi u leksure je bitan. ukoliko ste sigurni da vam je kod 
     * ispravan i kao gresku dobijate poruku da je neki tip podataka nedefinisan uvek prvo
     * proverite redosled include naredbi, pa tek onda popravljajte kod.
     */
    #include "parser.tab.hpp"    
%}

/* kada pisemo regularne izraze i njima pridruzena pravila
 * uvek prvo navodimo kljucne reci, pa onda pravila redom po rastucem nivou opstosti
 * kada bismo prvo naveli identifikatore, tada ne bismo imali mogucnost da prepoznamo kljucne reci,
 * jer bi svaka kljucna rec bila obelezena kao identifikator.
 * dakle, prvo navodimo kljucne reci (viseg prioriteta, manjeg nivoa opstosti), pa tek onda pravilo za 
 * identifikatore (nizeg prioriteta, viseg nivoa opstosti). identican pristup treba primeniti i u svakom
 * drugom slicnom slucaju.
 */
%%

print                       {return PRINT_T;}
[A-Za-z_]+[A-Za-z0-9_]*     {
                                /* yylval je unija koju smo definisali u parseru
                                 * u okviru unije cuvamo pokazivac na string, sto znaci da prvo negde
                                 * treba da ga alociramo da bismo mogli da zapamtimo njegov pokazivac
                                 * na hipu pravimo string pomocu njegovog konstruktora i operatora new.
                                 * s obzirom da smo na hipu napravili objekat, moracemo da ga unistimo onog
                                 * trenutka kada nam vise ne treba. ako objekat ne unistimo, curece nam memorija
                                 * a to je velika greska.
                                 */
                                yylval.s = new std::string(yytext);
                                return ID;
                            }
[0-9]+(\.[0-9]*)?           {
                                /* u uniju zapisujemo broj koji smo prepoznali na ulazu */
                                yylval.v = atof(yytext);
                                return BROJ;
                            }
"=="                        {return EQ;}
"!="                        {return NEQ;}
"<="                        {return LEQ;}
">="                        {return GEQ;}
[+*\<>;()=-]                {return *yytext;}
[ \t\n]                     { }
.                           {
                                /* ako repoznamo bilo sta sto nije deo naseg jezika (opisano leksickim pravilima),
                                 * prijavljujemo gresku i prekidamo program
                                 */
                                std::cerr << "Leksicka greska: "
                                    << (*yytext) << std::endl;
                                exit(EXIT_FAILURE);
                            }

%%


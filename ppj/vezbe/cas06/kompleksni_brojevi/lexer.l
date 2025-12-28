%option noyywrap

%{
    #include <iostream>
    #include <cstdlib>
    /* iako nigde ne koristimo map u lekseru, map je deo naseg parser.tab.hpp
     * dokumenta i moramo i ovde da je ukljuciemo
     */
    #include <map>
    /* obratite paznju na redosled include naredbi */
    #include "KompleksniBroj.hpp"
    #include "parser.tab.hpp"
%}

/* prvo definisemo kljucne reci 
 * zatim identifikatore
 * brojeve
 * operatore
 * itd
 */
%%

print   {return PRINT_T;}
arg     {return ARG_T;}
mod     {return MOD_T;}
re      {return RE_T;}
im      {return IM_T;}
def     {return DEF_T;}
[A-Za-z_][A-Za-z0-9_]* {
                            /* pravimo string objekat na hipu */
                            yylval.s = new std::string(yytext);
                            return ID;
                        }
[0-9]+(\.[0-9]*)?       {
                            /* pamtimo broj koji smo prepoznali */
                            yylval.v = atof(yytext);
                            return BROJ;
                        }
"=="            {   
                    /* slozene operaore uvek moramo da prepoznajemo kao literale
                    * u suporotnom, tekst =    = ce biti prepoznat kao ==, jer ignorisemo beline 
                    */
                    return EQ;
                }
"!="            {  return NEQ;}
[+*/<>(),;=~-]  {   
                    return *yytext;
                }
[ \t\n]         { /* ignorisemo beline */ }
.               {
                    /* ako prepoznamo bilo sta sto nije deo naseg jezika, prekidamo program 
                     * i stampamo odgovarajucu poruku
                     */
                    std::cerr<<"Leksicka greska: "
                        << (*yytext) << std::endl;
                    exit(EXIT_FAILURE);
                }

%%
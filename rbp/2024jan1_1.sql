SELECT D.INDEKS, D.IME, D.PREZIME, D.MESTORODJENJA, DECIMAL(AVG(
                                                            CASE
                                                                WHEN
                                                                    I.OCENA > 5 AND
                                                                    STATUS = 'o'
                                                                        THEN OCENA
                                                            END
                                                            ), 4, 2) PROSEK,
    D.IME || ' ' || D.PREZIME || ' (' ||
        CASE
            WHEN D.MESTORODJENJA like '%Beograd%'
                                       THEN 'Bg'
            ELSE
                D.MESTORODJENJA
        END|| ')' KOD
FROM DA.DOSIJE AS D
JOIN DA.ISPIT AS I
    ON I.INDEKS = D.INDEKS
JOIN DA.STUDIJSKIPROGRAM AS SP
    ON SP.ID = D.IDPROGRAMA AND SP.NAZIV = 'Informatika'
GROUP BY D.INDEKS, D.IME, D.PREZIME, D.MESTORODJENJA
HAVING SUM(
        CASE
            WHEN I.STATUS = 'x'
                THEN 1
                ELSE 0
       END
       ) < 2


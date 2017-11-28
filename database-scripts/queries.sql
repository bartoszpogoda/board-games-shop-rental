
SELECT * FROM ZamowieniaWewnetrzne;
SELECT * FROM ZamowieniaZewnetrzne;
SELECT * FROM ElemenyZamowienWewnetrznych;
SELECT * FROM ElementyZamowienZewnetrznych;
SELECT * FROM Wypozyczenia;

/*
	1. Znalezienie hasła klienta o danym adresie e-mail w celu uwierzytelnienia przy logowaniu.
*/
SELECT Haslo FROM klienci WHERE Email = 'fosgard@java.com';

/*
	2.	Znalezienie hasła pracownika o danym adresie e-mail w celu uwierzytelnienia przy logowaniu.
*/
SELECT Haslo FROM Pracownicy WHERE Email = 'mfurmonger5@discovery.com';

/*
	3.	Znalezienie wypożyczeń, których data zwrotu wypada w dniu bieżącym,
wraz z informacjami o kliencie oraz wypożyczonej grze planszowej.
*/

SELECT WypozyczenieID 'Numer wypozyczenia', Nazwa 'Nazwa gry', Imie, Nazwisko, Telefon FROM
Wypozyczenia w JOIN Klienci k ON w.KlientID = k.KlientID
JOIN GryPlanszowe g ON w.GraPlanszowaID = g.GraPlanszowaID
WHERE DATE (w.DataZwrotu) = DATE(NOW()) AND w.Status = 'Odebrane';


/*
	4.	Znalezienie informacji o grach planszowych, które
posiadają egzemplarze dostępne do wypożyczenia lub sprzedaży.
*/
SELECT
	GraPlanszowaID, Nazwa, Kategoria, CenaSprzedazy, IloscSprzedaz, CenaWypozyczenie, IloscWypozyczenie, DostawcaNazwa
FROM GryPlanszowe
WHERE IloscSprzedaz > 0 OR IloscWypozyczenie > 0;

/*
	5.	Znalezienie listy gier planszowych objętych konkretnym zamówieniem wewnętrznym (sprzedaż).
*/

SELECT GraPlanszowaID, Nazwa, Ilosc
FROM ZamowieniaWewnetrzne
	JOIN ElementyZamowienWewnetrznych USING (ZamowienieWewnetrzneID)
	JOIN GryPlanszowe USING(GraPlanszowaID)
WHERE ZamowienieWewnetrzneID = 1;

/*
	6.	Przeznaczenie gry planszowej dostępnej dotychczas do sprzedaży na
wypożyczenia, pod warunkiem, że jest dostępny co najmniej jeden egzemplarz.
*/
UPDATE GryPlanszowe
SET IloscWypozyczenie = IloscWypozyczenie + 1, IloscSprzedaz = IloscSprzedaz - 1
WHERE GraPlanszowaID = 1 AND IloscSprzedaz > 0;

/*
	7. Przesuniecie terminu zwrotu wypożyczenia o 5 dni
*/

UPDATE Wypozyczenia
SET DataZwrotu = DATE_ADD(DataZwrotu, INTERVAL 5 DAY)
WHERE WypozyczenieID = 1;


/*
	8. Usunięcie z systemu informacji o konkretnym wypożyczeniu – wraz z jego elementami.
 */

DELETE FROM ElementyZamowienZewnetrznych
WHERE ZamowienieZewnetrzneID = 3;

DELETE FROM ZamowieniaZewnetrzne
WHERE ZamowienieZewnetrzneID = 3;

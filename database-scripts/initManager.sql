-- Init manager

-- Haslo - zbyszek123
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Adam', 'Szefowicz', '2018-01-16 15:00:00', null, 'adam@szef.pl', 'fb3a0b05c2a75ecceb70f81ee9852a9f');
SELECT * FROM Pracownicy;
insert into Kierownicy (PracownikID) values (1);

-- Populate Pracownicy 
-- Note : Carlotta Corkish password is qwerty123

insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Carlotta', 'Corkish', '2014-10-09 18:02:47', null, 'ccorkish0@about.com', '3fc0a7acf087f549ac2b266baf94b8b1');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Cletis', 'Tomsen', '2015-05-15 06:52:29', null, 'ctomsen1@fema.gov', 'f5d089f9d015825aa540e3dd3ad0708a');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Vick', 'Motten', '2015-05-14 20:59:41', '2017-04-12 06:31:11', 'vmotten2@gnu.org', 'cc9913ef72687314d786e701b4a6faba');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Vidovik', 'Evequot', '2016-10-30 01:51:35', null, 'vevequot3@4shared.com', 'c81bbc7a2f06d7522870bdd6d61cbd45');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Hilario', 'Werrilow', '2016-03-25 22:36:41', null, 'hwerrilow4@ft.com', 'daee9565e69c427cf43684b1850fae80');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Mable', 'Furmonger', '2015-12-03 03:15:05', null, 'mfurmonger5@discovery.com', '9fb4ba0d6f4b6fb82bb7029ae3e4247c');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Carmella', 'Macconaghy', '2015-09-29 22:38:21', null, 'cmacconaghy6@g.co', '17c0bef669c97847ee2274dcc8a819cb');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Andree', 'Crace', '2016-10-17 02:56:02', null, 'acrace7@imgur.com', 'cdb2d1eb498efb4428a574f1de5e37c0');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Leonid', 'Hightown', '2016-10-16 03:08:46', null, 'lhightown8@goo.ne.jp', '5d14e6af976834e704d34764e91ae51e');
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Zuzana', 'Burton', '2016-01-12 22:19:03', null, 'zburton9@chronoengine.com', 'eb385e0999e633de408042879a9183be');

-- Populate Kierownicy

-- Haslo - zbyszek123
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Zbigniew', 'Szefowicz', '2013-01-01 22:19:03', null, 'zbychu@szef.pl', 'fb3a0b05c2a75ecceb70f81ee9852a9f');
SELECT * FROM Pracownicy;
insert into Kierownicy (PracownikID) values (1);
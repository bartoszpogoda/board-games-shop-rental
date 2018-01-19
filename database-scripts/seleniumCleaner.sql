
-- Clear last run
delete from elementyzamowienzewnetrznych;
delete from elementyzamowienwewnetrznych;
delete from zamowieniazewnetrzne;
delete from zamowieniawewnetrzne;
delete from wypozyczenia;
delete from gryplanszowe;
delete from dostawcy;
delete from kierownicy;
delete from pracownicy;
delete from klienci;

alter table elementyzamowienzewnetrznych AUTO_INCREMENT = 1;
alter table elementyzamowienwewnetrznych AUTO_INCREMENT = 1;
alter table zamowieniazewnetrzne AUTO_INCREMENT = 1;
alter table zamowieniawewnetrzne AUTO_INCREMENT = 1;
alter table wypozyczenia AUTO_INCREMENT = 1;
alter table gryplanszowe AUTO_INCREMENT = 1;
alter table dostawcy AUTO_INCREMENT = 1;
alter table kierownicy AUTO_INCREMENT = 1;
alter table pracownicy AUTO_INCREMENT = 1;
alter table klienci AUTO_INCREMENT = 1;

-- Init manager (adam@szef.pl, zbyszek123)
insert into Pracownicy (Imie, Nazwisko, DataZatrudnienia, DataZwolnienia, Email, Haslo) values ('Adam', 'Szefowicz', '2018-01-16 15:00:00', null, 'adam@szef.pl', 'fb3a0b05c2a75ecceb70f81ee9852a9f');
insert into Kierownicy (PracownikID) SELECT  PracownikID FROM Pracownicy WHERE Nazwisko='Szefowicz';

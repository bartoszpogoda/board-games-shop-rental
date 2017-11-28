CREATE TABLE Pracownicy (
	PracownikID INT(10) AUTO_INCREMENT PRIMARY KEY,
    Imie VARCHAR(20),
    Nazwisko VARCHAR(20),
    DataZatrudnienia DATE,
    DataZwolnienia DATE,
    Email VARCHAR(50),
    Haslo CHAR(32),
    
    INDEX(Email(10))
);

CREATE TABLE Kierownicy (
	KierownikID INT(10) AUTO_INCREMENT PRIMARY KEY,
    PracownikID INT(10) NOT NULL,
    
	FOREIGN KEY (PracownikID) REFERENCES Pracownicy(PracownikID)
);

CREATE TABLE Klienci (
	KlientID INT(10) AUTO_INCREMENT PRIMARY KEY,
    Imie VARCHAR(20),
    Nazwisko VARCHAR(20),
    Telefon VARCHAR(9),
    Email VARCHAR(50),
    Haslo CHAR(32),
    
    INDEX(Email(10))
);

CREATE TABLE Dostawcy (
	DostawcaNazwa VARCHAR(30) PRIMARY KEY,
    Telefon VARCHAR(9),
    Ulica VARCHAR(30),
    NumerDomu INT(3),
    NumerMieszkania INT(3),
    KodPocztowy CHAR(5),
    Miasto VARCHAR(30)
);

CREATE TABLE GryPlanszowe (
	GraPlanszowaID INT(10) AUTO_INCREMENT,
    Nazwa VARCHAR(30),
    Kategoria VARCHAR(30),
    CenaSprzedazy DECIMAL(10,2),
    IloscSprzedaz INTEGER(3),
    CenaWypozyczenie DECIMAL(10,2),
    IloscWypozyczenie INTEGER(3),
    DostawcaNazwa VARCHAR(30) NOT NULL,
    
    PRIMARY KEY (GraPlanszowaID),
    FOREIGN KEY (DostawcaNazwa) REFERENCES Dostawcy(DostawcaNazwa)
);

CREATE TABLE ZamowieniaZewnetrzne (
	ZamowienieZewnetrzneID INT(10) AUTO_INCREMENT,
    PracownikID INT(10) NOT NULL,
    DostawcaNazwa VARCHAR(30) NOT NULL,
    Status ENUM('Utworzone', 'Zrealizowane', 'Anulowane'),
    DataZlozenia DATE,
    
    PRIMARY KEY (ZamowienieZewnetrzneID),
    FOREIGN KEY (PracownikID) REFERENCES Pracownicy(PracownikID),
    FOREIGN KEY (DostawcaNazwa) REFERENCES Dostawcy(DostawcaNazwa)
);

CREATE TABLE ZamowieniaWewnetrzne (
	ZamowienieWewnetrzneID INT(10) AUTO_INCREMENT,
    KlientID INT(10) NOT NULL,
    DataZlozenia DATE,
    Status ENUM('Utworzone', 'Do odbioru', 'Zrealizowane', 'Anulowane'),
    
    PRIMARY KEY (ZamowienieWewnetrzneID),
    FOREIGN KEY (KlientID) REFERENCES Klienci(KlientID)
);

CREATE TABLE Wypozyczenia (
	WypozyczenieID INT(10) AUTO_INCREMENT,
    KlientID INT(10) NOT NULL,
    GraPlanszowaID INT(10) NOT NULL,
    DataWypozyczenia DATE,
    DataZwrotu DATE,
    KosztWypozyczenia DECIMAL(10,2),
    Status ENUM('Utworzone', 'Do odbioru', 'Odebrane', 'Zrealizowane', 'Anulowane'),
    
    PRIMARY KEY (WypozyczenieID),
    FOREIGN KEY (KlientID) REFERENCES Klienci(KlientID),
    FOREIGN KEY (GraPlanszowaID) REFERENCES GryPlanszowe(GraPlanszowaID),
    INDEX (DataZwrotu)
);

CREATE TABLE ElementyZamowienWewnetrznych (
	ZamowienieWewnetrzneID INT(10) NOT NULL,
    GraPlanszowaID INT(10) NOT NULL,
    Ilosc INT(3),
    
    FOREIGN KEY(ZamowienieWewnetrzneID) REFERENCES ZamowieniaWewnetrzne(ZamowienieWewnetrzneID),
    FOREIGN KEY(GraPlanszowaID) REFERENCES GryPlanszowe(GraPlanszowaID)
);

CREATE TABLE ElementyZamowienZewnetrznych (
	ZamowienieZewnetrzneID INT(10) NOT NULL,
    GraPlanszowaID INT(10) NOT NULL,
    Cena DECIMAL(10,2),
    Ilosc INT(3),
    
    FOREIGN KEY(ZamowienieZewnetrzneID) REFERENCES ZamowieniaZewnetrzne(ZamowienieZewnetrzneID),
    FOREIGN KEY(GraPlanszowaID) REFERENCES GryPlanszowe(GraPlanszowaID)
);

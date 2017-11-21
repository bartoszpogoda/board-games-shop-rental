SELECT * FROM klienci WHERE Email = 'fosgard@java.com';

SELECT WypozyczenieID 'Numer wypozyczenia', Nazwa 'Nazwa gry', Imie, Nazwisko, Telefon FROM 
Wypozyczenia w JOIN Klienci k ON w.KlientID = k.KlientID
JOIN GryPlanszowe g ON w.GraPlanszowaID = g.GraPlanszowaID
WHERE DATE(DataZwrotu) = DATE(NOW());
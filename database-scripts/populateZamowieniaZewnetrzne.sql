INSERT INTO zamowieniazewnetrzne (PracownikID, DostawcaNazwa, Status, DataZlozenia)
VALUES ('5', 'Cormier-Langosh' ,'Utworzone', '17-11-20');

INSERT INTO zamowieniazewnetrzne (PracownikID, DostawcaNazwa, Status, DataZlozenia)
VALUES ('3', 'Medhurst and Sons' ,'Zrealizowane', '16-06-15');

INSERT INTO zamowieniazewnetrzne (PracownikID, DostawcaNazwa, Status, DataZlozenia)
VALUES ('9', 'Medhurst and Sons' ,'Anulowane', '17-08-14');

-- Populate elements

INSERT INTO ElementyZamowienZewnetrznych VALUES (1, 23, 105.40, 5);
INSERT INTO ElementyZamowienZewnetrznych VALUES (1, 14, 150.00, 3);

INSERT INTO ElementyZamowienZewnetrznych VALUES (2, 100, 80.10, 2);
INSERT INTO ElementyZamowienZewnetrznych VALUES (2, 102, 85.20, 3);
INSERT INTO ElementyZamowienZewnetrznych VALUES (2, 105, 105.00, 3);
INSERT INTO ElementyZamowienZewnetrznych VALUES (2, 106, 85.00, 1);

INSERT INTO ElementyZamowienZewnetrznych VALUES (3, 50, 80.00, 8);
INSERT INTO ElementyZamowienZewnetrznych VALUES (3, 51, 62.59, 1);
INSERT INTO ElementyZamowienZewnetrznych VALUES (3, 52, 57.99, 3);

-- View results

SELECT * FROM zamowieniazewnetrzne;
SELECT * FROM elementyzamowienzewnetrznych;

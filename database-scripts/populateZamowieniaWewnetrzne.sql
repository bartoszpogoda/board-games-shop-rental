INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('13', '15-11-20', 'Zrealizowane');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('654', '16-09-25', 'Zrealizowane');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('500', '17-10-07', 'Anulowane');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('13', '16-09-11', 'Zrealizowane');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('429', '16-01-03', 'Zrealizowane');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('500', '17-10-07', 'Do odbioru');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('423', '17-11-21', 'Do odbioru');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('965', '17-11-21', 'Do odbioru');

INSERT INTO zamowieniawewnetrzne (KlientID, DataZlozenia, Status)
VALUES ('14', '17-11-17', 'Do odbioru');

-- Populate elements

INSERT INTO ElementyZamowienWewnetrznych VALUES (1, 7, 2);
INSERT INTO ElementyZamowienWewnetrznych VALUES (1, 5, 1);

INSERT INTO ElementyZamowienWewnetrznych VALUES (3, 54, 1);

INSERT INTO ElementyZamowienWewnetrznych VALUES (4, 33, 1);
INSERT INTO ElementyZamowienWewnetrznych VALUES (4, 34, 1);

-- View results

SELECT * FROM zamowieniawewnetrzne;
SELECT * FROM elementyzamowienwewnetrznych;

-- Nalozi
-- 1-3 Client
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'nesto1@gmail.com', 'sifra123', 1, 0, 1);
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'nesto2@gmail.com', 'sifra123', 1, 0, 2);
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'nesto3@gmail.com', 'sifra123', 1, 0, 3);
-- 4 Admin
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'admin@gmail.com', 'admin123', 1, 0, 4);
-- 5 BoatOwner
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'boatowner1@gmail.com', 'sifra123', 1, 0, 5);
-- 6 CottageOwner
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'cottageowner1@gmail.com', 'sifra123', 1, 0, 6);
-- 7 Instructor
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (accIdSeqGen.nextval, 'instructor1@gmail.com', 'sifra123', 1, 0, 7);
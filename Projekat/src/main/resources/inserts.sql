
-- ADRESE
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 1', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 2', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 3', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 4', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 5', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 6', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 7', 'Novi Sad', 'Srbija');

-- Client
INSERT INTO users (id, type, name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'CL', 'Jane', 'Austen', '1234567891', 1, 0, 0, NULL);
INSERT INTO users (id, type, name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'CL', 'Pamela', 'Trump', '1234467891', 2, 0, 0, NULL);
INSERT INTO users (id, type,name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'CL', 'Jacob', 'White', '1234367891', 3, 0, 0, NULL);

-- Admin
INSERT INTO users (id, type,name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'AD', 'Jacobson', 'White', '1234367892', 4, NULL, NULL, NULL);

-- BoatOwner
INSERT INTO users (id, type,name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'BO', 'Jakobijan', 'Levis', '1234367893', 5, NULL, NULL, NULL);

-- CottageOwner
INSERT INTO users (id, type,name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'CO', 'Milorad', 'Milosavljević', '1234367894', 6, NULL, NULL, NULL);

-- Instructor
INSERT INTO users (id, type,name, surname, phone, address_id, penalties, loyalty_score, biography)
    VALUES (nextval('user_id'), 'IN', 'Jakša', 'Jakić', '1234367895', 7, NULL, NULL, NULL);

-- Nalozi
-- 1-3 Client
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'),'nesto1@gmail.com', 'sifra123', TRUE, FALSE, 1);
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'),'nesto2@gmail.com', 'sifra123', TRUE, FALSE, 2);
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'nesto3@gmail.com', 'sifra123', TRUE, FALSE, 3);
-- 4 Admin
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'admin@gmail.com', 'admin123', TRUE, FALSE, 4);
-- 5 BoatOwner
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'boatowner1@gmail.com', 'sifra123', TRUE, FALSE, 5);
-- 6 CottageOwner
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'cottageowner1@gmail.com', 'sifra123', TRUE, FALSE, 6);
-- 7 Instructor
INSERT INTO accounts (id, email, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'),'instructor1@gmail.com', 'sifra123', TRUE, FALSE, 7);
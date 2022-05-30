
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


-- Cottages

--AdditionalService
INSERT INTO additional_services (id, name) VALUES (nextval('as_id_seq'), 'WIFI');


--INSERT INTO cottages (id, name, address_id, description, rules, price, cancellation_terms,
--    availability_start, availability_end, is_deleted, number_of_rooms, number_of_beds)
--    VALUES (nextval('service_id_seq'), 'Morava Vila', 1, "Ovo je opis Vile Dunav",
--    "Pravila pravila, da bi me udavila, ili kičmu savila i skroz pošašavila.", 500, "Neki uslovi za otkaz.", 2017-01-13T17:09:42.411, 2017-01-13T17:09:42.411, false, 10, 11);




--INSERT INTO public.cottages(
--	id, number_of_beds, number_of_rooms, availability_end,
--	availability_start,
--	description, is_deleted, name, price, rules, address_id, cancellation_terms)
--	VALUES (nextval('service_id_seq'), 10, 15, 1999-01-08 04:05:06, 1999-01-08 04:05:06,
--	"Najlepsa vikendica, samo nam dodjite", false, "Vila Morava", 500, "Pravila", 1, "Uslovi otkaza");

INSERT INTO public.cottages(
	id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms)
	VALUES (nextval('service_id_seq'), 10, 15,
			'2016-06-22 19:10:25-07',
			'2016-06-22 19:10:25-07',
			'Najlepsa vikendica, samo nam dodjite', false, 'Vila Morava', 500, 'Pravila', 1, 'Uslovi otkaza');

INSERT INTO public.cottages(
	id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms)
	VALUES (nextval('service_id_seq'), 10, 15,
			'2016-06-22 19:10:25-07',
			'2016-06-22 19:10:25-07',
			'Najlepsa vikendica na Dunavu, samo nam dodjite', false, 'Vila Dunav', 300, 'Pravila', 1, 'Uslovi otkaza');

INSERT INTO public.cottages(
	id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms)
	VALUES (nextval('service_id_seq'), 10, 15,
			'2016-06-22 19:10:25-07',
			'2016-06-22 19:10:25-07',
			'Veoma lepa kuća', false, 'Vila Dragulj', 500, 'Pravila', 1, 'Uslovi otkaza');

INSERT INTO additionals (service_id, additional_id) VALUES (1, 1);

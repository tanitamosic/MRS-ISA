INSERT INTO public.roles(
    id, name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO public.roles(
    id, name)
VALUES (2, 'ROLE_INSTRUCTOR');

INSERT INTO public.roles(
    id, name)
VALUES (3, 'ROLE_CLIENT');

INSERT INTO public.roles(
    id, name)
VALUES (4, 'ROLE_COTTAGEOWNER');

INSERT INTO public.roles(
    id, name)
VALUES (5, 'ROLE_BOATOWNER');


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
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 11', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 8', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 9', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 10', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 14', 'Novi Sad', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 15', 'Beočin', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 13', 'Beograd', 'Srbija');
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 12', 'Novi Sad', 'Srbija');

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
INSERT INTO accounts (id, username, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'),'nesto1@gmail.com', 'sifra123', TRUE, FALSE, 1);
INSERT INTO accounts (id, username, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'),'nesto2@gmail.com', 'sifra123', TRUE, FALSE, 2);
INSERT INTO accounts (id, username, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'nesto3@gmail.com', 'sifra123', TRUE, FALSE, 3);
-- 4 Admin
INSERT INTO public.accounts(id, activated, deleted, last_password_reset_date, password, username, user_id)
    VALUES (nextval('acc_id'), TRUE, FALSE, '2022-06-06 07:00:00', 'admin123', 'admin@gmail.com', 4);
-- 5 BoatOwner
INSERT INTO accounts (id, username, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'boatowner1@gmail.com', 'sifra123', TRUE, FALSE, 5);
-- 6 CottageOwner
INSERT INTO accounts (id, username, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'), 'cottageowner1@gmail.com', 'sifra123', TRUE, FALSE, 6);
-- 7 Instructor
INSERT INTO accounts (id, username, password, activated, deleted, user_id)
    VALUES (nextval('acc_id'),'instructor1@gmail.com', 'sifra123', TRUE, FALSE, 7);


-- ACCOUNTS ROLES
INSERT INTO public.account_role(account_id, role_id)
    VALUES (1, 3);
INSERT INTO public.account_role(account_id, role_id)
    VALUES (2, 3);
INSERT INTO public.account_role(account_id, role_id)
    VALUES (3, 3);
INSERT INTO public.account_role(account_id, role_id)
    VALUES (4, 1);
INSERT INTO public.account_role(account_id, role_id)
    VALUES (5, 5);
INSERT INTO public.account_role(account_id, role_id)
    VALUES (6, 4);
INSERT INTO public.account_role(account_id, role_id)
    VALUES (7, 2);


--AdditionalService
INSERT INTO additional_services (id, name, price) VALUES (nextval('as_id_seq'), 'WIFI', 10);


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

INSERT INTO public.photos(id, asset_path, service_id)
    VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica1.jpg', 1);

INSERT INTO public.photos(id, asset_path, service_id)
    VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica2.jpg', 2);

INSERT INTO public.photos(id, asset_path, service_id)
    VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica3.jpg', 3);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica4.jpg', 4);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica5.jpg', 5);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica6.jpg', 6);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica7.jpg', 7);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica8.jpg', 8);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica9.jpg', 9);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica10.jpg', 10);

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica11.jpg', 1);

INSERT INTO public.cottages(
	id, owner_id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
	VALUES (nextval('service_id_seq'), 6, 10, 15,
			'2025-10-22 00:00:00',
			'2016-06-22 00:00:00',
			'Najlepsa vikendica, samo nam dodjite', false, 'Vila Morava', 500, 'Pravila', 8, 'Uslovi otkaza', 1, 5);

INSERT INTO public.cottages(
	id, owner_id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
	VALUES (nextval('service_id_seq'), 6, 10, 15,
			'2025-10-22 00:00:00',
            '2016-06-22 00:00:00',
			'Najlepsa vikendica na Dunavu, samo nam dodjite', false, 'Vila Dunav', 300, 'Pravila', 9, 'Uslovi otkaza', 2, 4.1);

INSERT INTO public.cottages(
	id, owner_id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
	VALUES (nextval('service_id_seq'), 6, 10, 15,
			'2025-10-22 00:00:00',
            '2016-06-22 00:00:00',
			'Veoma lepa kuća', false, 'Vila Dragulj', 500, 'Pravila', 10, 'Uslovi otkaza', 3, 4.3);

INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 6, 14,
                 			'2023-10-22 00:00:00',
                            '2021-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 4', 432, 'Pravila', 6,
                 'Uslovi otkaza', 4, 5);
INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 3, 6,
                 			'2023-10-22 00:00:00',
                            '2019-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 5', 552, 'Pravila', 2,
                 'Uslovi otkaza', 5, 4);
INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 11, 13,
                 			'2026-10-22 00:00:00',
                            '2016-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 6', 237, 'Pravila', 9,
                 'Uslovi otkaza', 6, 4.6);
INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 5, 6,
                 			'2024-10-22 00:00:00',
                            '2016-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 7', 165, 'Pravila', 10,
                 'Uslovi otkaza', 7, 5);
INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 3, 13,
                 			'2023-10-22 00:00:00',
                            '2016-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 8', 730, 'Pravila', 9,
                 'Uslovi otkaza', 8, 2);
INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 4, 5,
                 			'2024-10-22 00:00:00',
                            '2016-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 9', 497, 'Pravila', 4,
                 'Uslovi otkaza', 9, 3);
INSERT INTO public.cottages(
                 	id, owner_id, number_of_beds, number_of_rooms, availability_end,
                 	availability_start,
                 	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
                 	VALUES (nextval('service_id_seq'), 6, 9, 7,
                 			'2023-10-22 00:00:00',
                            '2022-06-22 00:00:00',
                 'Najlepsa vikendica, samo nam dodjite', false, 'Vikendica 10', 475, 'Pravila', 7,
                 'Uslovi otkaza', 10, 5);

INSERT INTO additionals (service_id, additional_id) VALUES (1, 1);

-- Jedna avantura


--INSERT INTO public.adventures(
--	id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, fishing_equipment)
--	VALUES (nextval('service_id_seq'), '2020-06-16 15:46:27',
--                 			'2020-06-05 15:46:27', 'Otkaz', 'Opis', false, 'Avantura 1', 200, 'Pravila pravila', 11, 1, 5, 'Oprema');

-- Jedan Cottage


INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/cottages/vikendica12.jpg', 11);

INSERT INTO public.cottages(
	id, owner_id, number_of_beds, number_of_rooms, availability_end,
	availability_start,
	description, is_deleted, name, price, rules, address_id, cancellation_terms, photo_id, rating)
	VALUES (nextval('service_id_seq'), 6, 10, 15,
			'2020-10-22 00:00:00',
            '2016-06-22 00:00:00',
			'Najlepsa vikendica, samo nam dodjite', false, 'Vila Nova lepa', 500, 'Pravila', 12, 'Uslovi otkaza', 12, 4);

-- BRODOVI


INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/boats/boat1.jpg', 12);

INSERT INTO public.boats(
	id, owner_id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, engine_power, fishing_equipment, length, max_speed, navigational_equipment, number_of_engines, type, rating)
	VALUES (nextval('service_id_seq'), 5, '2023-06-30 19:10:25',
			'2023-05-02 19:10:25', 'Ukoliko se klijenti ponašaju neodgovorno, ili postoje problemi sa vremenom moguće je otkazivanje.',
			'Brod je veoma lep i prostran sa dosta mesta za sve koji su avanturisti i žele da dožive pravo životno iskustvo',
			false, 'Brod Sanja1', 100, 'Nije dozvoljeno uskakanje u vodu bez prethodne konsultacije sa glavnom i odgovornom osobom na brodu.',
			13, 13, 4, 200.0, 'Štapovi za pecanje, plovci svih gramaža, olova, udice, živi mamci, varalice.', 12, 50, 'GPS, WHF, Satelitski telefon', 2, 'Gliser', 5);

-----------------------------------------------------------------------------------------------------------------------



INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/boats/boat2.jpg', 13);

INSERT INTO public.boats(
	id, owner_id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, engine_power, fishing_equipment, length, max_speed, navigational_equipment, number_of_engines, type, rating)
	VALUES (nextval('service_id_seq'), 5, '2023-07-25 19:10:25',
			'2023-05-20 19:10:25', 'Ukoliko se klijenti ponašaju neodgovorno, ili postoje problemi sa vremenom moguće je otkazivanje.',
			'Brod je veoma lep i prostran sa dosta mesta za sve koji su avanturisti i žele da dožive pravo životno iskustvo',
			false, 'Brod Alisa2', 100, 'Nije dozvoljeno uskakanje u vodu bez prethodne konsultacije sa glavnom i odgovornom osobom na brodu.',
			14, 14, 4, 200.0, 'Štapovi za pecanje, plovci svih gramaža, olova, udice, živi mamci, varalice.', 12, 50, 'GPS, WHF, Satelitski telefon', 2, 'Gliser', 4);

-----------------------------------------------------------------------------------------------------------------------



INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/boats/boat3.jpg', 14);

INSERT INTO public.boats(
	id, owner_id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, engine_power, fishing_equipment, length, max_speed, navigational_equipment, number_of_engines, type, rating)
	VALUES (nextval('service_id_seq'), 5, '2023-06-25 19:10:25',
			'2023-06-20 19:10:25', 'Ukoliko se klijenti ponašaju neodgovorno, ili postoje problemi sa vremenom moguće je otkazivanje.',
			'Brod je veoma lep i prostran sa dosta mesta za sve koji su avanturisti i žele da dožive pravo životno iskustvo',
			false, 'Brod WIEN1', 100, 'Nije dozvoljeno uskakanje u vodu bez prethodne konsultacije sa glavnom i odgovornom osobom na brodu.',
			15, 15, 4, 200.0, 'Štapovi za pecanje, plovci svih gramaža, olova, udice, živi mamci, varalice.', 12, 50, 'GPS, WHF, Satelitski telefon', 2, 'Gliser', 5);

-----------------------------------------------------------------------------------------------------------------------


--AVANTURE
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 16', 'Beograd', 'Srbija');

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/adventures/adventure1.jpg', 15);

INSERT INTO public.adventures(
	id, owner_id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, fishing_equipment, rating)
	VALUES (nextval('service_id_seq'), 7, '2023-06-28 19:10:25',
			'2020-06-15 19:10:25', 'Ukoliko se klijenti ponašaju neodgovorno, ili postoje problemi sa vremenom moguće je otkazivanje.',
			'Avantura je veoma lepa i zanimljiva za sve koji su avanturisti i žele da dožive pravo životno iskustvo.',
			false, 'Avantura Zivota', 50, 'Pravila pravila, da bi me udavila. Neophodno je da klijenti u svakom momentu slušaju instruktora.',
			16, 16, 4, 'Štapovi za pecanje, plovci svih gramaža, olova, udice, živi mamci, varalice.', 5);

-----------------------------------------------------------------------------------------------------------------------
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 17', 'Beograd', 'Srbija');

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/adventures/adventure2.jpg', 16);

INSERT INTO public.adventures(
	id, owner_id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, fishing_equipment, rating)
	VALUES (nextval('service_id_seq'), 7, '2023-07-10 19:10:25',
			'2020-07-01 19:10:25', 'Ukoliko se klijenti ponašaju neodgovorno, ili postoje problemi sa vremenom moguće je otkazivanje.',
			'Avantura je veoma lepa i zanimljiva za sve koji su avanturisti i žele da dožive pravo životno iskustvo.',
			false, 'Avantura na Fruškoj gori', 50, 'Pravila pravila, da bi me udavila. Neophodno je da klijenti u svakom momentu slušaju instruktora.',
			17, 17, 4, 'Štapovi za pecanje, plovci svih gramaža, olova, udice, živi mamci, varalice.', 4);

-----------------------------------------------------------------------------------------------------------------------
INSERT INTO addresses (id, street, city, state)
    VALUES (nextval('adr_id'), 'Dunavska 18', 'Beograd', 'Srbija');

INSERT INTO public.photos(
	id, asset_path, service_id)
	VALUES (nextval('photo_id_seq'), 'img/adventures/adventure3.jpg', 17);

INSERT INTO public.adventures(
	id, owner_id, availability_end, availability_start, cancellation_terms, description, is_deleted, name, price, rules, address_id, photo_id, capacity, fishing_equipment, rating)
	VALUES (nextval('service_id_seq'), 7, '2023-06-25 19:10:25',
			'2020-06-20 19:10:25', 'Ukoliko se klijenti ponašaju neodgovorno, ili postoje problemi sa vremenom moguće je otkazivanje.',
			'Avantura je veoma lepa i zanimljiva za sve koji su avanturisti i žele da dožive pravo životno iskustvo.',
			false, 'Avantura na Crvenom Čotu', 50, 'Pravila pravila, da bi me udavila. Neophodno je da klijenti u svakom momentu slušaju instruktora.',
			18, 18, 4, 'Štapovi za pecanje, plovci svih gramaža, olova, udice, živi mamci, varalice.', 5);

-----------------------------------------------------------------------------------------------------------------------

-- In deze data.sql gebruiken we expliciete id's
INSERT INTO wall_bracket (id, size, adjustable, name, price) VALUES (1001, '25X32', false, 'LG bracket', 32.23),
(1002, '25X32/32X40', true, 'LG bracket', 32.23),
(1003, '25X25', false, 'Philips bracket', 32.23),
(1004, '25X32/32X40', true, 'Nikkei bracket', 32.23),
(1005, '25X32', false, 'Nikkei bracket', 32.23);

INSERT INTO cimodule (id, name, type, price) VALUES (1001, 'universal CI-module', '23JI12', 32.5);

INSERT INTO remote_controller (id, compatible_with, battery_type, name, brand, price, original_stock) VALUES (1001, 'NH3216SMART', 'AAA', 'Nikkei HD smart TV controller', 'Nikkei', 12.99, 235885),
                                                                                                             (1002, '43PUS6504/12/L', 'AA', 'Philips smart TV controller', 'Philips', 12.99, 235885),
                                                                                                             (1003, 'OLED55C16LA', 'AAA', 'OLED55C16LA TV controller', 'LG', 12.99, 235885);

INSERT INTO television (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold) VALUES (1001, 'NH3216SMART', 'Nikkei', 'HD smart TV', 159, 32, 100, 'LED', 'HD ready',  true, true, false, false, false, false, 235885, 45896),
                                                                                                                                                                                                         (1002, '43PUS6504/12/L', 'Philips', '4K UHD LED Smart Tv', 379, 43, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 8569452, 5685489),
                                                                                                                                                                                                       (1003, '43PUS6504/12/M', 'Philips', '4K UHD LED Smart Tv', 379, 50, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 345549, 244486),
                                                                                                                                                                                                         (1004, '43PUS6504/12/S', 'Philips', '4K UHD LED Smart Tv', 379, 58, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 6548945, 4485741),
                                                                                                                                                                                                         (1005, 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989, 55, 100, 'OLED', 'ULTRA HD',  true, true, true, true, true, false, 50000, 20500);

INSERT INTO television_wall_bracket(television_id, wall_bracket_id) values (1005, 1001),
                                                                           (1005, 1002),
                                                                           (1002, 1003),
                                                                           (1003, 1003),
                                                                           (1004, 1003),
                                                                           (1001, 1004),
                                                                           (1001, 1005);



DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS festivals CASCADE;
DROP TABLE IF EXISTS partys CASCADE;

CREATE TABLE events
(
    eventID SERIAL PRIMARY KEY,
    eventName VARCHAR(100),
    eventLocation VARCHAR(50),
    availableTickets INT,
    eventStartDate DATE,
    eventEndDate DATE,
    eventPrice DOUBLE PRECISION
);
CREATE TABLE festivals
(
    festivalID SERIAL PRIMARY KEY,
    eventID INT REFERENCES events(eventID), -- a foreign key relationship to Event
    festivalName VARCHAR(100),
    festivalLocation VARCHAR(50),
    artistName VARCHAR(100),
    campingAvailable BOOLEAN,
    festivalStartDate DATE,
    festivalEndDate DATE,
    festivalPrice DOUBLE PRECISION
);
CREATE TABLE partys
(
    partyID SERIAL PRIMARY KEY,
    eventID INT REFERENCES events(eventID), -- a foreign key relationship to Event
    partyName VARCHAR(100),
    partyLocation VARCHAR(50),
    partyStartDate DATE,
    partyEndDate DATE,
    partyPrice DOUBLE PRECISION,
    dresscode VARCHAR(50),
    djName VARCHAR(100)

);


INSERT INTO events (eventName, eventLocation, eventPrice, availableTickets, eventStartDate, eventEndDate)
VALUES ('hamid event', 'Rotterdam', 50.0, 1000, '2024-02-01', '2024-02-05');

INSERT INTO festivals (festivalName, festivalLocation, artistName, campingAvailable, festivalStartDate, festivalEndDate, festivalPrice)
VALUES ('santi festival', 'Utrecht', 30.0, false, '2024-01-01', '2024-01-02',120);


INSERT INTO partys (partyName, partyLocation, partyPrice,partyStartDate, partyEndDate, dressCode, djName)
VALUES ('fanti party', 'Amsterdam', 30.0, '2024-02-03', '2024-02-05', 'carnaval kleding', 'djKivit');

-- password = "password" (dit comment is een security lek, zet dit nooit in je code.
-- Als je hier je plaintext password niet meer weet, moet je een nieuw password encrypted)
INSERT INTO users (username, password, email, enabled) VALUES ('user', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK','user@test.nl', TRUE);
INSERT INTO users (username, password, email, enabled) VALUES ('admin', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK', 'admin@test.nl', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');


DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS festivals CASCADE;
DROP TABLE IF EXISTS partys CASCADE;

CREATE TABLE events
(
   eventID SERIAL PRIMARY KEY,
   eventName VARCHAR(100),
   eventLocation VARCHAR(50),
   eventPrice DOUBLE PRECISION,
   availableTickets INT,
   eventDate DATE
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
-- -- adding data insert to event table
-- INSERT INTO Event (eventName, eventLocation, eventPrice, availableTickets, eventDate)
-- VALUES ('project x event', 'Amsterdam', 50.0, 1000, '2024-07-01');
--
-- INSERT INTO Event (eventName, eventLocation, eventPrice, availableTickets, eventDate)
-- VALUES ('Jantjes verjaardag event', 'Arnhem', 29.0, 100, '2024-04-15');
--
-- INSERT INTO Event (eventName, eventLocation, eventPrice, availableTickets, eventDate)
-- VALUES ('Students event', 'Berlin', 20.0, 500, '2024-11-15');
--
-- -- adding data insert to Festival table
-- INSERT INTO Festival (eventID, festivalName, festivalLocation, artistName, campingAvailable, festivalStartDate, festivalEndDate, festivalPrice)
-- VALUES (1, 'Milkshake Amsterdam', 'Amsterdam', 'Queen Amsterdam', false, '2024-07-01', '2024-07-23', 50.0);
--
-- INSERT INTO Festival (eventID, festivalName, festivalLocation, artistName, campingAvailable, festivalStartDate, festivalEndDate, festivalPrice)
-- VALUES (1, 'Milkshake Utrecht', 'Utrecht', 'Queen Utrecht', false, '2024-07-01', '2024-09-02', 50.0);
--
-- -- -- adding data to Party table
-- -- INSERT INTO Party (eventID, partyName, partyDate, djName)
-- -- VALUES (2, 'Klubnacht Berlin', '2024-10-09', 'DJ Hamid');
--
-- -- INSERT INTO Party (eventID, partyName, partyDate, djName)
-- -- VALUES (2, 'White Party Amsterdam', '2024-01-15', 'DJ capriati');
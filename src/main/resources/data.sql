INSERT INTO events (event_name, event_location, event_type, event_price, available_tickets, event_start_date, event_end_date, event_Description)
VALUES ('mark event', 'utrecht', 'festival',53.0, 10040, '2024-02-04', '2024-02-07', 'dit event is alleen voor mark en frans, het is verplicht om in groene kleding te komen'),
       ('hamid event', 'Rotterdam','party', 50.0, 1000, '2024-02-01', '2024-02-05', 'dit event is alleen voor novi studenten, ' ||
                                                                                    'het is verplicht om met een hoge hoed te komen');


INSERT INTO events (event_name, event_location, event_type, event_price, available_tickets, event_start_date, event_end_date, event_description)
VALUES ('bas event', 'utrecht', 'festival',53.0, 10040, '2024-02-04', '2024-02-07', 'dit event is alleen voor mark en frans, het is verplicht om in groene kleding te komen'),
       ('eric event', 'Rotterdam','party', 50.0, 1000, '2024-02-01', '2024-02-05', 'dit event is alleen voor novi studenten, ' ||
                                                                                   'het is verplicht om met een hoge hoed te komen');
INSERT INTO festivals (artist_name, camping_available,event_id)
VALUES ('chris artist ', true, (SELECT event_id FROM Events
                                WHERE event_name='mark event'));

INSERT INTO partys (dj_name, dress_code,event_id)
VALUES ('dj hamid ', 'groene broek', (SELECT event_id FROM Events
                                      WHERE event_name='hamid event'));

INSERT INTO festivals (artist_name, camping_available,event_id)
VALUES ('micha artist ', true, (SELECT event_id FROM Events
                                WHERE event_name='bas event'));

INSERT INTO partys (dj_name, dress_code,event_id)
VALUES ('dj hamid ', 'groene broek', (SELECT event_id FROM Events
                                      WHERE event_name='eric event'));

-- password = "password" (dit comment is een security lek, zet dit nooit in je code.
-- Als je hier je plaintext password niet meer weet, moet je een nieuw password encrypted)
INSERT INTO users (username, password, email, enabled) VALUES ('user', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK','user@test.nl', TRUE);
INSERT INTO users (username, password, email, enabled) VALUES ('admin', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK', 'admin@test.nl', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');




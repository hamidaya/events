-- Template for generating random mock data

-- Adjust and execute as needed

-- Function to generate random string

CREATE OR REPLACE FUNCTION random_string(length INT)

RETURNS VARCHAR AS $$

DECLARE

    chars VARCHAR[] := ARRAY['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];

    result VARCHAR := '';

    i INT;

BEGIN

    FOR i IN 1..length LOOP

        result := result || chars[1 + random() * 26];

    END LOOP;

    RETURN result;

END;

$$ LANGUAGE plpgsql;

-- Inserting random data into Event table

DO $$ 

DECLARE

    i INT;

BEGIN

    FOR i IN 1..100 LOOP

        INSERT INTO events (event_Name, event_Location, event_Price, available_Tickets, event_Start_Date)

        VALUES (

            random_string(10), 

            random_string(8) || ' City', 

            random() * 100, 

            floor(random() * 1000), 

            '2024-01-01'::DATE + (random() * 365)::INT

        );

    END LOOP;

END $$;

-- Inserting random data into Festival table

DO $$ 

DECLARE

    i INT;

BEGIN

    FOR i IN 1..100 LOOP

        INSERT INTO festivals (eventID, festivalName, festivalLocation, artistName, campingAvailable, festivalStartDate, festivalEndDate, festivalPrice)

        VALUES (

            i, 

            random_string(15) || ' Festival', 

            random_string(8) || ' City', 

            random_string(12) || ' Artist', 

            random() < 0.5, 

            '2024-01-01'::DATE + (random() * 365)::INT, 

            '2024-01-01'::DATE + (random() * 365)::INT, 

            random() * 100

        );

    END LOOP;

END $$;

-- Inserting random data into Party table

DO $$ 

DECLARE

    i INT;

BEGIN

    FOR i IN 1..100 LOOP

        INSERT INTO partys (eventID, partyName, partyLocation, partyStartDate, partyEndDate, partyPrice, dressCode, djName)

        VALUES (

            i, 
			
			 random_string(15) || ' Party', 

            random_string(8) || ' City', 
			
			'2024-01-01'::DATE + (random() * 365)::INT,
			
			'2024-01-01'::DATE + (random() * 365)::INT,
			
            random() * 100,
            random_string(12) || ' dressCode',
			random_string(1) || ' Dj'

        );

    END LOOP;

END $$;

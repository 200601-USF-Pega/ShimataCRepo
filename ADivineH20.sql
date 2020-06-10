CREATE TABLE person (
    first_name text NOT NULL,
    last_name text NOT NULL,
    `role` text NOT NULL,
    pass text,
    seats_available integer,
    phone text UNIQUE,
    email text UNIQUE,
    facebook text UNIQUE,
    address_number integer,
    street_name text,
    unit text,
    city text,
    `state` text,
    zip integer,
    profile_image_url text UNIQUE,
	PRIMARY KEY (first_name, last_name)
);

CREATE TABLE `event` (
    title text PRIMARY KEY,
    info_url text,
    address_number integer,
    street_name text,
    city text,
    `state` text,
    zip integer,
    number_helpers_needed integer,
    event_image_url text
);

CREATE TABLE schedule (
    auto_id serial PRIMARY KEY,
    from_hour integer,
    from_minute integer,
    from_month integer,
    from_day integer,
    from_year integer,
    to_hour integer,
    to_minute integer,
    to_month integer,
    to_day integer,
    to_year integer,
    available boolean,
    first_name text,
    last_name text,
    event_title text
);

CREATE TABLE supply (
    auto_id serial PRIMARY KEY,
    title text NOT NULL,
    quantity integer NOT NULL,
    category text,
    sub_category text,
    expiration DATETIME,
    status text,
    event_title text,
    first_name text,
    last_name text
);

CREATE TABLE message_thread (
    date_time_started DATETIME PRIMARY KEY,
	title text,
    event_title text
);

CREATE TABLE `message` (
    date_time DATETIME PRIMARY KEY,
    `text` text,
    message_thread_date_time_started DATETIME,
    person_first_name text,
    person_last_name text
);

CREATE TABLE event_person (
    event_title text,
    person_first_name text,
    person_last_name text
);




------------------------------------
ALTER TABLE schedule 
	ADD CONSTRAINT event_title_fkey 
	FOREIGN KEY (event_title) 
	REFERENCES "event" (title);

ALTER TABLE schedule 
	ADD CONSTRAINT person_first_name_last_name_fkey 
	FOREIGN KEY (first_name, last_name) 
	REFERENCES person (first_name, last_name);

ALTER TABLE supply 
	ADD CONSTRAINT event_title_fkey 
	FOREIGN KEY (event_title) 
	REFERENCES "event" (title);

ALTER TABLE supply 
	ADD CONSTRAINT person_responsible_first_name_last_name_fkey 
	FOREIGN KEY (first_name, last_name) 
	REFERENCES person (first_name, last_name);

ALTER TABLE message_thread 
	ADD CONSTRAINT event_title_fkey 
	FOREIGN KEY (event_title) 
	REFERENCES "event" (title);

ALTER TABLE message 
	ADD CONSTRAINT message_thread_date_time_started_fkey 
	FOREIGN KEY (message_thread_date_time_started) 
	REFERENCES message_thread (date_time_started);

ALTER TABLE message 
	ADD CONSTRAINT person_first_name_last_name_fkey 
	FOREIGN KEY (person_first_name, person_last_name) 
	REFERENCES person (first_name, last_name);

ALTER TABLE event_person 
	ADD CONSTRAINT event_title_fkey 
	FOREIGN KEY (event_title) 
	REFERENCES "event" (title);

ALTER TABLE event_person 
	ADD CONSTRAINT person_first_name_last_name_fkey 
	FOREIGN KEY (person_first_name, person_last_name) 
	REFERENCES person (first_name, last_name);
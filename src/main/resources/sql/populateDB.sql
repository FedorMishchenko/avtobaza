INSERT INTO users VALUES (DEFAULT , 'admin', '123', 'admin','050-555-5555', 'admin@gmail.com');
INSERT INTO users VALUES (DEFAULT , 'manager', '123', 'manager','067-333-2211', 'mng@gmail.com');
INSERT INTO users VALUES (DEFAULT , 'user', '123', 'user', '099-123-4665', 'user@gmail.com');
INSERT INTO users VALUES (DEFAULT , 'user2', '123', 'user', '099-123-4665', 'user2@yahoo.com');
INSERT INTO users VALUES (DEFAULT , 'user3', '123', 'user', '099-123-4665', 'user3@gmail.com');



INSERT INTO user_info VALUES (DEFAULT ,'man','ready',25,3);
INSERT INTO user_info VALUES (DEFAULT ,'man','ready',25,4);
INSERT INTO user_info VALUES (DEFAULT ,'man','ready',25,5);


INSERT INTO orders (id, start_point, destination, distance, status, creation_date, user_id)
VALUES (DEFAULT, 'kharkov', 'kiev', '500', 'close', DEFAULT, 3);
INSERT INTO orders (id, start_point, destination, distance, status, creation_date, user_id)
VALUES (DEFAULT, 'kiev','kharkov', '500', 'close', DEFAULT, 4);
INSERT INTO orders (id, start_point, destination, distance, status, creation_date, user_id)
VALUES (DEFAULT, 'kharkov', 'lviv', '1000', 'progress', DEFAULT, 5);
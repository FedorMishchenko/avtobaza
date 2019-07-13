SET NAMES utf8;

DROP DATABASE IF EXISTS trucking_exchange;

CREATE DATABASE trucking_exchange CHARACTER SET utf8;

USE trucking_exchange;

CREATE TABLE users(
                    id  INT PRIMARY KEY AUTO_INCREMENT,
                    login VARCHAR(15) UNIQUE NOT NULL,
                    password VARCHAR (15) NOT NULL,
                    role VARCHAR (15) NOT NULL,
                    phone VARCHAR (15) NOT NULL ,
                    email VARCHAR (50) NOT NULL
);

CREATE TABLE orders(
                     id  INT PRIMARY KEY AUTO_INCREMENT,
                     start_point  VARCHAR(45) NOT NULL,
                     destination  VARCHAR(45) NOT NULL,
                     distance VARCHAR (10) NOT NULL,
                     status VARCHAR(15) CHECK (status IN('open','progress','close','cancel')),
                     creation_date DATETIME DEFAULT NOW(),
                     user_id INT NOT NULL ,
                     FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE user_info(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        truck VARCHAR (30) NOT NULL ,
                        status VARCHAR (15) NOT NULL /*CHECK (status IN('ready','progress','repair'))*/ ,
                        capacity VARCHAR (10) NOT NULL ,
                        user_id INT NOT NULL ,
                        FOREIGN KEY (user_id) REFERENCES users(id)


);


CREATE TABLE request(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user_id INT NOT NULL ,
                      order_id INT NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users (id),
                      FOREIGN KEY (order_id) REFERENCES orders(id)

);
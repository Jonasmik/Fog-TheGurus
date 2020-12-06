drop database if exists fogdbtest;
create database fogdbtest;
use fogdbtest;

CREATE TABLE properties (
                            name VARCHAR(255) PRIMARY KEY,
                            value VARCHAR(255) NOT NULL
);

INSERT INTO properties (name, value) VALUES ("version", "0");



CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(25) NOT NULL,
                       email VARCHAR(70) NOT NULL UNIQUE,
                       address VARCHAR(100) NOT NULL,
                       zip VARCHAR(50) NOT NULL,
                       city VARCHAR(100) NOT NULL,
                       createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       salt BINARY(16) NOT NULL,
                       secret BINARY(32) NOT NULL,
                       role VARCHAR(25) NOT NULL
);

CREATE TABLE carport (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         length INT NOT NULL,
                         width INT NOT NULL,
                         roof VARCHAR(255) NOT NULL,
                         roofangle INT DEFAULT 0
);

CREATE TABLE shed (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      width INT NOT NULL,
                      length INT NOT NULL,
                      carportid INT NOT NULL,
                      FOREIGN KEY (carportid) REFERENCES carport(id)
);

CREATE TABLE customers (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           userid INT NOT NULL,
                           name VARCHAR(100) NOT NULL,
                           adress VARCHAR(100) NOT NULL,
                           zipcode INT NOT NULL,
                           city VARCHAR(100) NOT NUlL,
                           email VARCHAR(255) NOT NULL,
                           additional VARCHAR(255) NOT NULL,
                           FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE salesmen (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          userid INT NOT NULL,
                          FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE preorders (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           customerid INT NOT NULL,
                           salesmanid INT DEFAULT NULL,
                           carportid INT NOT NULL,
                           FOREIGN KEY (customerid) REFERENCES customers(id),
                           FOREIGN KEY (salesmanid) REFERENCES salesmen(id),
                           FOREIGN KEY (carportid) REFERENCES carport(id)
);

CREATE TABLE offers (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        preorderid INT NOT NULL,
                        price DOUBLE NOT NULL,
                        FOREIGN KEY (preorderid) REFERENCES preorders(id)
);


CREATE TABLE orders (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        customerid INT NOT NULL,
                        offerid INT NOT NULL,
                        FOREIGN KEY (customerid) REFERENCES customers(id),
                        FOREIGN KEY (offerid) REFERENCES offers(id)
);

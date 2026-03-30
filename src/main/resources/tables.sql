DROP DATABASE IF EXISTS dbapi;
CREATE DATABASE IF NOT EXISTS dbapi;
USE dbapi;

CREATE TABLE IF NOT EXISTS characters (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    ki VARCHAR(50),
    maxKi VARCHAR(50),
    race VARCHAR(50),
    gender VARCHAR(10) NOT NULL,
    description TEXT,
    image VARCHAR(255),
    affiliation VARCHAR(100)
);
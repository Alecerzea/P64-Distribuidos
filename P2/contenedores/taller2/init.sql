CREATE DATABASE IF NOT EXISTS my_database;

USE my_database;

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles (role_name) VALUES ('Admin');
INSERT INTO roles (role_name) VALUES ('User');

INSERT INTO users (username, email, password, role_id) VALUES ('admin_user', 'admin@example.com', 'adminpassword', 1);
INSERT INTO users (username, email, password, role_id) VALUES ('regular_user', 'user@example.com', 'userpassword', 2);

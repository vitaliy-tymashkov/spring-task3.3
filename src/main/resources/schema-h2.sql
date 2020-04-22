CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
phoneNumber VARCHAR(255),
phoneOperator VARCHAR(255),
balance INT);
CREATE TABLE usersPasswords
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    password VARCHAR(255)
);
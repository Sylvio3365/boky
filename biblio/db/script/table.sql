CREATE DATABASE biblio;

USE biblio;

CREATE TABLE role (
    idrole INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(250)
);

INSERT INTO role (idrole, nom) VALUES (1, 'admin');

INSERT INTO role (idrole, nom) VALUES (2, 'bibliothecaire');

INSERT INTO role (idrole, nom) VALUES (3, 'adherent');

CREATE TABLE utilisateur (
    idutilisateur int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(250),
    password VARCHAR(250),
    idrole INT NOT NULL,
    FOREIGN KEY (idrole) REFERENCES role (idrole)
);
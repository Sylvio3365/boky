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

INSERT INTO
    utilisateur (
        idutilisateur,
        username,
        password,
        idrole
    )
VALUES (1, 'admin', 'admin', 1);

INSERT INTO
    utilisateur (
        idutilisateur,
        username,
        password,
        idrole
    )
VALUES (
        2,
        'bibliothecaire',
        'bibliothecaire',
        2
    );

INSERT INTO
    utilisateur (
        idutilisateur,
        username,
        password,
        idrole
    )
VALUES (3, 'adherent1', 'adehrent', 3);

CREATE TABLE profil (
    idprofil INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255)
);

CREATE TABLE adherent (
    idadherent INT AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    dtn DATE,
    idutilisateur INT NOT NULL,
    idprofil INT,
    PRIMARY KEY (idadherent),
    UNIQUE (idutilisateur),
    FOREIGN KEY (idutilisateur) REFERENCES utilisateur (idutilisateur),
    FOREIGN KEY (idprofil) REFERENCES profil (idprofil)
);

CREATE TABLE utilisateur (
    idutilisateur INT AUTO_INCREMENT,
    username VARCHAR(250),
    password VARCHAR(250),
    idadherent INT,
    idrole INT NOT NULL,
    PRIMARY KEY (idutilisateur),
    FOREIGN KEY (idadherent) REFERENCES adherent (idadherent),
    FOREIGN KEY (idrole) REFERENCES role (idrole)
);

CREATE TABLE livre (
    idlivre INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    auteur VARCHAR(255)
);

CREATE TABLE exemplaire (
    idexemplaire INT AUTO_INCREMENT PRIMARY KEY,
    idlivre INT NOT NULL,
    numero VARCHAR(50) NOT NULL,
    FOREIGN KEY (idlivre) REFERENCES livre (idlivre)
);
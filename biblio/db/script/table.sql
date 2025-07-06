-- Création de la base de données
DROP DATABASE IF EXISTS biblio;

CREATE DATABASE IF NOT EXISTS biblio;

USE biblio;

-- Table des rôles
CREATE TABLE role (
    idrole INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(250) NOT NULL
);

-- Table des profils d’adhérents
CREATE TABLE profil (
    idprofil INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

-- Table des règles générales liées aux profils
CREATE TABLE regle (
    idregle INT AUTO_INCREMENT PRIMARY KEY,
    nb_jour_pret_max INT NOT NULL,
    nb_reservation_max INT NOT NULL,
    nb_prolongement_max INT NOT NULL,
    idprofil INT NOT NULL,
    FOREIGN KEY (idprofil) REFERENCES profil (idprofil)
);

-- Table des règles spécifiques liées aux livres (ex : âge minimum)
CREATE TABLE reglelivre (
    idreglelivre INT AUTO_INCREMENT PRIMARY KEY,
    age_min INT
);

-- Table des types de prêt
CREATE TABLE typepret (
    idtypepre INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

-- Table des adhérents
CREATE TABLE adherent (
    idadherent INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    dtn DATE,
    idprofil INT NOT NULL,
    FOREIGN KEY (idprofil) REFERENCES profil (idprofil)
);

-- Table des livres (avec règle d’âge minimale)
CREATE TABLE livre (
    idlivre INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    auteur VARCHAR(255),
    idreglelivre INT NOT NULL,
    FOREIGN KEY (idreglelivre) REFERENCES reglelivre (idreglelivre)
);

-- Table des exemplaires
CREATE TABLE exemplaire (
    idexemplaire INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(50) NOT NULL,
    idtypepre INT NOT NULL,
    idlivre INT NOT NULL,
    FOREIGN KEY (idtypepre) REFERENCES typepret (idtypepre),
    FOREIGN KEY (idlivre) REFERENCES livre (idlivre)
);

-- Table des prêts
CREATE TABLE pret (
    idpret INT AUTO_INCREMENT PRIMARY KEY,
    debut DATE,
    fin DATE,
    idadherent INT NOT NULL,
    idtypepre INT NOT NULL,
    idexemplaire INT NOT NULL,
    FOREIGN KEY (idadherent) REFERENCES adherent (idadherent),
    FOREIGN KEY (idtypepre) REFERENCES typepret (idtypepre),
    FOREIGN KEY (idexemplaire) REFERENCES exemplaire (idexemplaire)
);

-- Table des rendus
CREATE TABLE rendu (
    idrendu INT AUTO_INCREMENT PRIMARY KEY,
    date_ DATE NOT NULL,
    idpret INT NOT NULL UNIQUE,
    FOREIGN KEY (idpret) REFERENCES pret (idpret)
);

-- Table des abonnements
CREATE TABLE abonnement (
    idabonnement INT AUTO_INCREMENT PRIMARY KEY,
    mois INT NOT NULL,
    annee INT,
    idadherent INT NOT NULL,
    FOREIGN KEY (idadherent) REFERENCES adherent (idadherent)
);

-- Table des activités
CREATE TABLE activite (
    idactivite INT AUTO_INCREMENT PRIMARY KEY,
    debut DATE,
    fin DATE,
    idadherent INT NOT NULL,
    FOREIGN KEY (idadherent) REFERENCES adherent (idadherent)
);

-- Table des utilisateurs (accès système)
CREATE TABLE utilisateur (
    idutilisateur INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    idadherent INT,
    idrole INT NOT NULL,
    FOREIGN KEY (idadherent) REFERENCES adherent (idadherent),
    FOREIGN KEY (idrole) REFERENCES role (idrole)
);

CREATE TABLE Sanction (
    idsanction INT,
    debut DATE,
    fin DATE,
    idadherent INT NOT NULL,
    PRIMARY KEY (idsanction),
    FOREIGN KEY (idadherent) REFERENCES adherent (idadherent)
);

-- Données initiales
INSERT INTO
    role (nom)
VALUES ('admin'),
    ('bibliothecaire'),
    ('adherent');

INSERT INTO
    utilisateur (username, password, idrole)
VALUES ('admin', 'admin', 1),
    (
        'bibliothecaire',
        'bibliothecaire',
        2
    ),
    ('adherent1', 'adherent', 3);
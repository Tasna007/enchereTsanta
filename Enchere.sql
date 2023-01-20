CREATE DATABASE enchere;
-- CREATE ROLE itu LOGIN PASSWORD '0';
ALTER DATABASE enchere OWNER TO itu;

/*

enchere

itu
0

*/


CREATE TABLE Produit (
  idProduit   SERIAL NOT NULL, 
  produit     varchar(50), 
  idCategorie int4 NOT NULL, 
  PRIMARY KEY (idProduit));
CREATE TABLE Utilisateur (
  idUtilisateur SERIAL NOT NULL, 
  Nom           varchar(50) NOT NULL, 
  Prenom        varchar(50) NOT NULL, 
  Naissance     date NOT NULL, 
  idCompte      int4 NOT NULL, 
  email         varchar(50) NOT NULL,
  "password"    varchar(32) NOT NULL,
  inscription   timestamp DEFAULT now() NOT NULL,
  PRIMARY KEY (idUtilisateur));
CREATE TABLE Categorie (
  idCategorie SERIAL NOT NULL, 
  categorie   varchar(30) NOT NULL, 
  PRIMARY KEY (idCategorie));
CREATE TABLE Enchere (
  idEnchere    SERIAL NOT NULL, 
  produit      varchar(70) NOT NULL, 
  prix_planche float8 NOT NULL, 
  duree        int4 NOT NULL, 
  ajout        timestamp DEFAULT now() NOT NULL, 
  description  text NOT NULL, 
  idUtilisateur int4 NOT NULL,
  idCategorie  int4 NOT NULL,
  etat         int4 NOT NULL DEFAULT 0, 
  PRIMARY KEY (idEnchere));
CREATE TABLE DetailsEnchere (
  idDetailsEnchere SERIAL NOT NULL, 
  idEnchere        int4 NOT NULL, 
  idUtilisateur    int4 NOT NULL, 
  mise             float8 NOT NULL, 
  dateMise           timestamp DEFAULT now() NOT NULL, 
  PRIMARY KEY (idDetailsEnchere));
CREATE TABLE Compte (
  idCompte SERIAL NOT NULL, 
  Solde    float8 NOT NULL DEFAULT 0, 
  mise     float8 NOT NULL DEFAULT 0, 
  PRIMARY KEY (idCompte));
CREATE TABLE EncherePhoto (
  idEnchere int4 NOT NULL, 
  photo     varchar(100) NOT NULL);
CREATE TABLE Gagnant (
  idGagnant     SERIAL NOT NULL, 
  idUtilisateur int4 NOT NULL, 
  idEnchere int4 NOT NULL, 
  idDetailsEnchere int4 NOT NULL, 
  prix float8 NOT NULL,
  PRIMARY KEY (idGagnant));
CREATE TABLE Etat (
  idEtat     SERIAL NOT NULL, 
  etat       varchar(30) NOT NULL, 
  PRIMARY KEY (idEtat));
CREATE TABLE MouvementCompte (
  idMouvementCompte     SERIAL NOT NULL,
  idUtilisateur int4 NOT NULL,
  idEtat int4 NOT NULL DEFAULT 1,
  dateMouvement timestamp DEFAULT NULL,
  dateDemande timestamp NOT NULL DEFAULT NOW(),
  somme float8 NOT NULL,
  PRIMARY KEY (idMouvementCompte));
ALTER TABLE MouvementCompte ADD FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE MouvementCompte ADD FOREIGN KEY (idEtat) REFERENCES Etat (idEtat);

ALTER TABLE EncherePhoto ADD CONSTRAINT FKEncherePho391879 FOREIGN KEY (idEnchere) REFERENCES Enchere (idEnchere);
ALTER TABLE Produit ADD CONSTRAINT FKProduit946830 FOREIGN KEY (idCategorie) REFERENCES Categorie (idCategorie);
ALTER TABLE Enchere ADD CONSTRAINT FKEnchere950663 FOREIGN KEY (idCategorie) REFERENCES Categorie (idCategorie);
ALTER TABLE Enchere ADD FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE DetailsEnchere ADD CONSTRAINT FKDetailsEnc697129 FOREIGN KEY (idEnchere) REFERENCES Enchere (idEnchere);
ALTER TABLE Utilisateur ADD CONSTRAINT FKUtilisateu282098 FOREIGN KEY (idCompte) REFERENCES Compte (idCompte);
ALTER TABLE DetailsEnchere ADD CONSTRAINT FKDetailsEnc894946 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE Gagnant ADD CONSTRAINT FKGagnant178470 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);

INSERT INTO Etat(etat) VALUES('Demande');
INSERT INTO Etat(etat) VALUES('Accepter');
INSERT INTO Etat(etat) VALUES('Refuser');

CREATE OR REPLACE VIEW V_Compte AS
SELECT 
  Utilisateur.idUtilisateur,
  Utilisateur.idCompte,
  Compte.solde,
  Compte.mise,
  Compte.solde - Compte.mise AS argent
FROM 
  Utilisateur JOIN Compte
    ON Compte.idCompte = Utilisateur.idCompte;

--------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE EncherePhoto DROP CONSTRAINT FKEncherePho391879;
ALTER TABLE Utilisateur DROP CONSTRAINT FKUtilisateu282098;
ALTER TABLE DetailsEnchere DROP CONSTRAINT FKDetailsEnc855524;
ALTER TABLE DetailsEnchere DROP CONSTRAINT FKDetailsEnc894946;
ALTER TABLE Gagnant DROP CONSTRAINT FKGagnant413606;
ALTER TABLE Gagnant DROP CONSTRAINT FKGagnant325526;
ALTER TABLE Gagnant DROP CONSTRAINT FKGagnant178470;
ALTER TABLE Enchere DROP CONSTRAINT FKEnchere922758;
ALTER TABLE MouvementCompte DROP CONSTRAINT FKMouvementC727859;
ALTER TABLE Enchere DROP CONSTRAINT FKEnchere94438;
ALTER TABLE MouvementCompte DROP CONSTRAINT FKMouvementC372486;
DROP TABLE IF EXISTS Compte CASCADE;
DROP TABLE IF EXISTS Parametre CASCADE;
DROP TABLE IF EXISTS Utilisateur CASCADE;
DROP TABLE IF EXISTS Categorie CASCADE;
DROP TABLE IF EXISTS Enchere CASCADE;
DROP TABLE IF EXISTS EncherePhoto CASCADE;
DROP TABLE IF EXISTS DetailsEnchere CASCADE;
DROP TABLE IF EXISTS Gagnant CASCADE;
DROP TABLE IF EXISTS Administrateur CASCADE;
DROP TABLE IF EXISTS MouvementCompte CASCADE;
DROP TABLE IF EXISTS Etat CASCADE;
CREATE TABLE Compte (
  idCompte SERIAL NOT NULL, 
  solde    float8 NOT NULL DEFAULT 0, 
  mise     float8 NOT NULL DEFAULT 0, 
  PRIMARY KEY (idCompte));
CREATE TABLE Parametre (
  idParametre SERIAL NOT NULL, 
  cle         varchar(70) NOT NULL, 
  valeur      varchar(70), 
  valueNumber float8, 
  PRIMARY KEY (idParametre));
CREATE TABLE Utilisateur (
  idUtilisateur SERIAL NOT NULL, 
  nom           varchar(50) NOT NULL, 
  prenom        varchar(70) NOT NULL, 
  naissance     date NOT NULL, 
  email         varchar(60) NOT NULL, 
  password      varchar(60) NOT NULL, 
  idCompte      int4 NOT NULL, 
  inscription   timestamp DEFAULT now() NOT NULL, 
  PRIMARY KEY (idUtilisateur));
CREATE TABLE Categorie (
  idCategorie SERIAL NOT NULL, 
  categorie   varchar(50) NOT NULL, 
  PRIMARY KEY (idCategorie));
CREATE TABLE Enchere (
  idEnchere     SERIAL NOT NULL, 
  produit       varchar(70) NOT NULL, 
  prix_planche  float8 NOT NULL, 
  duree         float8 NOT NULL, 
  ajout         timestamp DEFAULT now() NOT NULL, 
  description   text NOT NULL, 
  idCategorie   int4 NOT NULL, 
  etat          int4 DEFAULT 0 NOT NULL, 
  idUtilisateur int4 NOT NULL, 
  PRIMARY KEY (idEnchere));
CREATE TABLE EncherePhoto (
  idEnchere int4 NOT NULL, 
  photo     varchar(100) NOT NULL);
CREATE TABLE DetailsEnchere (
  idDetailsEnchere SERIAL NOT NULL, 
  mise             float8 NOT NULL, 
  dateMise         timestamp NOT NULL DEFAULT NOW(), 
  idEnchere         int4 NOT NULL, 
  idUtilisateur    int4 NOT NULL, 
  PRIMARY KEY (idDetailsEnchere));
CREATE TABLE Gagnant (
  idGagnant        SERIAL NOT NULL, 
  idEnchere        int4 NOT NULL, 
  idDetailsEnchere int4 NOT NULL, 
  idUtilisateur    int4 NOT NULL, 
  prix             float8 NOT NULL, 
  PRIMARY KEY (idGagnant));
CREATE TABLE Administrateur (
  idAdmin     SERIAL NOT NULL, 
  identifiant varchar(60) NOT NULL, 
  password    varchar(40) NOT NULL, 
  PRIMARY KEY (idAdmin));
CREATE TABLE MouvementCompte (
  idMouvementCompte SERIAL NOT NULL, 
  idUtilisateur     int4 NOT NULL, 
  idEtat            int4 NOT NULL DEFAULT 1, 
  dateMouvement     timestamp DEFAULT null, 
  somme             float8 NOT NULL, 
  datedemande       timestamp DEFAULT now() NOT NULL, 
  PRIMARY KEY (idMouvementCompte));
CREATE TABLE Etat (
  idEtat SERIAL NOT NULL, 
  etat   varchar(30) NOT NULL, 
  PRIMARY KEY (idEtat));
ALTER TABLE EncherePhoto ADD CONSTRAINT FKEncherePho391879 FOREIGN KEY (idEnchere) REFERENCES Enchere (idEnchere);
ALTER TABLE Utilisateur ADD CONSTRAINT FKUtilisateu282098 FOREIGN KEY (idCompte) REFERENCES Compte (idCompte);
ALTER TABLE DetailsEnchere ADD CONSTRAINT FKDetailsEnc855524 FOREIGN KEY (dEnchere) REFERENCES Enchere (idEnchere);
ALTER TABLE DetailsEnchere ADD CONSTRAINT FKDetailsEnc894946 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE Gagnant ADD CONSTRAINT FKGagnant413606 FOREIGN KEY (idEnchere) REFERENCES Enchere (idEnchere);
ALTER TABLE Gagnant ADD CONSTRAINT FKGagnant325526 FOREIGN KEY (idDetailsEnchere) REFERENCES DetailsEnchere (idDetailsEnchere);
ALTER TABLE Gagnant ADD CONSTRAINT FKGagnant178470 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE Enchere ADD CONSTRAINT FKEnchere922758 FOREIGN KEY (idCategorie) REFERENCES Categorie (idCategorie);
ALTER TABLE MouvementCompte ADD CONSTRAINT FKMouvementC727859 FOREIGN KEY (idEtat) REFERENCES Etat (idEtat);
ALTER TABLE Enchere ADD CONSTRAINT FKEnchere94438 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE MouvementCompte ADD CONSTRAINT FKMouvementC372486 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
INSERT INTO Parametre(idParametre, cle, valeur, valueNumber) VALUES (1, 'delai', null, 10);
INSERT INTO Parametre(idParametre, cle, valeur, valueNumber) VALUES (2, 'limite', null, 30);
INSERT INTO Parametre(idParametre, cle, valeur, valueNumber) VALUES (3, 'commission', null, 0.4);
INSERT INTO Etat(idEtat, etat) VALUES (1, 'Attente');
INSERT INTO Etat(idEtat, etat) VALUES (2, 'Accepte');
INSERT INTO Etat(idEtat, etat) VALUES (3, 'Refuse');

CREATE OR REPLACE VIEW V_Compte AS
SELECT 
  Utilisateur.idUtilisateur,
  Utilisateur.idCompte,
  Compte.solde,
  Compte.mise,
  Compte.solde - Compte.mise AS argent
FROM 
  Utilisateur JOIN Compte
    ON Compte.idCompte = Utilisateur.idCompte;

create role rojo login password 'rata';
create database cristaline;
alter database cristaline owner to rojo;
\c cristaline rojo;


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
  solde    float8 NOT NULL, 
  mise     float8 NOT NULL, 
  PRIMARY KEY (idCompte));
CREATE TABLE Parametre (
  idParametre SERIAL NOT NULL, 
  cle         varchar(70) NOT NULL, 
  valeur      varchar(70), 
  valueNumber float8, 
  PRIMARY KEY (idParametre));
CREATE TABLE Utilisateur (
  idUtilisateur SERIAL NOT NULL, 
  idgenre int4 not null,
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
  idUtilisateur int4 , 
  PRIMARY KEY (idEnchere));
CREATE TABLE EncherePhoto (
  idEnchere int4 NOT NULL, 
  photo     varchar(100) NOT NULL);
CREATE TABLE DetailsEnchere (
  idDetailsEnchere SERIAL NOT NULL, 
  mise             float8 NOT NULL, 
  dateMise         timestamp NOT NULL, 
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
create table administrateur(
	idAdmin serial primary key,
	identifiant varchar(20) not null,
	mdp varchar(20) not null
);
CREATE TABLE MouvementCompte (
  idMouvementCompte SERIAL NOT NULL, 
  idUtilisateur     int4 NOT NULL, 
  idEtat            int4 NOT NULL, 
  dateMouvement     timestamp DEFAULT null, 
  somme             float8 NOT NULL, 
  datedemande       timestamp DEFAULT now() NOT NULL, 
  PRIMARY KEY (idMouvementCompte));
CREATE TABLE Etat (
  idEtat SERIAL NOT NULL, 
  etat   varchar(30) NOT NULL, 
  PRIMARY KEY (idEtat));
create type sex as enum('masculin','feminin');
create table genre(
	idGenre serial primary key,
	genre varchar(10) not null 
);

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
insert into genre (idGenre,genre) values(default,'masculin'),(default,'feminin');



create or replace view vutilisateur as 
select utilisateur.*,genre.genre as genre from utilisateur join genre on genre.idgenre=utilisateur.idgenre;

create or replace view masc as
select count(*) as masculin from vutilisateur where genre='masculin' ;
create or replace view fem as
select count(*) as feminin from vutilisateur where genre='feminin';

create or replace view repartgenre as 
select masc.*,fem.* from masc cross join fem;

create or replace view vtotal as 
select count(*) from vutilisateur;

create or replace view repartitiongenre as 
select repartgenre.*,vtotal.count as total from repartgenre cross join vtotal;

create or replace view pourcentagegenre as
select (masculin*100)/(total*1.0) as masculin,(feminin*100)/(total*1.0) as feminin from repartitiongenre;
--done

create or replace view genregagnant as 
select gagnant.*,vutilisateur.genre from gagnant join vutilisateur on gagnant.idutilisateur=vutilisateur.idutilisateur;

create or replace view genregagnantmasc as 
select count(*) as masculin from genregagnant where genre='masculin';

create or replace view genregagnantfem as 
select count(*) as feminin from genregagnant where genre='feminin';

create or replace view genregagnanttotal as 
select count(*) as total from genregagnant;

create or replace view nbregagnant as
select genregagnantmasc.*,genregagnantfem.* from genregagnantmasc cross join genregagnantfem;

create or replace view repartitiongagnant as
select genregagnanttotal.*,nbregagnant.* from nbregagnant cross join genregagnanttotal;

create or replace view pourcentagegagnant as 
select (masculin*100)/(total*1.0) as masculin,(feminin*100)/(total*1.0) as feminin from repartitiongagnant;
--done

create or replace view naissutilisateur as 
select extract(year from naissance) as naiss from utilisateur;

create or replace view yearactuel as
select extract(year from now()) as year from utilisateur;


create or replace view age as 
select naiss,extract(year from now()) as year, extract(year from now())-naiss as age from naissutilisateur;

insert into categorie values (default,'Appareils menagers'),(default,'Meubles'),(default,'Voitures'),(default,'Maison');
insert into categorie values (default,'Telephone');

insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'Iphone 8',2400000,4,default,'bonne occasion mora be',5,default,28);
insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'Four',645000,7,default,'tsara be',1,default,28);
insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'Iphone 12',3500000,4,default,'bonne occasion',5,default,29);
insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'golf type 3',8000000,4,default,'mbola d origine',3,default,29);
insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'V8',48000000,12,default,'',3,default,30);
insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'406',240000,13,default,'',3,default,30);
insert into enchere(idEnchere,produit,prix_planche,duree,ajout,description,idcategorie,etat,idutilisateur) values (default,'Villa Tsarahonenana',500000,7,default,'villa en securite',4,default,31);

create or replace view categorieenchere as 
select enchere.idcategorie,categorie.categorie from enchere join categorie on categorie.idcategorie=enchere.idcategorie;

insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,1,1,2,555000);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,2,6,3,22000);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,3,7,3,13000);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,4,8,9,145000);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,5,10,7,8500);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,6,9,2,9600);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,7,12,1,74000);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,8,20,5,85500);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,9,15,4,74000);
insert into gagnant(idGagnant,idenchere,idDetailsEnchere,idutilisateur,prix) values (default,10,17,5,85500);

insert into administrateur values (default,'cloud','enchere');

insert into Compte(solde,mise) values (50000,1000);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (1,'Rak','Ben','2001-07-06','rak@yahoo.mg','jean',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (1,'Idealy','Benja','2003-11-06','jean@yahoo.mg','jean',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (1,'Goku','Sayan','1997-12-31','rakoto@yahoo.fr','jeanne',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (2,'Tanjona','Kanto','1960-07-05','rakotonimaro@gmail.com','toky',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (2,'Robs','Jean','2007-07-06','rakotomanana@yahoo.mg','andy',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (2,'Kaloina','Rak','1986-10-06','rakotobe@yahoo.mg','tojo',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (2,'Paika','Faniry','1975-07-10','rako@yahoo.mg','mikooo',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (2,'Tsanta','Bum','1996-07-22','andria@yahoo.mg','ok',1);
insert into utilisateur(idgenre,nom,prenom,naissance,email,password,idcompte) values (2,'Saf','Hery','2002-07-12','rak@gmail.com','gem',1);


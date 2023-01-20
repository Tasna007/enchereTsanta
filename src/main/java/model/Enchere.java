package model;
import util.ObjetBDD;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

    public class Enchere extends ObjetBDD{
        private int idenchere;
        private int idutilisateur;
        private String produit;
        private float prix_planche;
        private float duree;
        private Timestamp ajout;
        private String description;
        private int idcategorie;
		private int etat;

		public void setidenchere(int a){idenchere=a;}
		public int getidenchere(){return idenchere;}

        public void setidutilisateur(int a){idutilisateur=a;}
        public int getidutilisateur(){return idutilisateur;}
        
		public void setproduit(String a){produit=a;}
		public String getproduit(){return produit;}

        public void setprix_planche(float a){prix_planche=a;}
        public float getprix_planche(){return prix_planche;}

        public void setduree(float a){duree=a;}
        public float getduree(){return duree;}

        public void setajout(Timestamp a){ajout=a;}
        public Timestamp getajout(){return ajout;}

        public void setdescription(String a){description=a;}
        public String getdescription(){return description;}

        public void setetat(int a){etat=a;}
        public int getetat(){return etat;}

        public void setidcategorie(int a){idcategorie=a;}
        public int getidcategorie(){return idcategorie;}

		public Enchere() throws Exception {}

    }
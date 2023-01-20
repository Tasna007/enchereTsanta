package model;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

    public class Utilisateur{
        private int idutilisateur;
        private String nom;
        private String prenom;
        private String email;
        private String password;
        private Timestamp inscription;
        private Date naissance;
		// private int compte;

        public void setidutilisateur(int a){idutilisateur=a;}
        public int getidutilisateur(){return idutilisateur;}
        
		public void setpassword(String a){password=a;}
		public String getpassword(){return password;}

        public void setemail(String a){email=a;}
        public String getemail(){return email;}

        public void setprenom(String a){prenom=a;}
        public String getprenom(){return prenom;}

        public void setnom(String a){nom=a;}
        public String getnom(){return nom;}

        public void setnaissance(Date a){naissance=a;}
        public Date getnaissance(){return naissance;}

        public void setinscription(Timestamp a){inscription=a;}
        public Timestamp getinscription(){return inscription;}

        // public void setcompte(int a){compte=a;}
        // public int getcompte(){return compte;}

		public Utilisateur() throws Exception {}

    }
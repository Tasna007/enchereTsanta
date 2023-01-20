package model;
import util.ObjetBDD;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

    public class Gagnant extends ObjetBDD{
        private int idgagnant;
        private int idutilisateur;
        private int iddetailsenchere;
        private int idenchere;
        private float prix;

		public void setidgagnant(int a){idgagnant=a;}
		public int getidgagnant(){return idgagnant;}

        public void setidutilisateur(int a){idutilisateur=a;}
        public int getidutilisateur(){return idutilisateur;}

        public void setiddetailsenchere(int a){iddetailsenchere=a;}
        public int getiddetailsenchere(){return iddetailsenchere;}

        public void setidenchere(int a){idenchere=a;}
        public int getidenchere(){return idenchere;}

        public void setprix(float a){prix=a;}
        public float getprix(){return prix;}

		public Gagnant() throws Exception {}

    }
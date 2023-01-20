package model;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

    public class Detailsenchere{
        private Timestamp datemise;
        private int idutilisateur;
        private int iddetailsenchere;
        private int idenchere;
        private float mise;

		public void setdatemise(Timestamp a){datemise=a;}
		public Timestamp getdatemise(){return datemise;}

        public void setidutilisateur(int a){idutilisateur=a;}
        public int getidutilisateur(){return idutilisateur;}

        public void setiddetailsenchere(int a){iddetailsenchere=a;}
        public int getiddetailsenchere(){return iddetailsenchere;}

        public void setidenchere(int a){idenchere=a;}
        public int getidenchere(){return idenchere;}

        public void setmise(float a){mise=a;}
        public float getmise(){return mise;}

		public Detailsenchere() throws Exception {}

    }
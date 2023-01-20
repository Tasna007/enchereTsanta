package model;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

    public class Categorie{
        private int idcategorie;
        private String categorie;

        public void setidcategorie(int a){idcategorie=a;}
        public int getidcategorie(){return idcategorie;}

        public void setcategorie(String a){categorie=a;}
        public String getcategorie(){return categorie;}

		public Categorie() throws Exception {}

    }
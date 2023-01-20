package service;

import model.*;
import util.Connexion;
import util.Requete;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

public class S_Categorie {

    public Categorie get(int id) throws Exception{
        Categorie categorie=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idcategorie"; 
        String[] value=new String[1];value[0]=new Integer(id).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Categorie(),condition,value,signe);
            categorie=(Categorie)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Categorie.get(id) : "+e);
            e.printStackTrace();
        }
        return categorie;
    }

    public static Categorie[] getall() throws Exception{
        Categorie[] categorie=null;
        Connexion connexion = null;
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Categorie(),new String[0],new String[0],new String[0]);
            categorie=new Categorie[o.length];
            for (int i=0;i<o.length ;i++ ) {
                categorie[i]=(Categorie)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Categorie.get(id) : "+e);
            e.printStackTrace();
        }
        return categorie;
    }
}
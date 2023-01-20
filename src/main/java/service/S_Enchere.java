package service;

import model.*;
import util.Connexion;
import util.Requete;
import java.util.*;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.io.*;
// import java.sql.*;

public class S_Enchere {
   

    public Enchere get(int id) throws Exception{
        Enchere enchere=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idenchere"; 
        String[] value=new String[1];value[0]=new Integer(id).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Enchere(),condition,value,signe);
            enchere=(Enchere)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.get(id) : "+e);
            e.printStackTrace();
        }
        return enchere;
    }

    public static Enchere[] get_terminer() throws Exception{
        Enchere[] enchere=null;
        Enchere[] vita=null;
        int taille=0;
        try{
           enchere=S_Enchere.getall(0);
           for (int i=0;i<enchere.length ;i++ ) {
               long diff=new Date().getTime()-enchere[i].getajout().getTime();
               long heure=((diff/1000)/60)/60;
               if (heure>=enchere[i].getduree()) {
                   taille=taille+1;
               }
           }
           vita= new Enchere[taille];
           int v=0;
           for (int i=0;i<enchere.length ;i++ ) {
               long diff=new Date().getTime()-enchere[i].getajout().getTime();
               long heure=((diff/1000)/60)/60;
               if (heure>=enchere[i].getduree()) {
                   vita[v]=enchere[i];
                   i++;
               }
           }
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.get_terminer() : "+e);
            e.printStackTrace();
        }
        return vita;
    }

    public static void update_etat(Enchere enchere) throws Exception{
        Connexion connexion = null;
        Enchere vita=null;
        try{
            vita=new Enchere();
            vita.setidenchere(enchere.getidenchere());
            vita.setidutilisateur(enchere.getidutilisateur());
            vita.setidcategorie(enchere.getidcategorie());
            vita.setproduit(enchere.getproduit());
            vita.setduree(enchere.getduree());
            vita.setajout(enchere.getajout());
            vita.setdescription(enchere.getdescription());
            vita.setprix_planche(enchere.getprix_planche());
            vita.setetat(1);
            enchere.update(connexion.getConnexion(),"Enchere",vita);
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.update_etat() : "+e);
            e.printStackTrace();
        }
    }

    public static Enchere[] getall(int etat) throws Exception{
        Enchere[] enchere=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="etat"; 
        String[] value=new String[1];value[0]=new Integer(etat).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Enchere(),condition,value,signe);
            enchere=new Enchere[o.length];
            for (int i=0;i<o.length ;i++ ) {
                enchere[i]=(Enchere)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.get() : "+e);
            e.printStackTrace();
        }
        return enchere;
    }

    public static Gagnant get_gagnant(Enchere enchere) throws Exception{
        Gagnant gagnant=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idenchere"; 
        String[] value=new String[1];value[0]=new Integer(enchere.getidenchere()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Gagnant(),condition,value,signe);
            gagnant=(Gagnant)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.get_gagnant(Enchere) : "+e);
            e.printStackTrace();
        }
        return gagnant;
    }

    public static Utilisateur get_utilisateur(Enchere enchere) throws Exception{
        Utilisateur utilisateur=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idutilisateur"; 
        String[] value=new String[1];value[0]=new Integer(enchere.getidutilisateur()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Utilisateur(),condition,value,signe);
            utilisateur=(Utilisateur)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.get_utilisateur(Enchere) : "+e);
            e.printStackTrace();
        }
        return utilisateur;
    }

    public static Categorie get_categorie(Enchere enchere) throws Exception{
        Categorie categorie=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idenchere"; 
        String[] value=new String[1];value[0]=new Integer(enchere.getidenchere()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Categorie(),condition,value,signe);
            categorie=(Categorie)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.get_categorie(Enchere) : "+e);
            e.printStackTrace();
        }
        return categorie;
    }

    public static Detailsenchere[] liste_details_enchere(Enchere enchere) throws Exception{
        Detailsenchere[] detail=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idenchere"; 
        String[] value=new String[1];value[0]=new Integer(enchere.getidenchere()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Detailsenchere(),condition,value,signe);
            detail=new Detailsenchere[o.length];
            for (int i=0;i<o.length ;i++ ) {
                detail[i]=(Detailsenchere)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.liste_details_enchere(Enchere) : "+e);
            e.printStackTrace();
        }
        return detail;
    }

    public static Encherephoto[] liste_photo(Enchere enchere) throws Exception{
        Encherephoto[] photos=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idenchere"; 
        String[] value=new String[1];value[0]=new Integer(enchere.getidenchere()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Encherephoto(),condition,value,signe);
            photos=new Encherephoto[o.length];
            for (int i=0;i<o.length ;i++ ) {
                photos[i]=(Encherephoto)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Enchere.liste_photo(Enchere) : "+e);
            e.printStackTrace();
        }
        return photos;
    }
}

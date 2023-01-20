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

public class S_Utilisateur {

    public Utilisateur get(int id) throws Exception{
        Utilisateur utilisateur=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idutilisateur"; 
        String[] value=new String[1];value[0]=new Integer(id).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Utilisateur(),condition,value,signe);
            utilisateur=(Utilisateur)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get(id) : "+e);
            e.printStackTrace();
        }
        return utilisateur;
    }

    public static Enchere[] get_enchere(Utilisateur utilisateur,int etat) throws Exception{
        Enchere[] enchere=null;
        Connexion connexion = null;
        String[] condition=new String[2];condition[0]="idutilisateur";condition[1]="etat"; 
        String[] value=new String[2];value[0]=new Integer(utilisateur.getidutilisateur()).toString();value[1]=new Integer(etat).toString();
        String[] signe=new String[2];signe[0]="=";signe[1]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Enchere(),condition,value,signe);
            enchere=new Enchere[o.length];
            for (int i=0;i<o.length ;i++ ) {
                enchere[i]=(Enchere)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get_enchere(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return enchere;
    }

    public static Gagnant[] get_enchere_gagner(Utilisateur utilisateur) throws Exception{
        Gagnant[] gagnant=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idutilisateur"; 
        String[] value=new String[1];value[0]=new Integer(utilisateur.getidutilisateur()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Gagnant(),condition,value,signe);
            gagnant=new Gagnant[o.length];
            for (int i=0;i<o.length ;i++ ) {
                gagnant[i]=(Gagnant)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get_enchere_gagner(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return gagnant;
    }

    public static Detailsenchere[] get_historique(Utilisateur utilisateur) throws Exception{
        Detailsenchere[] details=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idutilisateur"; 
        String[] value=new String[1];value[0]=new Integer(utilisateur.getidutilisateur()).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Detailsenchere(),condition,value,signe);
            details=new Detailsenchere[o.length];
            for (int i=0;i<o.length ;i++ ) {
                details[i]=(Detailsenchere)o[i];
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get_historique(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return details;
    }
}

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
            connexion.getConnexion().close();
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
            connexion.getConnexion().close();
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get_enchere(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return enchere;
    }

    public static Detailsenchere[] get_enchere_encours(Utilisateur utilisateur) throws Exception{
        Detailsenchere[] details=null;
        Detailsenchere[] dtls=null;
        Enchere[] enchere=null;
        int taille=0;
        try{
            Detailsenchere[] d=S_Detailsenchere.get_by_utilisateur(utilisateur.getidutilisateur());
            for (int i=0;i<d.length ;i++ ) {
                if (new S_Enchere().get(d[i].getidenchere()).getetat()==0) {
                    taille=taille+1;
                }
            }
            details=new Detailsenchere[taille];
            enchere=new Enchere[taille];
            taille=0;
            for (int i=0;i<d.length ;i++ ) {
                if (new S_Enchere().get(d[i].getidenchere()).getetat()==0) {
                    enchere[i]=new S_Enchere().get(d[i].getidenchere());
                    details[taille]=d[i];
                    taille=taille+1;
                }
            }
            taille=0;
            for (int i=0;i<enchere.length ;i++ ) {
                    int n=0;
                if(i!=enchere.length-1){
                    for (int v=i+1;v<enchere.length ;v++ ) {
                        if (enchere[i].getidenchere()==enchere[v].getidenchere()) {
                            n=1;
                        }
                    }
                }
                if (n==0) {
                    taille=taille+1;
                }
            }
            dtls=new Detailsenchere[taille];
            taille=0;
            for (int i=0;i<enchere.length ;i++ ) {
                    int n=0;
                if(i!=enchere.length-1){
                    for (int v=i+1;v<enchere.length ;v++ ) {
                        if (enchere[i].getidenchere()==enchere[v].getidenchere()) {
                            n=1;
                        }
                    }
                }
                if (n==0) {
                    dtls[taille]=details[i];
                    taille=taille+1;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get_enchere_encours(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return dtls;
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
            connexion.getConnexion().close();
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
            connexion.getConnexion().close();
        }
        catch (Exception e) {
            System.out.println("Error S_Utilisateur.get_historique(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return details;
    }
}

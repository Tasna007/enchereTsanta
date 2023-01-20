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

public class S_Gagnant {

    public Gagnant get(int id) throws Exception{
        Gagnant gagnant=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idgagnant"; 
        String[] value=new String[1];value[0]=new Integer(id).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Gagnant(),condition,value,signe);
            gagnant=(Gagnant)o[0];
        }
        catch (Exception e) {
            System.out.println("Error S_Gagnant.get(id) : "+e);
            e.printStackTrace();
        }
        return gagnant;
    }

    public static void gagner() throws Exception{
        Enchere[] enchere=null;
        Detailsenchere[] details=null;
        Utilisateur utilisateur=null;
        Connexion connexion = null;
        try{
            Gagnant gagnant=new Gagnant();
            enchere=S_Enchere.get_terminer();
            for (int i=0;i<enchere.length ;i++ ) {
                details=S_Enchere.liste_details_enchere(enchere[i]);
                utilisateur=S_Enchere.get_utilisateur(enchere[i]);
                gagnant.setidutilisateur(utilisateur.getidutilisateur());
                gagnant.setidenchere(enchere[i].getidenchere());
                gagnant.setiddetailsenchere(details[details.length-1].getiddetailsenchere());
                gagnant.setprix(details[details.length-1].getmise());
                gagnant.insert(connexion.getConnexion(),"Gagnant");
                S_Enchere.update_etat(enchere[i]);
                TS_Compte.updateCompteGagner(details[details.length-1].getmise(),TS_Compte.getVCompte(utilisateur.getidutilisateur()).getIdCompte());
            }
        }
        catch (Exception e) {
            System.out.println("Error S_Gagnant.gagner() : "+e);
            e.printStackTrace();
        }
    }
}
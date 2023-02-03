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

public class S_Detailsenchere {

    public Detailsenchere get(int id) throws Exception{
        Detailsenchere details=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="iddetailsenchere"; 
        String[] value=new String[1];value[0]=new Integer(id).toString(); 
        String[] signe=new String[1];signe[0]="="; 
        try{
            connexion = new Connexion();
            Object[] o=Requete.select(connexion.getConnexion(),new Detailsenchere(),condition,value,signe);
            details=(Detailsenchere)o[0];
            connexion.getConnexion().close();
        }
        catch (Exception e) {
            System.out.println("Error S_Detailsenchere.get(id) : "+e);
            e.printStackTrace();
        }
        return details;
    }
    public static Detailsenchere[] get_by_utilisateur(int id) throws Exception{
        Detailsenchere[] details=null;
        Connexion connexion = null;
        String[] condition=new String[1];condition[0]="idutilisateur"; 
        String[] value=new String[1];value[0]=new Integer(id).toString(); 
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
            System.out.println("Error S_Detailsenchere.get_by_utilisateur(id) : "+e);
            e.printStackTrace();
        }
        return details;
    }
}
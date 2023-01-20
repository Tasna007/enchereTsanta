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
        }
        catch (Exception e) {
            System.out.println("Error S_Detailsenchere.get(id) : "+e);
            e.printStackTrace();
        }
        return details;
    }
}
package util;
import model.*;
import service.*;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.Object;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

    public class Requete{

        public static Object[] select(Connection c,Object type,String[] condition,String[] value,String[] signe)throws Exception{
            Object[] retour=new Object[Requete.getCount(c,type,condition,value,signe)];
            for (int i=0;i<retour.length ;i++ ) {
                retour[i]=type.getClass().newInstance();
            }
            // System.out.println(getCount(c,type,condition,value));
            Field[] attribut=type.getClass().getDeclaredFields(); 
            Method[] fonction=new Method[attribut.length];
            for (int i=0;i<attribut.length ;i++ ) {
                fonction[i]=type.getClass().getDeclaredMethod("set"+attribut[i].getName(),attribut[i].getType()); 
            // System.out.println(attribut[i].getName());
            }
            Connection con=null;
            PreparedStatement m=null;
            ResultSet rs=null;
            try{
                String req="select * from "+type.getClass().getSimpleName();
                if (condition.length!=0) {
                    req=req+" where ";
                    for (int i=0;i<condition.length-1 ;i++ ) {
                        req=req+condition[i]+signe[i]+"'"+value[i]+"'"+" and ";
                    }
                    req=req+condition[condition.length-1]+signe[condition.length-1]+"'"+value[condition.length-1]+"'";
                }
                con=c;
                m=con.prepareStatement(req);
                rs=m.executeQuery();
            // System.out.println(req);
                int i=0;
                while(rs.next()){
                    // System.out.println("dgfh");
                    for (int v=0;v<fonction.length ;v++ ) {
                    String identite="get"+attribut[v].getType().getSimpleName().substring(0,1).toUpperCase()+attribut[v].getType().getSimpleName().substring(1);
                    // System.out.println(identite);
                    Method a=rs.getClass().getMethod(identite,String.class);
                    // System.out.println(fonction[v].getName());
                    fonction[v].invoke(retour[i],a.invoke(rs,attribut[v].getName()));
                    // System.out.println(rs.getObject(attribut[v].getName()));
                    }
                    i++;
                }
                // for (int v=0;v<retour.length ;v++ ) {
                //     Ecriture e=new Ecriture();
                //     e=(Ecriture)retour[v];
                //     System.out.println(e.getintitule());
                // }
            }
            catch(Exception ex){
                  throw ex;
              }
            finally{
                // if(con!=null){
             //        con.close();
             //    }
                if(rs!=null){
                    rs.close();
                }
                if (m!=null) {
                    m.close();
                }
            }
            return retour;
        }


        // public static void insert(Connection c,Object type,String[] champ,String value)throws Exception{
        //     Connection co=null;
        //     PreparedStatement m=null;
        //     try{
        //         String a="(";
        //         String b="(";
        //         for (int i=0;i<champ.length-1 ;i++ ) {
        //             a=a+champ[i]+",";
        //             // b=b+value[i]+",";
        //         }
        //         a=a+champ[champ.length-1]+")";
        //         // b=b+value[champ.length-1]+")";
        //         System.out.println("insert into "+type.getClass().getSimpleName()+a+" values "+value);
        //         m=co.prepareStatement("insert into "+type.getClass().getSimpleName()+a+" values "+value);
        //         for (int i=0;i<champ.length ;i++ ) {
        //             m.setObject(i,champ[i]);
        //         }
        //         m.executeUpdate();
        //     }
        //     catch(Exception ex){
        //       throw ex;
        //     }
        //     finally{
        //         if(co!=null){
        //             co.close();
        //         }
        //         if (m!=null) {
        //             m.close();
        //         }
        //     }
        // }


        public static int getCount(Connection c,Object type,String[] condition,String[] value,String[] signe)throws Exception{
            Connection con=null;
            PreparedStatement m=null;
            ResultSet rs=null;
            int retour;
            try{
                String req="select count(*) from "+type.getClass().getSimpleName();
                if (condition.length!=0) {
                    req=req+" where ";
                    for (int i=0;i<condition.length-1 ;i++ ) {
                        req=req+condition[i]+signe[i]+"'"+value[i]+"'"+" and ";
                    }
                    req=req+condition[condition.length-1]+signe[condition.length-1]+"'"+value[condition.length-1]+"'";
                }
                // System.out.println(req);
                con=c;
                m=con.prepareStatement(req);
                rs=m.executeQuery();
                rs.next();
                retour=rs.getInt("count");
              }
              catch(Exception ex){
                  throw ex;
              }
            finally{
            	// if(con!=null){
             //        con.close();
             //    }
                if(rs!=null){
                    rs.close();
                }
                if (m!=null) {
                    m.close();
                }
            }
            return retour;
        }

    }
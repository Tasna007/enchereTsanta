/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Tan
 */
public class ObjetBDD {
    public static ArrayList<String> getColumnOfDatabase(Connection connexion,String table,boolean primaryKey){
        ArrayList<String> retour = new ArrayList<String>();
        Statement statement;
        try {
            statement = connexion.createStatement ();
            ResultSet result = statement.executeQuery("SELECT * FROM "+table+" limit 0");
            ResultSetMetaData resultMetaData = result.getMetaData();
            for (int index = 1; index < resultMetaData.getColumnCount()+1; index++) {
                if (primaryKey) {
                    retour.add(resultMetaData.getColumnName(index));
                }
                else{
                    if (resultMetaData.isAutoIncrement(index) == primaryKey) {
                        retour.add(resultMetaData.getColumnName(index));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error ObjetBDD getColumnOfDatabase() : "+e);
            e.printStackTrace();
        }
        return retour;
    }

    public ArrayList<String> getAnotationFields(Connection connexion,String table,boolean primaryKey){
        Field[] champ = this.getClass().getDeclaredFields();
        ArrayList<String> columnOfDatabase = ObjetBDD.getColumnOfDatabase(connexion,table,primaryKey);
        ArrayList<String> retour = new ArrayList<String>();
        for (int i =0 ;i < champ.length ;i++ ) {
            for (int j = 0; j < columnOfDatabase.size(); j++) {
                // System.out.println(columnOfDatabase.get(j)+" : "+champ[i].getName()+"->"+columnOfDatabase.get(j).compareToIgnoreCase(champ[i].getName()));
                if (columnOfDatabase.get(j).compareToIgnoreCase(champ[i].getName())==0){
                    retour.add(champ[i].getName());
                }    
            }
        }
        return retour;
    }
    public ArrayList<String> getAnotationMethods (Connection connexion,String fonction,String table,boolean primaryKey){
        ArrayList<String> anotationFields = this.getAnotationFields(connexion, table, primaryKey);
        ArrayList<String> retour = new ArrayList<String>();
        for (int i =0 ;i < anotationFields.size() ;i++ ) {
            retour.add(fonction + anotationFields.get(i).substring(0, 1).toUpperCase() + anotationFields.get(i).substring(1));
        }
        return retour;
    }
    public ArrayList<Field> getMyFieldsArray(Connection connexion,String table,boolean primaryKey){
        Field[] champ = this.getClass().getDeclaredFields();
        ArrayList<String> columnOfDatabase = ObjetBDD.getColumnOfDatabase(connexion,table,primaryKey);
        ArrayList<Field> retour = new ArrayList<Field>();
        for (int i =0 ;i < champ.length ;i++ ) {
            for (int j = 0; j < columnOfDatabase.size(); j++) {
                // System.out.println(columnOfDatabase.get(j)+" : "+champ[i].getName()+"->"+columnOfDatabase.get(j).compareToIgnoreCase(champ[i].getName()));
                if (columnOfDatabase.get(j).compareToIgnoreCase(champ[i].getName())==0){
                    retour.add(champ[i]);
                }    
            }
        }
        return retour;
    }
    public String scriptSelect(Connection connexion,String table){
        boolean primaryKey = true;
        ArrayList<String> anotationFields = this.getAnotationFields(connexion,table,primaryKey);
        ArrayList<String> anotationMethods = this.getAnotationMethods(connexion,"get",table,primaryKey);
        Class classer = this.getClass();
        String request = "SELECT * FROM "+table+" WHERE 1=1";
        for (int i = 0; i < anotationFields.size(); i++) {
            try {
                Method function = classer.getMethod(anotationMethods.get(i));
                if((function.invoke(this)!=null)&&(!function.invoke(this).toString().equals("-1234567"))&&(!function.invoke(this).toString().equals("-1234567.0"))){
                    request += " AND "+anotationFields.get(i)+" = '"+function.invoke(this)+"'";
                }
            } catch (Exception e) {
                System.out.println("Error ObjetBDD  scriptSelect() : " + e);
                e.printStackTrace();
            }
        }
        System.out.println(request);
        return request;
    }
    public String scriptInsert(Connection connexion,String table){
        boolean primaryKey = false;
        ArrayList<String> anotationFields = this.getAnotationFields(connexion,table,primaryKey);
        ArrayList<String> anotationMethods = this.getAnotationMethods(connexion,"get",table,primaryKey);
        Class classer = this.getClass();
         String request = "INSERT INTO "+table+ "(";
         for (int i = 0; i < anotationFields.size(); i++) {
             try {
                 Method function = classer.getMethod(anotationMethods.get(i));
                 if((function.invoke(this)!=null)&&(!function.invoke(this).toString().equals("-1234567"))&&(!function.invoke(this).toString().equals("-1234567.0"))){
                     request += anotationFields.get(i)+",";
                 }
             } catch (Exception e) {
                 System.out.println("Error ObjetBDD scriptInsert() : " + e);
                 e.printStackTrace();
             }
         }
         request = request.substring(0,request.length()-1);
         request += ") VALUES (";
         for (int i = 0; i < anotationFields.size(); i++) {
             try {
                 Method function = classer.getMethod(anotationMethods.get(i));
                 if((function.invoke(this)!=null)&&(!function.invoke(this).toString().equals("-1234567"))&&(!function.invoke(this).toString().equals("-1234567.0"))){
                     request += "'"+function.invoke(this)+"',";    
                 }
             } catch (Exception e) {
                 System.out.println("Error : GenericDao.java : scriptInsert()" + e);
                 e.printStackTrace();
             }
         }
         request = request.substring(0,request.length()-1);
         request += ")";
         System.out.println(request);
         return request;
     }

    public Object[] select(Connection connexion,String table){
        ArrayList<Object> retour = new ArrayList<Object>();
        boolean primaryKey = true;
        ArrayList<String> anotationFields = this.getAnotationFields(connexion,table,primaryKey);
        ArrayList<String> anotationMethods = this.getAnotationMethods(connexion,"set",table,primaryKey);
        ArrayList<Field> fieldsTypeFields = this.getMyFieldsArray(connexion,table,primaryKey);
        Class classer = this.getClass();
        try{
            String request = this.scriptSelect(connexion,table);
            Statement statement = connexion.createStatement ();
            ResultSet result = statement.executeQuery (request);
            while(result.next()){
                Object stock = classer.newInstance();
                for (int i = 0; i < anotationFields.size(); i++) {
                    Method function = classer.getMethod(anotationMethods.get(i),fieldsTypeFields.get(i).getType());
                    // anotationMethods.get(i),classer.forName(anotationTypes.get(i))
                    function.invoke(stock,result.getObject(anotationFields.get(i)));
                }
                retour.add(stock);
            }
        }
        catch(Exception e){
            System.out.println("Error ObjetBDD select() : " + e);
            e.printStackTrace();
        }
        return retour.toArray();
    }
    public void insert (Connection connexion,String table){
        String request = scriptInsert(connexion,table);
        try{
            Statement statement = connexion.createStatement();
            statement.executeUpdate(request);
        }
        catch (Exception e) {
            System.out.println("Error ObjetBDD insert() : " + e);
            e.printStackTrace();
        }
    }
    public String scriptDelete(Connection connexion,String table){
        boolean primaryKey = true;
        ArrayList<String> anotationFields = this.getAnotationFields(connexion,table,primaryKey);
        ArrayList<String> anotationMethods = this.getAnotationMethods(connexion,"get",table,primaryKey);
        Class classer = this.getClass();
        String request = "DELETE FROM "+table+" WHERE 1=1";
        for (int i = 0; i < anotationFields.size(); i++) {
            try {
                Method function = classer.getMethod(anotationMethods.get(i));
                if((function.invoke(this)!=null)&&(!function.invoke(this).toString().equals("-1234567"))&&(!function.invoke(this).toString().equals("-1234567.0"))){
                    // System.out.println(anotationFields.get(i)+" : "+function.invoke(this));
                    request += " AND "+anotationFields.get(i)+" = '"+function.invoke(this)+"'";
                }
            } catch (Exception e) {
                System.out.println("Error ObjetBDD scriptDelete() : " + e);
                e.printStackTrace();
            }
        }
        System.out.println(request);
        return request;
    }
    public void delete (Connection connexion,String table){
        String request = scriptDelete(connexion,table);
        try{
            Statement statement = connexion.createStatement();
            statement.executeUpdate(request);
        }
        catch (Exception e) {
            System.out.println("Error ObjetBDD delete() : " + e);
            e.printStackTrace();
        }
    }
    public String scriptUpdate(Connection connexion,String table,Object newObjet){
        boolean primaryKey = true;
        ArrayList<String> anotationFields = this.getAnotationFields(connexion,table,primaryKey);
        ArrayList<String> anotationMethods = this.getAnotationMethods(connexion,"get",table,primaryKey);
        ArrayList<String> anotationFieldsNew = ((ObjetBDD) newObjet).getAnotationFields(connexion,table,primaryKey);
        ArrayList<String> anotationMethodsNew = ((ObjetBDD) newObjet).getAnotationMethods(connexion,"get",table,primaryKey);
        // System.out.println("WHALA : "+anotationFieldsNew);
        Class classer = this.getClass();
        String request = "UPDATE " + table + " SET ";
        for (int i = 0; i < anotationFields.size(); i++) {
            try {
                Method function = classer.getMethod(anotationMethodsNew.get(i));
                if((function.invoke(this)!=null)&&(!function.invoke(this).toString().equals("-1234567"))&&(!function.invoke(this).toString().equals("-1234567.0"))){
                    request += anotationFieldsNew.get(i)+" = '"+function.invoke(newObjet)+"',";
                }
            } catch (Exception e) {
                System.out.println("Error ObjetBDD scriptUpdate() : " + e);
                e.printStackTrace();
            }
        }
        request = request.substring(0,request.length()-1);
        classer = newObjet.getClass();
        request += " WHERE ";
        for (int i = 0; i < anotationFields.size(); i++) {
            try {
                Method function = classer.getMethod(anotationMethods.get(i));
                if((function.invoke(this)!=null)&&(!function.invoke(this).toString().equals("-1234567"))&&(!function.invoke(this).toString().equals("-1234567.0"))){
                    request += anotationFields.get(i)+" = '"+function.invoke(this)+"' AND ";
                }
            } catch (Exception e) {
                System.out.println("Error ObjetBDD scriptUpdate() : " + e);
                e.printStackTrace();
            }
        }
        request = request.substring(0,request.length()-4);
        System.out.println(request);
        return request;
    }
    public void update (Connection connexion,String table,Object newObjet){
        String request = scriptUpdate(connexion,table,newObjet);
        try{
            Statement statement = connexion.createStatement();
            statement.executeUpdate(request);
        }
        catch (Exception e) {
            System.out.println("Error ObjetBDD update() : " + e);
            e.printStackTrace();
        }
    }
}

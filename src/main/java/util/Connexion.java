/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;

public class Connexion {
    
    String driver = "org.postgresql.Driver";
    String nameDatabase = "railway";
    // String nameDatabase = "railway";
    String url = "jdbc:postgresql://containers-us-west-53.railway.app:6996/"+nameDatabase;
    // String url = "jdbc:postgresql://containers-us-west-134.railway.app:6124/"+nameDatabase;
    String user = "postgres";
    // String user = "postgres";
    String password = "5PCXUgb1vMtXZYcmlKK1";
    // String password = "atpeoFWtkUC4HmQWkR6a";
       // Boolean autoCommit = false;

    public Connection getConnexion()throws Exception{
        Connection connexion = null;
        try {
            Class.forName(driver);
            connexion = DriverManager.getConnection(url,user,password);
//            connexion.setAutoCommit(autoCommit);    
        } 
        catch (Exception e) {
            throw e;    
        }
        return connexion;
    }
}

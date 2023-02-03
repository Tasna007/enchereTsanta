/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;

public class Connexion {
    
    String driver = "org.postgresql.Driver";
    // String nameDatabase = "enchere";
    String nameDatabase = "railway";
    // String url = "jdbc:postgresql://localhost:5432/"+nameDatabase;
    String url = "jdbc:postgresql://containers-us-west-53.railway.app:6996/"+nameDatabase;
    // String user = "s5";
    String user = "postgres";
    // String password = "s5";
    String password = "5PCXUgb1vMtXZYcmlKK1";
    //    Boolean autoCommit = false;

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

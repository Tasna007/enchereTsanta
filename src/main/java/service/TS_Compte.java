package service;

import java.sql.*;

import model.T_Compte;
import model.TV_Compte;
import util.Connexion;

public class TS_Compte {
    public static boolean newCompte() {
        boolean retour = false;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            new T_Compte(true).insert(connexion.getConnexion(), "Compte");
            retour = true;
        } catch (Exception e) {
            System.out.println("Error S_Compte.newCompte() : " + e);
            e.printStackTrace();
        }
        return retour;
    }

    public static int getIdMaxCompte() throws Exception {
        PreparedStatement statement = null;
        ResultSet result = null;
        Connexion connexion = null;
        try{
            connexion = new Connexion();
            statement = connexion.getConnexion().prepareStatement("SELECT MAX(idCompte) AS maxCompte FROM compte");
            result = statement.executeQuery();
            while(result.next()){
                return result.getInt("maxcompte");
            }
        }
        catch(Exception e){
            System.out.println("Error S_Compte.getIdMaxCompte() : " + e);
            e.printStackTrace();
        }
        finally{
            if(statement != null) {
                statement.close();
            }
            if(result != null) {
                result.close();
            }
        }
        return 0;
    }

    public static TV_Compte getVCompte(int idUtilisateur) {
        TV_Compte retour = null;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            retour = TV_Compte.cast(new TV_Compte(idUtilisateur).select(connexion.getConnexion(), "V_Compte"))[0];
        } catch (Exception e) {
            System.out.println("Error S_Compte.newCompte() : " + e);
            e.printStackTrace();
        }
        return retour;
    }
    
    public static void updateCompteMise(double mise,int idCompte) throws Exception {
        PreparedStatement statement = null;
        Connexion connexion = null;
        try{
            connexion = new Connexion();
            statement = connexion.getConnexion().prepareStatement("UPDATE Compte SET Mise = Mise + ? WHERE idCompte = ?");
            statement.setDouble(1, mise);
            statement.setInt(2, idCompte);
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw e;
        }
        finally{
            if(statement != null) statement.close();
        }
    }

    public static void updateCompteGagner(double mise,int idCompte) throws Exception {
        PreparedStatement statement = null;
        Connexion connexion = null;
        try{
            connexion = new Connexion();
            statement = connexion.getConnexion().prepareStatement("UPDATE Compte SET Mise = Mise - ? WHERE idCompte = ?");
            statement.setDouble(1, mise);
            statement.setInt(2, idCompte);
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw e;
        }
        finally{
            if(statement != null) statement.close();
        }
    }
}

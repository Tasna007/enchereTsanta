package service;

import model.T_Utilisateur;
import util.Connexion;

public class TS_Utilisateur {
    public static boolean login(T_Utilisateur user) throws Exception{
        boolean retour = false;
        T_Utilisateur[] stock = null;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            stock = T_Utilisateur.cast(user.select(connexion.getConnexion(),"Utilisateur"));
            if (stock.length == 1) {
                retour = true;
            }
        } catch (Exception e) {
            System.out.println("Error S_Utilisateur.login(Utilisateur) : " + e);
            e.printStackTrace();
        }
        return retour;
    }
    public static boolean createUser(T_Utilisateur user){
        boolean retour = false;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            TS_Compte.newCompte();
            user.setIdCompte(TS_Compte.getIdMaxCompte());
            user.insert(connexion.getConnexion(),"Utilisateur");
            retour = true;
        } catch (Exception e) {
            System.out.println("Error S_Utilisateur.createUser(Utilisateur) : " + e);
            e.printStackTrace();
        }
        return retour;
    }
}

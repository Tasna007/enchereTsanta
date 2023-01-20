package service;

import model.T_Enchere;
import util.Connexion;

public class TS_Enchere {
    public static boolean insert(T_Enchere enchere){
        boolean retour = false;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            enchere.insert(connexion.getConnexion(),"Enchere");
            retour = true;
        } catch (Exception e) {
            System.out.println("Error S_Enchere.insert(Enchere) : " + e);
            e.printStackTrace();
        }
        return retour;
    }
}

package service;

import model.T_DetailsEnchere;
import util.Connexion;

public class TS_DetailsEnchere {
    public static boolean insert(T_DetailsEnchere de){
        boolean retour = false;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            de.insert(connexion.getConnexion(),"DetailsEnchere");
            retour = true;
        } catch (Exception e) {
            System.out.println("Error S_Enchere.insert(Enchere) : " + e);
            e.printStackTrace();
        }
        return retour;
    }
}

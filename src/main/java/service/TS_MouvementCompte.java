package service;

import model.T_MouvementCompte;
import util.Connexion;

public class TS_MouvementCompte {
    public static boolean demandeRechargeCompte(T_MouvementCompte moove){
        boolean retour = false;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            moove.insert(connexion.getConnexion(),"MouvementCompte");
            retour = true;
        } catch (Exception e) {
            System.out.println("Error S_MouvementCompte.insert(MouvementCompte) : " + e);
            e.printStackTrace();
        }
        return retour;
    }
}

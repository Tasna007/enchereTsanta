package service;

import model.Categorie;
import model.T_Categorie;
import util.Connexion;

public class TS_Categorie {
    public static T_Categorie[] getAll(){
        T_Categorie[] retour = null;
        Connexion connexion = null;
        try {
            connexion = new Connexion();
            retour = T_Categorie.cast(new T_Categorie().select(connexion.getConnexion(),"Categorie"));
        } catch (Exception e) {
            System.out.println("Error TS_Categorie.select() : " + e);
            e.printStackTrace();
        }
        return retour;
    }
}

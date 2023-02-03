package model;

import util.ObjetBDD;

public class T_Categorie extends ObjetBDD{
    int idCategorie = -1234567;
    String categorie;
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public static T_Categorie[] cast(Object[] objet){
        T_Categorie[] retour = new T_Categorie[objet.length];
        for (int indice = 0;indice<objet.length;indice++) {
            retour[indice] = (T_Categorie) objet[indice];
        }
        return retour;
    }

}

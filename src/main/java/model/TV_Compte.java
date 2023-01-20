package model;

import util.ObjetBDD;

public class TV_Compte extends ObjetBDD{
    int idUtilisateur = -1234567;
    int idCompte = -1234567;
    double argent = -1234567;
    
    
    public TV_Compte() {
    }

    public TV_Compte(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public int getIdCompte() {
        return idCompte;
    }
    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }
    public double getArgent() {
        return argent;
    }
    public void setArgent(double argent) {
        this.argent = argent;
    }
    public static TV_Compte[] cast(Object[] objet){
        TV_Compte[] retour = new TV_Compte[objet.length];
        for (int indice = 0;indice<objet.length;indice++) {
            retour[indice] = (TV_Compte) objet[indice];
        }
        return retour;
    }
}

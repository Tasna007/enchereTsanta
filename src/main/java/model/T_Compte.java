package model;

import util.ObjetBDD;

public class T_Compte extends ObjetBDD{
    int idCompte = -1234567;
    double solde = -1234567;
    double mise = -1234567;

    public T_Compte() {
    }

    public T_Compte(boolean create) {
        if (create) {
            this.solde = 0;
            this.mise = 0;
        }
    }
    public int getIdCompte() {
        return idCompte;
    }
    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }
    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public double getMise() {
        return mise;
    }
    public void setMise(double mise) {
        this.mise = mise;
    }

    public static T_Compte[] cast(Object[] objet){
        T_Compte[] retour = new T_Compte[objet.length];
        for (int indice = 0;indice<objet.length;indice++) {
            retour[indice] = (T_Compte) objet[indice];
        }
        return retour;
    }
}

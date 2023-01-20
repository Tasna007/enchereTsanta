package model;

import java.util.Date;

import util.ObjetBDD;

public class T_MouvementCompte extends ObjetBDD{
    int idMouvementCompte = -1234567;
    int idUtilisateur = -1234567;
    int idEtat = -1234567;
    Date dateMouvement;
    Date dateDemande;
    double somme = -1234567;

    

    public T_MouvementCompte(int idUtilisateur, double somme) {
        this.idUtilisateur = idUtilisateur;
        this.somme = somme;
    }
    
    public int getIdMouvementCompte() {
        return idMouvementCompte;
    }
    public void setIdMouvementCompte(int idMouvementCompte) {
        this.idMouvementCompte = idMouvementCompte;
    }
    public int getIdEtat() {
        return idEtat;
    }
    public void setIdEtat(int idEtat) {
        this.idEtat = idEtat;
    }
    public Date getDateMouvement() {
        return dateMouvement;
    }
    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }
    public double getSomme() {
        return somme;
    }
    public void setSomme(double somme) {
        this.somme = somme;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }
    
}

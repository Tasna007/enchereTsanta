package model;

import util.ObjetBDD;

public class T_Enchere extends ObjetBDD{
    int idEnchere = -1234567;
    String produit;
    double prix_planche = -1234567;
    int duree = -1234567;
    String description;
    int idUtilisateur = -1234567;
    int idCategorie = -1234567;
    int etat = -1234567;


    public T_Enchere(String produit, double prix_planche, int duree, String description, int idUtilisateur,
            int idCategorie) {
        this.produit = produit;
        this.prix_planche = prix_planche;
        this.duree = duree;
        this.description = description;
        this.idUtilisateur = idUtilisateur;
        this.idCategorie = idCategorie;
    }
    public T_Enchere() {
    }
    public T_Enchere(String produit, double prix_planche, int duree, String description, int idUtilisateur,
            int idCategorie, int etat) {
        this.produit = produit;
        this.prix_planche = prix_planche;
        this.duree = duree;
        this.description = description;
        this.idUtilisateur = idUtilisateur;
        this.idCategorie = idCategorie;
        this.etat = etat;
    }
    public String getProduit() {
        return produit;
    }
    public void setProduit(String produit) {
        this.produit = produit;
    }
    public double getPrix_planche() {
        return prix_planche;
    }
    public void setPrix_planche(double prix_planche) {
        this.prix_planche = prix_planche;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    public int getIdEnchere() {
        return idEnchere;
    }
    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    
}

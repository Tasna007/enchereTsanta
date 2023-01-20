package model;

import java.util.Date;

import util.ObjetBDD;

public class T_Utilisateur extends ObjetBDD{
    int idUtilisateur = -1234567;
    int idCompte = -1234567;
    String nom;
    String prenom;
    Date naissance;
    String email;
    String password;
    Date inscription;
    public T_Utilisateur() {
    }
    public T_Utilisateur(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public T_Utilisateur(String nom, String prenom, Date naissance, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.email = email;
        this.password = password;
    }
    public T_Utilisateur(String nom, String prenom, Date naissance, String email, String password, Date inscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.email = email;
        this.password = password;
        this.inscription = inscription;
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
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static T_Utilisateur[] cast(Object[] objet){
        T_Utilisateur[] retour = new T_Utilisateur[objet.length];
        for (int indice = 0;indice<objet.length;indice++) {
            retour[indice] = (T_Utilisateur) objet[indice];
        }
        return retour;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getNaissance() {
        return naissance;
    }
    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
    public Date getInscription() {
        return inscription;
    }
    public void setInscription(Date inscription) {
        this.inscription = inscription;
    }
}

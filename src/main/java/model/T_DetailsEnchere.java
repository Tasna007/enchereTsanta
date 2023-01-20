package model;

import java.sql.Date;

import util.ObjetBDD;

public class T_DetailsEnchere extends ObjetBDD{
    int idDetailsEnchere;
    int idEnchere;
    int idUtilisateur;
    double mise;
    Date dateMise;

    
    public T_DetailsEnchere(int idEnchere, int idUtilisateur, double mise) {
        this.idEnchere = idEnchere;
        this.idUtilisateur = idUtilisateur;
        this.mise = mise;
    }
    public int getIdDetailsEnchere() {
        return idDetailsEnchere;
    }
    public void setIdDetailsEnchere(int idDetailsEnchere) {
        this.idDetailsEnchere = idDetailsEnchere;
    }
    public int getIdEnchere() {
        return idEnchere;
    }
    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public double getMise() {
        return mise;
    }
    public void setMise(double mise) {
        this.mise = mise;
    }
    public Date getDateMise() {
        return dateMise;
    }
    public void setDateMise(Date date) {
        this.dateMise = date;
    }

    
}

package com.ehpadManagement.userManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;


    public User() {

    }
    public User(String nom){
        this.nom = nom;
        this.prenom = "Jack";
        this.statut= Statut.BENEFICIAIRE;

    }
    public enum Statut{
        BENEVOLE,
        ADMIN,
        BENEFICIAIRE
    };
    Statut statut;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public long getId() {
        return id;
    }
    public Statut getStatut() {
        return statut;
    }
    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Personne {nom=" + nom + ", prenom=" + prenom + ", id=" + id+" }" ;
    }




}
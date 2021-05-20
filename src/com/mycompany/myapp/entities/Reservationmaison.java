/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artefact.myapp.entities;


/**
 *
 * @author Dell
 */
public class Reservationmaison {
    
 private int id;
    private int maisondhote_id;
    private String nom;
    private String prenom;
    private int numerodetele;
    private String startat;
    private String endat;
    private String Email ;
    public Reservationmaison() {
    }

    public Reservationmaison(String Email) {
        this.Email = Email;
    }

    

    public int getId() {
        return id;
    }

    public int getMaisondhote_id() {
        return maisondhote_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNumerodetele() {
        return numerodetele;
    }

    public String getEmail() {
        return Email;
    }

    public String getStartat() {
        return startat;
    }

    public void setStartat(String startat) {
        this.startat = startat;
    }

    public String getEndat() {
        return endat;
    }

    public void setEndat(String endat) {
        this.endat = endat;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setMaisondhote_id(int maisondhote_id) {
        this.maisondhote_id = maisondhote_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumerodetele(int numerodetele) {
        this.numerodetele = numerodetele;
    }

    @Override
    public String toString() {
        return "Reservationmaison{" + "id=" + id + ", maisondhote_id=" + maisondhote_id + ", nom=" + nom + ", prenom=" + prenom + ", numerodetele=" + numerodetele + ", startat=" + startat + ", endat=" + endat + ", Email=" + Email + '}';
    }

   
    

    
}

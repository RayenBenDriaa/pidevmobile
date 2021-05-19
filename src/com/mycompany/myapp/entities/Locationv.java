/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Nour
 */
public class Locationv {
     private int id,voiture_id,numerodetelephone;
    private String nom,prenom,permis;
    private String startat,endat;
    private String heuredebut,heurefin;

    public Locationv() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoiture_id() {
        return voiture_id;
    }

    public void setVoiture_id(int voiture_id) {
        this.voiture_id = voiture_id;
    }

    public int getNumerodetelephone() {
        return numerodetelephone;
    }

    public void setNumerodetelephone(int numerodetelephone) {
        this.numerodetelephone = numerodetelephone;
    }

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

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
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

//    public Date getStartat() {
//        return startat;
//    }
//
//    public void setStartat(Date startat) {
//        this.startat = startat;
//    }
//
//    public Date getEndat() {
//        return endat;
//    }
//
//    public void setEndat(Date endat) {
//        this.endat = endat;
//    }

    public String getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(String heuredebut) {
        this.heuredebut = heuredebut;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
    }

    @Override
    public String toString() {
        return "Locationv{" + "id=" + id + ", voiture_id=" + voiture_id + ", numerodetelephone=" + numerodetelephone + ", nom=" + nom + ", prenom=" + prenom + ", permis=" + permis + ", startat=" + startat + ", endat=" + endat + ", heuredebut=" + heuredebut + ", heurefin=" + heurefin + '}';
    }

   
   

    
    
}

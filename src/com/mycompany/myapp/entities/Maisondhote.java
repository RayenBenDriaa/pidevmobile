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
public class Maisondhote {
    private int id;
    private String nomMaison;
    private String ville;
    private String address;
    private int nombrechambres;
    private double prix;
    private String description;
    private String image;
    private boolean disponible;

    public Maisondhote() {
    }

    public Maisondhote(int id, String nomMaison, String ville, String address, int nombrechambres, double prix, String description, String image, boolean disponible) {
        this.id = id;
        this.nomMaison = nomMaison;
        this.ville = ville;
        this.address = address;
        this.nombrechambres = nombrechambres;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.disponible = disponible;
    }

    public Maisondhote(String nomMaison, String ville, String address, int nombrechambres, double prix, String description, String image, boolean disponible) {
        this.nomMaison = nomMaison;
        this.ville = ville;
        this.address = address;
        this.nombrechambres = nombrechambres;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMaison() {
        return nomMaison;
    }

    public void setNomMaison(String nomMaison) {
        this.nomMaison = nomMaison;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNombrechambres() {
        return nombrechambres;
    }

    public void setNombrechambres(int nombrechambres) {
        this.nombrechambres = nombrechambres;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Maisondhote{" + "id=" + id + ", nomMaison=" + nomMaison + ", ville=" + ville + ", address=" + address + ", nombrechambres=" + nombrechambres + ", prix=" + prix + ", description=" + description + ", image=" + image + ", disponible=" + disponible + '}';
    }
    
    
}

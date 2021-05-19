/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author wale
 */
public class Evenement {
    private int id_event;
    private String nom_event;
    private java.util.Date start_at;
    private java.util.Date end_at;
    private String ville;
    private String description;
    private String image;
    private float rating;
    private ImageView img;

    public Evenement() {
    }

    public Evenement(int id_event, String nom_event, java.util.Date start_at, java.util.Date end_at, String ville, String description, String image, float rating) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.start_at = start_at;
        this.end_at = end_at;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    public Evenement(String nom_event, java.util.Date start_at, java.util.Date end_at, String ville, String description, String image, float rating) {
        this.nom_event = nom_event;
        this.start_at = start_at;
        this.end_at = end_at;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    
    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public java.util.Date getStart_at() {
        return start_at;
    }

    public void setStart_at(java.util.Date start_at) {
        this.start_at = start_at;
    }

    public java.util.Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(java.util.Date end_at) {
        this.end_at = end_at;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", start_at=" + start_at + ", end_at=" + end_at + ", ville=" + ville + ", description=" + description + ", image=" + image + ", rating=" + rating + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Rayen Ben Driaa
 */
public class user {
    Integer id_user;
    String Username;
    String nom;
    String prenom;
    String Email;
    String Password;
    String Role;
    String image;
    String reset_token;

    public user() {
        
    }

    public user(String Username, String nom, String prenom, String Email, String Password) {
        
        
        
        this.Username = Username;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Password = Password;
        
        
        
    }

    public user(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }
    

    public Integer getId_user() {
        return id_user;
    }

    public String getUsername() {
        return Username;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public String getImage() {
        return image;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", Username=" + Username + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", Password=" + Password + '}';
    }

    
    
    
    
    
}

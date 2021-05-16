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
public class Reclamation {
    Integer id_reclamation;
    String text_reclamation;
    Integer iduser ;

    public Reclamation() {
    }

    public Reclamation(Integer id_reclamation, String text_reclamation, Integer iduser) {
        this.id_reclamation = id_reclamation;
        this.text_reclamation = text_reclamation;
        this.iduser = iduser;
    }

    public Integer getId_reclamation() {
        return id_reclamation;
    }

    public String getText_reclamation() {
        return text_reclamation;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setId_reclamation(Integer id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setText_reclamation(String text_reclamation) {
        this.text_reclamation = text_reclamation;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }
    
    
    
}

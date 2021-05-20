/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.artefact.myapp.entities.Maisondhote;
import com.artefact.myapp.services.ServiceMaisondhote;

/**
 *
 * @author Dell
 */
public class AddMaisonForm extends Form {
    public AddMaisonForm(Form previous){
        setTitle("Ajoutez une nouvelle maison");
        setLayout(BoxLayout.y());
        
        TextField tfNomMaison = new TextField("","NomMaison");
        TextField tfVille= new TextField("", "Ville");
        TextField tfAddress = new TextField("","Address");
        TextField tfNbch= new TextField("", "nombrechambres");
        TextField tfPrix= new TextField("", "Prix");
        TextField tfDescription = new TextField("","Description");

        Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNomMaison.getText().length()==0)||(tfVille.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Maisondhote v = new Maisondhote();
                        v.setNomMaison(tfNomMaison.getText());
                        v.setVille(tfVille.getText());
                        v.setAddress(tfAddress.getText());
                                                v.setNombrechambres(Integer.parseInt(tfNbch.getText()));

                        v.setPrix(Integer.parseInt(tfPrix.getText()));
                        v.setDescription(tfDescription.getText());

                        if( new ServiceMaisondhote().addMaisondhote(v))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }                    
                }                
            }
        });
        addAll(tfNomMaison,tfVille,tfAddress,tfNbch,tfPrix,tfDescription,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
        
        
    }
    
    
}


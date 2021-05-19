/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Locationv;
import com.mycompany.myapp.services.ServiceLocationv;


/**
 *
 * @author Nour
 */
public class ModifierLocationForm extends Form {
       Form current;
    public ModifierLocationForm(Form previous,Locationv n) {
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setTitle("Modifiez  location");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(false);
        
        
        

               TextField nom = new TextField(String.valueOf(n.getNom()) , "Nom" , 20 , TextField.ANY);
                TextField prenom = new TextField(String.valueOf(n.getPrenom()) , "Prenom" , 20 , TextField.ANY);
                
      
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       btnModifier.addPointerPressedListener(l ->   { 
           
      n.setNom(nom.getText());
      n.setPrenom(prenom.getText());
     

       
       if(ServiceLocationv.getInstance().modifierLocation(n)) { 
           new ListLocationvForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListLocationvForm(previous).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, nom,prenom,
              
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}


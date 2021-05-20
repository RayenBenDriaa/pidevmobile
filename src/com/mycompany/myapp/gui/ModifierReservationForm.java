/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.artefact.myapp.entities.Reservationmaison;
import com.artefact.myapp.services.ServiceReservationmaison;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Dell
 */

    
  public class ModifierReservationForm extends Form {
       Form current;
    public ModifierReservationForm(Form previous,Reservationmaison v) {
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setTitle("Modifiez votre reservation");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(false);
        
        
        

               TextField contenu = new TextField(String.valueOf(v.getNom()) , "Reservationmaison" , 20 , TextField.ANY);
                TextField contenu2 = new TextField(String.valueOf(v.getPrenom()) , "Reservationmaison" , 20 , TextField.ANY);

        
      
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       btnModifier.addPointerPressedListener(l ->   { 
           
      v.setNom(contenu.getText());
       v.setPrenom(contenu2.getText());
       
       
       if(ServiceReservationmaison.getInstance().modifierReservationmaison(v)) { 
           new ListReservationForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReservationForm(previous).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, contenu, contenu2,
              
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}


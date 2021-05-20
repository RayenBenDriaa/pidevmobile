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
import com.artefact.myapp.entities.Maisondhote;
import com.artefact.myapp.services.ServiceMaisondhote;

/**
 *
 * @author De
 */
public class ModifierMaisonForm extends Form {
       Form current;
    public ModifierMaisonForm(Form previous,Maisondhote v) {
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setTitle("Modifiez votre maison");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(false);
        
        
        

               TextField contenu = new TextField(String.valueOf(v.getNomMaison()) , "Maisondhote" , 20 , TextField.ANY);
 
        
      
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       btnModifier.addPointerPressedListener(l ->   { 
           
      v.setNomMaison(contenu.getText());
       
       
       if(ServiceMaisondhote.getInstance().modifierMaisondhote(v)) { 
           new ListMaisonForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListMaisonForm(previous).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, contenu,
              
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}


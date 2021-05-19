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
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.services.ServiceVoiture;

/**
 *
 * @author Nour
 */
public class ModifierVoitureForm extends Form {
       Form current;
    public ModifierVoitureForm(Form previous,Voiture v) {
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setTitle("Modifiez votre voiture");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(false);
        
        
        

               TextField contenu = new TextField(String.valueOf(v.getMatricule()) , "Matricule" , 20 , TextField.ANY);
                TextField contenu2 = new TextField(String.valueOf(v.getModele()) , "Modele" , 20 , TextField.ANY);
                TextField contenu3 = new TextField(String.valueOf(v.getMarque()) , "Marque" , 20 , TextField.ANY);
                TextField contenu4 = new TextField(String.valueOf(v.getDescription()) , "Description" , 20 , TextField.ANY);
                TextField contenu5 = new TextField(String.valueOf(v.getBoite_ma()) , "Boite_ma" , 20 , TextField.ANY);
               TextField contenu6 = new TextField(String.valueOf(v.getVille()) , "Ville" , 20 , TextField.ANY);
               TextField contenu7 = new TextField(String.valueOf(v.getPrix()) , "Prix" , 20 , TextField.ANY);


      
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       btnModifier.addPointerPressedListener(l ->   { 
           
      v.setMatricule(contenu.getText());
      v.setModele(contenu2.getText());
      v.setMarque(contenu3.getText());
      v.setDescription(contenu4.getText());
      v.setBoite_ma(contenu5.getText());
      v.setVille(contenu6.getText());
      //v.setPrix((int)contenu7.getText());

       
       if(ServiceVoiture.getInstance().modifierVoiture(v)) { 
           new ListVoitureForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListVoitureForm(previous).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, contenu,contenu2,contenu3,contenu4,contenu5,contenu6,
              
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}


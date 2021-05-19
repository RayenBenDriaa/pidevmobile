/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Locationv;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.services.ServiceLocationv;
import com.mycompany.myapp.services.ServiceVoiture;
import java.net.URISyntaxException;

/**
 *
 * @author Nour
 */
public class AddLocationvForm extends Form {
    public AddLocationvForm(Form previous){
        setTitle("Ajouter une location");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom");
        TextField tfPrenom= new TextField("", "Prenom");
        TextField tfNumtel = new TextField("","Numero de telephone");
//     
        Picker startat=new Picker();
        startat.setType(Display.PICKER_TYPE_DATE);
        Picker endat=new Picker(); 
        endat.setType(Display.PICKER_TYPE_DATE);
        
        java.util.Date date1=startat.getDate();

        java.util.Date date2=endat.getDate(); 
        add(startat);
        add(endat);

        Button btnValider = new Button("Louer");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Locationv v = new Locationv();
                        v.setNom(tfNom.getText());
                        v.setPrenom(tfPrenom.getText());
                        v.setNumerodetelephone(Integer.parseInt(tfNumtel.getText()));
 
                        v.setStartat(startat.getText());
                        v.setEndat(endat.getText());
                      
                      
                        if( new ServiceLocationv().addLocation(v))
                        {  Dialog.show("Success","Connection accepted",new Command("OK"));
                            Sms s=new Sms();
                            s.send(tfNumtel.getText());
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } 
                    catch (URISyntaxException ex) {
                        ex.getMessage();                    }                    
                }                
            }
        });
        addAll(tfNom,tfPrenom,tfNumtel,btnValider);
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
        
        
    }
    
    
}


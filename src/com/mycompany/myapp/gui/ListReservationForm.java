/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artefact.myapp.gui;

import com.artefact.myapp.entities.Reservationmaison;
import com.artefact.myapp.services.ServiceReservationmaison;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ListReservationForm extends Form {
     public ListReservationForm(Form previous) {
        setTitle("List Comments");
        

                
              
        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceNews.getInstance().getAllNews().toString());
//        add(sp);
            ArrayList<Reservationmaison> listComments= ServiceReservationmaison.getInstance(). getAllReservationmaisons();
            for ( Reservationmaison v: listComments){
             
                    

                
           String urlImage = "back-logo.jpeg";
                Image placeHolder =Image.createImage(120,90);
                EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
                URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
                  int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(15f);
        Button Image = new Button(urlim.fill(width,height));
        Image.setUIID("Label");
        
        
        Container cnt=BorderLayout.west(Image);
            TextArea ta = new TextArea(v.getNom());
            TextArea ta4 = new TextArea(v.getPrenom());
            TextArea num = new TextArea(String.valueOf(v.getNumerodetele()));

            


            ta.setEditable(false);
  
                 
        Label Supprimer = new Label(" ");
        Supprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0x000000);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
        Supprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");

            if(dig.show("Suppression","Vous voulez supprimer ce commentaire ?","Annuler","Oui")) {
                dig.dispose();

            }
            else {
                dig.dispose();
                 }
                if(ServiceReservationmaison.getInstance().deleteReservation(v.getId())) {
                   new ListReservationForm(previous).show();                    
                }
           
        });
        Label Modifier = new Label(" ");
        Modifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(Modifier.getUnselectedStyle());
        modifierStyle.setFgColor(0x000000);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        Modifier.setIcon(mFontImage);
        Modifier.setTextPosition(LEFT);
        
        
        Modifier.addPointerPressedListener(l -> {
              
            new ModifierReservationForm(previous,v).show();
        });
        
        
        
                
//cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(ta,ta4,num),
//         BoxLayout.encloseX(Supprimer),
//         BoxLayout.encloseX(Modifier)
//         ));
//add(cnt); 

cnt.add(BorderLayout.WEST, BoxLayout.encloseY(ta,ta4,num,Modifier,Supprimer));
            add(cnt); 
            
            }
                 Toolbar.setGlobalToolbar(true);
            Style s = UIManager.getInstance().getComponentStyle("Title");

            TextField searchField = new TextField("", "Rechercher.."); 
            searchField.getHintLabel().setUIID("Title");
            searchField.setUIID("Title");
            searchField.getAllStyles().setAlignment(Component.LEFT);
            getToolbar().setTitleComponent(searchField);
            FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH,
             s);
            searchField.addDataChangeListener((i1, i2) -> { 
             String t = searchField.getText();
             if(t.length() < 1) {
             for(Component cmp : getContentPane()) {
             cmp.setHidden(false);
             cmp.setVisible(true);
             }
             } else {
             t = t.toLowerCase();
             for(Component cmp : getContentPane()) {
                  System.out.println(((Container) cmp).getComponentCount());
             String val = null;
              val = ((TextArea) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
            System.out.println( val );
             boolean show = val != null && val.toLowerCase().indexOf(t) >
             -1;
             cmp.setHidden(!show); 
             cmp.setVisible(show);
             }
             }
             getContentPane().animateLayout(250);
            });
            getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
             searchField.startEditingAsync(); 
            });
            
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     }
     
     
     

    
}

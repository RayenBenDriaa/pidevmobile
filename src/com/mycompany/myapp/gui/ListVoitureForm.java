/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.services.ServiceVoiture;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Nour
 */
public class ListVoitureForm extends Form {
    private Resources theme;

     public ListVoitureForm(Form previous) {
         
        setTitle("List Voitures");
      getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_STACKED_LINE_CHART, e->new Stat(theme).show());
                         Button screen = new Button("Screen");
                add(screen);
                screen.addActionListener(e -> {

                    Form form = Display.getInstance().getCurrent();
                    if (form != null) {

                        Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
                        form.revalidate();
                        form.setVisible(true);
                        form.paintComponent(screenshot.getGraphics(), true);
                        
                        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
                        System.out.println(imageFile);
                        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                        } catch (IOException err) {
                            Log.e(err);
                        }
                    }

                });
                
              
        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceNews.getInstance().getAllNews().toString());
//        add(sp);
            ArrayList<Voiture> listComments= ServiceVoiture.getInstance().getAllVoitures();
            for ( Voiture v: listComments){
             
                    

                
              int deviceWidth = Display.getInstance().getDisplayWidth(); 
        Image placeholder = Image.createImage(120,90); 
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false); 
        
        String urlImage = "http://127.0.0.1:8000/uploads/images/"+v.getImage();
        URLImage imgUrl = URLImage.createToStorage(encImage, "Large_" + urlImage,urlImage,URLImage.RESIZE_SCALE);
System.out.println(urlImage);
        ImageViewer img = new ImageViewer(imgUrl);
                  int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(15f);
                 Button Image = new Button(imgUrl.fill(width,height));
        Container cnt=BorderLayout.west(Image);
        
            TextArea ta = new TextArea(v.getMatricule());
            TextArea modele = new TextArea(v.getModele());
            TextArea marque = new TextArea(v.getMarque());
            TextArea prix = new TextArea (String.valueOf(v.getPrix()));          
            TextArea descri = new TextArea(v.getDescription());
            TextArea boite = new TextArea(v.getBoite_ma());
            TextArea ville = new TextArea(v.getVille());

  
                 
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
                if(ServiceVoiture.getInstance(). deletevoiture(v.getId())) {
                   new ListVoitureForm(previous).show();                    
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
              
            new ModifierVoitureForm(previous,v).show();
        });
        
        
        
                
cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(ta,marque,modele,descri,boite,ville,prix),
         BoxLayout.encloseX(Supprimer),
         BoxLayout.encloseX(Modifier)
         ));
add(cnt); 
            }
            
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         // getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_STACKED_LINE_CHART, e->new Stat(theme).show());

     }

    
}

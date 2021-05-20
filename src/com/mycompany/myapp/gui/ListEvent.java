/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;


import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author wale
 */
public class ListEvent extends Form{
        Form current;

        public ListEvent(Evenement u) {
        setTitle("Liste des Evenements");
        
          Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Evenement> event = new ArrayList();
                        ServiceEvenement sa =new ServiceEvenement();
                    event=sa.getAllEvents();
                             for (Evenement fi : event) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("ID : "+String.valueOf(fi.getId_event()+" Nom : "+fi.getNom_event()));
                            m.setTextLine2("Description : "+fi.getDescription());
                            Date datecreation = new Date(System.currentTimeMillis());
                            String date= (String) fi.getStart_at().toString();
                            String datee= (String) fi.getEnd_at().toString();
                            m.setTextLine3("Le: "+date+" Jusqu'a :"+datee);
                            String url = "file://C:/wamp64/www/pi-dev/public/uploads/images/"+ fi.getImage();
                                 System.out.println(url);
                            int deviceWidth = Display.getInstance().getDisplayWidth();
                            Image placeholder = Image.createImage( deviceWidth/3,  deviceWidth/3, 0xbfc9d2);
                            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + fi.getId_event(),url, URLImage.RESIZE_SCALE);
                            System.out.println(i);
                            m.setIcon(i);
                            m.setTextLine4("Ville : "+fi.getVille());
                            m.addPointerReleasedListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer ?", "Supprimer", "Annuler")) {
                    Evenement t = new Evenement();
                    t.setId_event(fi.getId_event());
                        if( ServiceEvenement.getInstance().deleteEvent(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ListEvent(u).show();
                            }
                   
                }
            }    
            }
        });
            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier ?", "Modifier ", "Annuler")) {
                   new EditEvent(current, fi,u).show();
                }    
            }
        });

                            add(m);
                             }
                     revalidate();
                    });
                });
    getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);
        //Tool Bar
        
        getToolbar().addCommandToSideMenu("Gestions des Evenements", null, e -> new MenuEvent(u).show());
        
    }

    
}

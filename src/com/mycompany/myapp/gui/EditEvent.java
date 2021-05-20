/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import static com.codename1.components.FloatingActionButton.createFAB;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Evenement;

import com.mycompany.myapp.services.ServiceEvenement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class EditEvent extends Form{
    
   String filePath;
   Form detailsForm;
   String Imagecode;
    ServiceEvenement sa= new ServiceEvenement();
    public EditEvent(Form previous,Evenement v,Evenement u) {
        setTitle("Modifier Evenement");
        setLayout(BoxLayout.y());
        TextComponent tfnom= new TextComponent().label("Nom");
        tfnom.text(v.getNom_event());
        TextComponent tfddd= new TextComponent().label("Date Debut");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(v.getStart_at());
        TextComponent tffff= new TextComponent().label("Date Fin");
        Picker datePickerr = new Picker();
        datePickerr.setType(Display.PICKER_TYPE_DATE);
        datePickerr.setDate(v.getEnd_at());
        TextComponent tfville= new TextComponent().label("Ville");
        tfville.text(v.getVille());
        
        TextComponent tfdescription= new TextComponent().label("Description");
        tfdescription.text(v.getDescription());
        TextComponent tfimg= new TextComponent().label("Image");

        Container c=new Container(new FlowLayout(Container.CENTER));
        //IMAGE
        Font materialFont = FontImage.getMaterialDesignFont();
        FontImage fntImage = FontImage.createFixed("\uE871", materialFont, 0xffffff, 100, 100);
        Button b2 = new Button(fntImage);
        Style fabStyle = b2.getAllStyles();
        fabStyle.setBorder(RoundBorder.create().color(0xf05f5f).shadowOpacity(100));
        fabStyle.setFgColor(0xf15f5f);
        fabStyle.setBgTransparency(50);
        fabStyle.setBgColor(0xf05f5f);
        
       

        Button btnValider = new Button("Modifier Evenement");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfnom.getText().equals("")||(tfdescription.getText().equals("")||(tfville.getText().equals(""))))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
            ImageIO imgIO = ImageIO.getImageIO();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        System.out.println(datePicker.getDate());
                        System.out.println(filePath);
                        byte[] ba = out.toByteArray();
                        Imagecode = Base64.encode(ba);
                           Evenement t = new Evenement(v.getId_event(),tfnom.getText(), datePicker.getDate(), datePickerr.getDate(),tfville.getText(), tfdescription.getText(), "/"+filePath,0); //Double.parseDouble(tfrating.getText())

                           if( ServiceEvenement.getInstance().editEvent(t)){
                               {
                                   Dialog.show("Success","Connection accepted",new Command("OK"));
                                  
                               }
  
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        addAll(tfnom,tfddd,datePicker,tffff,datePickerr,tfville,tfdescription,b2,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListEvent(u).show());
                
    }
     
}

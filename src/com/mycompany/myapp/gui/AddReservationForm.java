/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artefact.myapp.gui;

import com.artefact.myapp.entities.Reservationmaison;
import com.artefact.myapp.services.ServiceReservationmaison;
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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Dell
 */
    public class AddReservationForm extends Form {
    public AddReservationForm(Form previous){
        setTitle("Ajoutez une nouvelle reservation");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","nom");
        TextField tfPrenom= new TextField("", "prenom");
        TextField tfNum= new TextField("", "numerodetele");
        TextField tfEmail= new TextField("", "mail adress");
        
         Picker startat=new Picker();
        startat.setType(Display.PICKER_TYPE_DATE);
        Picker endat=new Picker(); 
        endat.setType(Display.PICKER_TYPE_DATE);
        
        java.util.Date date1=startat.getDate();

        java.util.Date date2=endat.getDate(); 
        add(startat);
        add(endat);

        Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0||(tfEmail.getText().length()==0)))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Reservationmaison v = new Reservationmaison();
                        v.setNom(tfNom.getText());
                        v.setPrenom(tfPrenom.getText());
                        v.setNumerodetele(Integer.parseInt(tfNum.getText()));
                        v.setEmail(tfEmail.getText());
                        v.setStartat(startat.getText());
                        v.setEndat(endat.getText());
                      

                        if( new ServiceReservationmaison().addReservationmaison(v))
                        {Dialog.show("Success","Connection accepted",new Command("OK"));

send("artefact.corp2021@gmail.com", "javaFX123456", tfEmail.getText(), "AUTOMATIC MESSAGE from ARTEFACT DO NOT REPLY ","Thank you mr " +tfNom.getText() +" for choosing Artefact, "+"Your reservation has been successfully done !");
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }                    
                }                
            }
        });
        addAll(tfNom,tfPrenom,tfNum,tfEmail,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
        

    }
    public void send(String from, String password, String to, String sub, String msg) {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    
    
}
    


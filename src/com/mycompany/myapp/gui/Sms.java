/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URISyntaxException;

/**
 *
 * @author Nour
 */
public class Sms {
      public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

    public static void send(String num) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message;
        message = Message.creator(new PhoneNumber("+216"+num), // to
                        new PhoneNumber("+16672432429"), // from
                        "Inscription effectuée avec succès  ")
                .create();
        System.out.println(message.getSid());
        System.out.println("notif");
    }
    
}

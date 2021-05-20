/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;


import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author SIHEM
 */
public class ServiceEvenement {

    public ArrayList<Evenement> events;
    
    public static ServiceEvenement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServiceEvenement() {
         req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
    


    public boolean addEvent(Evenement t) {
        String url = Statics.BASE_URL + "/Evenement/add?nom="+t.getNom_event()+ "&startat="+t.getStart_at()+"&endat=" + t.getEnd_at()+ "&ville="+ t.getVille()+ "&description=" + t.getDescription()+ "&image="+t.getImage()+ "&rating="+t.getRating(); //création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean editEvent(Evenement t) {
        String url = Statics.BASE_URL + "/Evenement/edit?id="+t.getId_event()+"&nom="+t.getNom_event()+ "&startat="+t.getStart_at()+"&endat=" + t.getEnd_at()+ "&ville="+ t.getVille()+ "&description=" + t.getDescription()+ "&image="+t.getImage()+ "&rating="+t.getRating(); //création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean deleteEvent(Evenement t) {
        String url = Statics.BASE_URL + "/Event/del?id=" + t.getId_event();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Evenement> parseVelo(String jsonText){
                try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement a = new Evenement();
                float id = Float.parseFloat(obj.get("idEvent").toString());
                a.setId_event((int)id);
                a.setNom_event(obj.get("nomEvent").toString());
                a.setDescription(obj.get("description").toString());
                a.setVille(obj.get("ville").toString());
                a.setImage(obj.get("image").toString());
                float rating = Float.parseFloat(obj.get("rating").toString());
                a.setRating(rating);
                String start = obj.get("startAt").toString();
                String end = obj.get("endAt").toString();
                start =start.substring(0,10);
                end =end.substring(0,10);
                try {  
                    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(start);
                                    a.setStart_at( date1);
                                    System.out.println(a.getStart_at());
                    Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(end);
                                    a.setEnd_at(date2);
                                    System.out.println(a.getEnd_at());

                } catch (ParseException ex) {
                }

                
                       
                events.add(a);
                           //     t.setDateabsence((Date) obj.get("dateabsence"));

            }
            
            
        } catch (IOException ex) {
            
        }
        return events;
    }
              
                    

    
    public ArrayList<Evenement> getAllEvents(){
        String url = Statics.BASE_URL+"/affmobEvent";
                System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseVelo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Evenement> getEvent(int id){
        String url = Statics.BASE_URL+"location/velo/findVeloMob/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseVelo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }    
    
        public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }

        
}

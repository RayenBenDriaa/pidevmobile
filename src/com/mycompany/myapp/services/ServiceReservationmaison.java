/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artefact.myapp.services;

import com.artefact.myapp.entities.Reservationmaison;
import com.artefact.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class ServiceReservationmaison {
    
    
     public ArrayList<Reservationmaison> Reservationmaison;
   public boolean resultOK;
    public static ServiceReservationmaison instance=null;
    private ConnectionRequest req;

    public ServiceReservationmaison() {
         req = new ConnectionRequest();
    }

    public static ServiceReservationmaison getInstance() {
        if (instance == null) {
            instance = new ServiceReservationmaison();
        }
        return instance;
    }
        public boolean addReservationmaison(Reservationmaison v) {
        String url = Statics.BASE_URL + "/addReservation/newJSON?nom=" + v.getNom()+ "&prenom=" + v.getPrenom()+ "&numerodetele=" + v.getNumerodetele()+ "&startat=" + v.getStartat()+ "&endat=" + v.getEndat(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 
     

           
      
          public ArrayList<Reservationmaison> parseReservationmaison(String jsonText){
   try {
            Reservationmaison=new ArrayList<>();
            
               JSONParser jsonp;
               jsonp= new JSONParser();

          
             Map<String,Object> mapReservationmaisons = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
           
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapReservationmaisons.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj :  listOfMaps){
                //Création des tâches et récupération de leurs données
                
                 Reservationmaison re = new Reservationmaison();
//                
               float id = Float.parseFloat(obj.get("id").toString());               
               String nom = obj.get("nom").toString();
               String prenom = obj.get("prenom").toString();
               float numerodetele = Float.parseFloat(obj.get("numerodetele").toString());
               

               re.setId((int)id);
               re.setNom(nom);
               re.setPrenom(prenom);
               re.setNumerodetele((int)numerodetele);
               

                                 Reservationmaison.add(re);

                               
            }
            
            
        } catch (IOException ex) {
             System.out.println(ex.getMessage()); 
            
        }
        
        return Reservationmaison;
    }
       public ArrayList<Reservationmaison> getAllReservationmaisons(){
        String url = Statics.BASE_URL+"/apiReservationmaison";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reservationmaison = parseReservationmaison(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reservationmaison;
    }
        
  //Delete
       public boolean deleteReservation(int id){
           String url= Statics.BASE_URL+"/DeleteReservationmaisonJSON/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                                req.removeResponseListener(this);

                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
           
           
       }
         //Update 
    public boolean modifierReservationmaison(Reservationmaison v) {
        String url = Statics.BASE_URL +"/UpdateReservationmaisonJSON/"+v.getId()+"?nom="+v.getNom()+ "&prenom=" + v.getPrenom();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
        
    }
    
}

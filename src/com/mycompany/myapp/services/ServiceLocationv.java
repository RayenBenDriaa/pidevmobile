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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Locationv;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nour
 */
public class ServiceLocationv {
    public ArrayList<Locationv> Locationv;
   public boolean resultOK;
    public static ServiceLocationv instance=null;
    private ConnectionRequest req;

    public ServiceLocationv() {
         req = new ConnectionRequest();
    }

    public static ServiceLocationv getInstance() {
        if (instance == null) {
            instance = new ServiceLocationv();
        }
        return instance;
    }
        public boolean addLocation(Locationv n) {
        String url = Statics.BASE_URL + "/addLocation/newLOC?nom=" + n.getNom()+ "&prenom=" + n.getPrenom()+ "&numerodetelephone=" + n.getNumerodetelephone() + "&heuredebut=" + n.getHeuredebut()+ "&heurefin=" + n.getHeurefin()+  "&startat=" + n.getStartat()+ "&endat=" + n.getEndat()+ "&permis=" + n.getPermis();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
                System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public ArrayList<Locationv> parseNews(String jsonText){
   try {
            Locationv =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Locationv t = new Locationv();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setNumerodetelephone(((int)Float.parseFloat(obj.get("numerodetelephone").toString())));
//   //date debut 
//                 String DateConverter =  obj.get("startat").toString().substring(obj.get("startat").toString().indexOf("timestamp") + 10 , obj.get("startat").toString().lastIndexOf("}"));
//                        
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                        t.setStartat(dateString);
//                        
//                      //date fin   
//                        String DateConverter1 =  obj.get("endat").toString().substring(obj.get("endat").toString().indexOf("timestamp") + 10 , obj.get("endat").toString().lastIndexOf("}"));
//                        
//                        Date currentTime1 = new Date(Double.valueOf(DateConverter1).longValue() * 1000);
//                        
//                        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString1 = formatter1.format(currentTime1);
//                        t.setEndat(dateString1);
////                
                //Ajouter la tâche extraite de la réponse Json à la liste
                Locationv.add(t);
            }
            
            
        } catch (IOException ex) {
             System.out.println(ex.getMessage()); 
            
        }
       
        return Locationv;
    }
       public ArrayList<Locationv> getLocations(){
        String url = Statics.BASE_URL+"/apilocations";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Locationv = parseNews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Locationv;
    }
       
       //Delete
       public boolean deletelocation(int id){
           String url= Statics.BASE_URL+"/DeleteLocationJSON/"+id;
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
    public boolean modifierLocation(Locationv n) {
        String url = Statics.BASE_URL +"/UpdateLocationJSON/"+n.getId()+"?nom="+n.getNom()+ "&prenom=" + n.getPrenom()+ "&numerodetelephone=" + n.getNumerodetelephone();
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

    


    
    
    


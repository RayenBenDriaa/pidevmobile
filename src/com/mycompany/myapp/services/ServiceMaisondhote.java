/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artefact.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.artefact.myapp.entities.Maisondhote;
import com.artefact.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Dell
 */
public class ServiceMaisondhote {
      public ArrayList<Maisondhote> Maisondhote;
   public boolean resultOK;
    public static ServiceMaisondhote instance=null;
    private ConnectionRequest req;

    public ServiceMaisondhote() {
         req = new ConnectionRequest();
    }

    public static ServiceMaisondhote getInstance() {
        if (instance == null) {
            instance = new ServiceMaisondhote();
        }
        return instance;
    }
        public boolean addMaisondhote(Maisondhote v) {
        String url = Statics.BASE_URL + "/addMaison/newJSON?nommaison=" + v.getNomMaison()+ "&ville=" + v.getVille()+ "&address=" + v.getAddress()+ "&nombrechambres=" + v.getNombrechambres()+ "&prix=" + v.getPrix()+ "&description=" + v.getDescription()+ "&image=" + v.getImage()+ "&disponible=" + v.isDisponible(); //création de l'URL
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
 
     

           
      
          public ArrayList<Maisondhote> parseMaisondhote(String jsonText){
   try {
            Maisondhote=new ArrayList<>();
            
               JSONParser jsonp;
               jsonp= new JSONParser();

          
             Map<String,Object> mapMaisondhotes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
           
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapMaisondhotes.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj :  listOfMaps){
                //Création des tâches et récupération de leurs données
                
                 Maisondhote re = new Maisondhote();
//                
               float id = Float.parseFloat(obj.get("id").toString());               
               String nommaison = obj.get("nommaison").toString();
               String ville = obj.get("ville").toString();
               String address = obj.get("address").toString();
               float nombrechambres = Float.parseFloat(obj.get("nombrechambres").toString());
               float prix = Float.parseFloat(obj.get("prix").toString());
               String description = obj.get("description").toString();
                               String Image = obj.get("image").toString();

               re.setId((int)id);
               re.setNomMaison(nommaison);
               re.setVille(ville);
               re.setAddress(address);
               re.setPrix((int)prix);
               re.setDescription(description);
                 re.setImage(Image);
                                 Maisondhote.add(re);

                               
            }
            
            
        } catch (IOException ex) {
             System.out.println(ex.getMessage()); 
            
        }
        
        return Maisondhote;
    }
       public ArrayList<Maisondhote> getAllMaisondhotes(){
        String url = Statics.BASE_URL+"/apiMaisondhote";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Maisondhote = parseMaisondhote(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Maisondhote;
    }
        
  //Delete
       public boolean deleteMaison(int id){
           String url= Statics.BASE_URL+"/DeleteMaisondhoteJSON/"+id;
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
    public boolean modifierMaisondhote(Maisondhote v) {
        String url = Statics.BASE_URL +"/UpdateMaisondhoteJSON/"+v.getId()+"?nommaison="+v.getNomMaison();
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

    


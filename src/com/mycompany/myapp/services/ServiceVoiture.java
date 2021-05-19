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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Nour
 */
public class ServiceVoiture {
      public ArrayList<Voiture> Voiture;
   public boolean resultOK;
    public static ServiceVoiture instance=null;
    private ConnectionRequest req;

    public ServiceVoiture() {
         req = new ConnectionRequest();
    }

    public static ServiceVoiture getInstance() {
        if (instance == null) {
            instance = new ServiceVoiture();
        }
        return instance;
    }
        public boolean addVoiture(Voiture v) {
        String url = Statics.BASE_URL + "/addVoiture/newJSON?matricule=" + v.getMatricule()+ "&modele=" + v.getModele()+ "&marque=" + v.getMarque()+ "&prix=" + v.getPrix()+ "&description=" + v.getDescription()+ "&boite_ma=" + v.getBoite_ma()+ "&ville=" + v.getVille()+ "&image=" + v.getImage()+ "&disponible=" + v.isDisponible(); //création de l'URL
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
 
     

           
      
          public ArrayList<Voiture> parseVoiture(String jsonText){
   try {
            Voiture=new ArrayList<>();
            
               JSONParser jsonp;
               jsonp= new JSONParser();

          
             Map<String,Object> mapVoitures = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
           
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapVoitures.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj :  listOfMaps){
                //Création des tâches et récupération de leurs données
                
                 Voiture re = new Voiture();
//                
               float id = Float.parseFloat(obj.get("id").toString());               
               String matricule = obj.get("matricule").toString();
               String modele = obj.get("modele").toString();
               String marque = obj.get("marque").toString();
               float prix = Float.parseFloat(obj.get("prix").toString());
               String description = obj.get("description").toString();
               String Boite_ma = obj.get("boite_ma").toString();
               String Ville = obj.get("ville").toString();
                               String Image = obj.get("image").toString();

               re.setId((int)id);
               re.setMatricule(matricule);
               re.setModele(modele);
               re.setMarque(marque);
               re.setPrix((int)prix);
               re.setDescription(description);
               re.setBoite_ma(Boite_ma);
               re.setVille(Ville);
                 re.setImage(Image);
                                 Voiture.add(re);

                               
            }
            
            
        } catch (IOException ex) {
             System.out.println(ex.getMessage()); 
            
        }
        
        return Voiture;
    }
       public ArrayList<Voiture> getAllVoitures(){
        String url = Statics.BASE_URL+"/apivoitures";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Voiture = parseVoiture(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Voiture;
    }
        
  //Delete
       public boolean deletevoiture(int id){
           String url= Statics.BASE_URL+"/DeleteVoitureJSON/"+id;
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
    public boolean modifierVoiture(Voiture v) {
        String url = Statics.BASE_URL +"/UpdateVoitureJSON/"+v.getId()+"?matricule="+v.getMatricule()+ "&modele=" + v.getModele()+ "&marque=" + v.getMarque()+ "&prix=" + v.getPrix()+ "&description=" + v.getDescription()+ "&boite_ma=" + v.getBoite_ma()+ "&ville=" + v.getVille();
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

    


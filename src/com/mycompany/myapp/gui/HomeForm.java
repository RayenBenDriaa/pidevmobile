/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.MenuEvent;
import com.mycompany.myapp.entities.Evenement;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        Evenement u = new Evenement();

        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add user");
        Button btnListTasks = new Button("List user");
        Button btnRemoveUser = new Button ("Delete User");
        Button btnUpdateUser = new Button("Update User");
        Button btnAddrecla = new Button("Add reclamation");
        Button btnlistrecla = new Button("List reclamation");
        Button btnremoverecla = new Button("Delete reclamation");
        Button btnupdaterecla = new Button("Update reclamation");
        Button btnAddVoiture = new Button("Add Voiture");
        Button btnListVoiture = new Button("List Voiture");
        Button btnAddLocationv = new Button("Add Location");
        Button btnListLocationv = new Button("List Location");
        Button btnAide = new Button("Gestions des Evenements");
         Button btnAddMaisondhote = new Button("Add Maisondhote");
        Button btnListMaisondhote = new Button("List Maisondhote");
        
        Button btnReservation = new Button("Add Reservation");
        Button btnListreservation = new Button("List Reservation");
        
       btnListMaisondhote.addActionListener(e-> new ListMaisonForm(current).show());
        btnAddMaisondhote.addActionListener(e-> new AddMaisonForm(current).show());
       
       btnListreservation.addActionListener(e-> new ListReservationForm(current).show());
      btnReservation.addActionListener(e-> new AddReservationForm(current).show());
      
        
        btnListVoiture.addActionListener(e-> new ListVoitureForm(current).show());
        btnAddVoiture.addActionListener(e-> new AddVoitureForm(current).show());
        
        btnAddLocationv.addActionListener(e-> new AddLocationvForm(current).show());
        btnListLocationv.addActionListener(e-> new ListLocationvForm(current).show());
        

        btnAddTask.addActionListener(e -> new AddTaskForm(current).show());
        btnListTasks.addActionListener(e -> new ListTasksForm(current).show());
        btnRemoveUser.addActionListener(e-> new RemoveUserForm(current).show());
        btnUpdateUser.addActionListener(e-> new UpdateUserForm(current).show());
        btnAddrecla.addActionListener(e-> new AddReclaForm(current).show());
        btnlistrecla.addActionListener(e-> new ListReclaForm(current).show());
        btnAide.addActionListener(e-> new MenuEvent(u).show());
        
        addAll(btnAddTask, btnListTasks,btnRemoveUser,btnUpdateUser,btnAddrecla,btnAddVoiture,btnListVoiture,btnAddLocationv,btnAide,btnListLocationv,btnReservation,btnListreservation,btnAddMaisondhote,btnListMaisondhote);

    }

}

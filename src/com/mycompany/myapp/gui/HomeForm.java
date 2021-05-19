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
        

        btnAddTask.addActionListener(e -> new AddTaskForm(current).show());
        btnListTasks.addActionListener(e -> new ListTasksForm(current).show());
        btnRemoveUser.addActionListener(e-> new RemoveUserForm(current).show());
        btnUpdateUser.addActionListener(e-> new UpdateUserForm(current).show());
        btnlistrecla.addActionListener(e-> new ListReclaForm(current).show());
        addAll(btnAddTask, btnListTasks,btnRemoveUser,btnUpdateUser,btnAddrecla,btnlistrecla,btnremoverecla,btnupdaterecla);

    }

}

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
import com.mycompany.myapp.entities.Evenement;



/**
 *
 * @author wajih
 */
public class MenuEvent extends Form{
    Form current;

    public MenuEvent(Evenement u) {
                current=this;
        setTitle("Gestions des Evenements");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutEvent = new Button("Ajouter Evenement");
        Button btnListEvents = new Button("Liste des Evenements");
        Button btnStat= new Button("Stat");
        
        btnAjoutEvent.addActionListener(e-> new AddEvent(current,u).show());
        btnListEvents.addActionListener(e-> new ListEvent(u).show());
        
        //Tool Bar
        
        getToolbar().addCommandToSideMenu("Gestions des Evenements", null, e -> new MenuEvent(u).show());
        

        addAll(btnAjoutEvent,btnListEvents,btnStat);

    }

}


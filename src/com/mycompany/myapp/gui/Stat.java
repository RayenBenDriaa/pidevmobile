/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.services.ServiceVoiture;

import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Nada
 */
public class Stat extends Form{
  
    Form home;
    Form home0;
    Form f, f2;
    SpanLabel lb;
    Button Vente,acceuill;
    Button Adoption;
    Button LostAndFound;
    Button HorsLigne;

    private Form current;
    private Resources theme;
    
    public Resources res;
  
  
    private EncodedImage enc;
   protected CategorySeries buildCategoryDataset(String title, double[] values) {
            CategorySeries series = new CategorySeries(title);
            
            String[] strings = new String[]{"fiat%","aa%", "hh%",};

            for (int i = 0 ; i != values.length ; i++) {
                series.add(strings[i], values[i]);
            }

            return series;

    }


    private DefaultRenderer buildCategoryRenderer(int[] colors) {
            DefaultRenderer renderer = new DefaultRenderer();
            renderer.setLabelsTextSize(40);
            renderer.setShowLegend(false);
            renderer.setBackgroundColor(BLACK);

            // doesn't influence the space around the graph
            renderer.setMargins(new int[]{20, 20, 120, 20});
            for (int color : colors) {
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(color);
                renderer.addSeriesRenderer(r);

            }
            return renderer;
     }
   
  public Stat(Resources res) {
      
      
     
      
    super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
//        getContentPane().setScrollVisible(false);
         Form previous = Display.getInstance().getCurrent();
//        tb.setBackCommand("", e -> previous.showBack());
//        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        

  
            int porcFal =6*100/ 1;
int Hand=0;
int aa=0;
int hh=0;


         ServiceVoiture sc = new ServiceVoiture();
        ArrayList<Voiture> list = sc.getAllVoitures();
          for (Voiture a : list) { {
          
          if(a.getModele().equals("fiat"))
          {
              Hand++;
          }
          if(a.getModele().equals("aa"))
          {
              aa++;
          }
          if(a.getModele().equals("hh"))
          {
              hh++;
          }
           
                
          }
                    }

            double[] values = new double[]{Hand,aa,hh};
            int[] colors = new int[]{ColorUtil.CYAN, 0xEA9999,ColorUtil.LTGRAY,ColorUtil.YELLOW};

            DefaultRenderer renderer = buildCategoryRenderer(colors);
            renderer.setDisplayValues(true);
            renderer.setShowLabels(true);

            // doesn't influence the space around the graph
            renderer.setMargins(new int [] {0,0,0,0});

            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
            r.setHighlighted(true);

            PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
            ChartComponent c = new ChartComponent(chart);
               addStringValue("Modele", FlowLayout.encloseRightMiddle(c));
          
//                    Button btnAnnuler = new Button("Retour");
//       btnAnnuler.addActionListener(e -> {
//           new AjoutAbonnementForm(res).show();
//       }); 
      // Container content = BoxLayout.encloseY( btnAnnuler );
//      add(content);
//        show();
             
               
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new ListVoitureForm(previous).show());
    }
   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
//        add(createLineSeparator(0xeeeeee));
    }
    public Form getHome() {
        return home;
    }

    public void setHome(Form home) {
        this.home = home;
    }

    

    
}
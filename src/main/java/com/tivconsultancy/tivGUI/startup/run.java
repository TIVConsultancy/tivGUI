/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.tivGUI.MainFrame;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.TIVScene;
import com.tivconsultancy.tivGUI.helpfunctions.Dummy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
 *
 * @author Thomas Ziegenhein
 */
public class run extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showEmptyMainFrame(primaryStage);
    }
    
    public static void showEmptyMainFrame(Stage primaryStage) throws IOException {
                        
        StaticReferences.setStandardIcons(new Dummy(), "/com/tivconsultancy/tivGUI/icons/menuicons/");
        
        List<String> icons = new ArrayList<>();
        icons.add("/com/tivconsultancy/tivGUI/icons/Icon128x128.png");
        icons.add("/com/tivconsultancy/tivGUI/icons/Icon64x64.png");
        icons.add("/com/tivconsultancy/tivGUI/icons/Icon32x32.png");
        icons.add("/com/tivconsultancy/tivGUI/icons/Icon16x16.png");
        MainFrame.setIcons(new Dummy().getClass(), icons);
        
        StaticReferences.controller = new StartUpController();        
        MainFrame tivGUI = new MainFrame();
        TIVScene scene = new TIVScene(tivGUI);
        StaticReferences.controller.setScene(scene);
        StaticReferences.controller.setGUI(tivGUI);
        JMetro jMetro = new JMetro(Style.DARK);

//        jMetro.setScene(scene);
        scene.getStylesheets().add(scene.getClass().getResource("/com/tivconsultancy/tivGUI/cssFiles/tiv.css").toExternalForm());

        
        primaryStage.getIcons().addAll(MainFrame.getIcon());
        primaryStage.setTitle("tivGUI mainFrame");
        primaryStage.setScene(scene);
        primaryStage.show();
        tivGUI.startNewSession();
    }
    
}

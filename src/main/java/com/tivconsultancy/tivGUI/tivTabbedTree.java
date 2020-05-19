/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI;

import com.tivconsultancy.tivGUI.logging.LogTextArea;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.tivGUI.trees.tivSettingsTree;
import com.tivconsultancy.tivGUI.trees.tivImageTree;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class tivTabbedTree extends TabPane {

    protected Tab tabImages;
    protected Tab tabSettings;
    protected Tab tabLog;

    protected AnchorPane anchorImageTree;
    protected AnchorPane anchorSettingsTree;
    protected AnchorPane anchorLog;

    protected ScrollPane scrollImageTree;
    protected ScrollPane scrollSettingsTree;
    protected ScrollPane scrollLog;

    tivImageTree imageTree;
    tivSettingsTree settingsTree;
    LogTextArea logArea;

    public tivTabbedTree() {

        initComponents();
        setAnchors();
        setBehaviour();
        fillComponents();
        setAnchors();

        setStyle();
        
        getTabs().add(tabImages);
        getTabs().add(tabSettings);
        getTabs().add(tabLog);
        StaticReferences.getlog().log(Level.SEVERE, "Severe tets");

    }
    
    private void setStyle(){
        getStyleClass().add("tabController");
        tabImages.getStyleClass().add("tabImages");
    }

    private void initComponents() {
        tabImages = new Tab();
        tabSettings = new Tab();
        tabLog = new Tab();

        imageTree = new tivImageTree();
        settingsTree = new tivSettingsTree();
        logArea = LogTextArea.getInstance();

        scrollImageTree = new ScrollPane();
        scrollSettingsTree = new ScrollPane();
        scrollLog = new ScrollPane();

        anchorImageTree = new AnchorPane();
        anchorSettingsTree = new AnchorPane();
        anchorLog = new AnchorPane();
    }

    private void fillComponents() {

        anchorImageTree.getChildren().add(imageTree);
        anchorSettingsTree.getChildren().add(settingsTree);
        anchorLog.getChildren().add(logArea);

        scrollImageTree.setContent(anchorImageTree);
        scrollSettingsTree.setContent(anchorSettingsTree);
        scrollLog.setContent(anchorLog);

        tabImages.setText("Images");
        tabImages.setContent(anchorImageTree);        

        tabSettings.setText("Settings");
        tabSettings.setContent(anchorSettingsTree);
        
        tabLog.setText("Log");
        tabLog.setContent(logArea);

//        scrollImageTree.setStyle("-fx-background-color: #c9c9c9");
//        tabImages.setStyle("-fx-background-color: #c9c9c9;-fx-border-color: #c9c9c9");
//        tabImages.setStyle(getClass().getResource("/com/tivconsultancy/tivGUI/cssFiles/tiv.css").toExternalForm());
        
//        List<String> ls = tabImages.getStyleClass();
//        tabImages.setContent(anchorImageTree);
//        tabSettings.setContent(anchorSettingsTree);
    }

    private void setBehaviour() {
        setTabClosingPolicy(javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    private void setAnchors() {

        AnchorPane.setBottomAnchor(imageTree, 0.0);
        AnchorPane.setLeftAnchor(imageTree, 0.0);
        AnchorPane.setRightAnchor(imageTree, 0.0);
        AnchorPane.setTopAnchor(imageTree, 0.0);
        AnchorPane.setBottomAnchor(settingsTree, 0.0);
        AnchorPane.setLeftAnchor(settingsTree, 0.0);
        AnchorPane.setRightAnchor(settingsTree, 0.0);
        AnchorPane.setTopAnchor(settingsTree, 0.0);
//        AnchorPane.setBottomAnchor(scrollSettingsTree, 0.0);
//        AnchorPane.setLeftAnchor(scrollSettingsTree, 0.0);
//        AnchorPane.setRightAnchor(scrollSettingsTree, 0.0);
//        AnchorPane.setTopAnchor(scrollSettingsTree, 0.0);
//        AnchorPane.setBottomAnchor(scrollImageTree, 0.0);
//        AnchorPane.setLeftAnchor(scrollImageTree, 0.0);
//        AnchorPane.setRightAnchor(scrollImageTree, 0.0);
//        AnchorPane.setTopAnchor(scrollImageTree, 0.0);

    }
    
    public void startNewSession(){
        imageTree.startNewSession();
        settingsTree.startNewSession();
    }

}

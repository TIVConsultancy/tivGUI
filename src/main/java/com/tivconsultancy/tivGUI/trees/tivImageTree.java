/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.trees;

import com.tivconsultancy.tivGUI.StaticReferences;
import delete.com.tivconsultancy.opentiv.devgui.main.ImagePath;
import java.io.File;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class tivImageTree extends TreeView {

    private TreeItem<Object> imageRoot = new TreeItem<>("Images");

    public tivImageTree() {
        super(new TreeItem<>("Input Files"));
        
        this.getRoot().getChildren().add(new TreeItem<>(new ImagePath("")));
        setStyle("imageTree");
        this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue == null || !(newValue instanceof TreeItemFile)){
//                    StaticReferences.controller.setSelectedFile(null);
                    return;
                }
                try {
                    StaticReferences.controller.setSelectedFile(((TreeItemFile) newValue).getFile());
                } catch (Exception e) {
                    StaticReferences.getlog().log(Level.INFO, "Cannot view or set file", e);
                }                
            }
        });
    }

    public void startNewSession() {
        this.getRoot().getChildren().clear();
        imageRoot.getChildren().clear();
        this.getRoot().getChildren().add(imageRoot);
        for (File f : StaticReferences.controller.getInputFiles(null)) {
            imageRoot.getChildren().add(new TreeItemFile(f));
        }
        if(!this.getSelectionModel().isEmpty()){
            this.getSelectionModel().select(0);        
        }
        StaticReferences.controller.getViewController(null).update();
    }

    public class TreeItemFile extends TreeItem {

        File f;

        public TreeItemFile(File f) {
            super(f.getName());
            this.f = f;
        }

        public File getFile() {
            return f;

        }
    }

}

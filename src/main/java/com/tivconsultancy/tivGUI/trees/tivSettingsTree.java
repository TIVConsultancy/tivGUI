/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.trees;

import com.tivconsultancy.opentiv.helpfunctions.settings.SettingObject;
import com.tivconsultancy.opentiv.helpfunctions.settings.SettingsCluster;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.trees.tivcallbacks.SettingsTreeFactory;
import com.tivconsultancy.tivGUI.trees.treecells.EditableTextComboBox;
import com.tivconsultancy.tivGUI.trees.treecells.contentclasses.TreeContentClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class tivSettingsTree extends TreeView {

    public tivSettingsTree() {
        super(new TreeItem<>(new TreeContentClass(null,"root0", "root0", "root0", new String())));
        setEditable(true);
        setCellFactory(new SettingsTreeFactory());

        TreeView t = this;
        this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                TreeItem<Object> selectedItem = (TreeItem<Object>) newValue;
                Set<Node> treeCells = t.lookupAll(".tree-cell");
                List<Node> cells = new ArrayList<>(treeCells);
                int row = t.getRow(((TreeItem) selectedItem));
                TreeCell cell = ((TreeCell) cells.get(row));
                if (cell instanceof  EditableTextComboBox) {
                    EditableTextComboBox cCell = (EditableTextComboBox) cell;
                    cCell.startEdit();
                }
            }

        });

        this.getRoot().getChildren().add(new TreeItem <>(new TreeContentClass(null,"disp0","name0", "value0", new String())));
        this.getRoot().getChildren().add(new TreeItem <>(new TreeContentClass(null,"disp1","name1", "value1", new String())));
        this.getRoot().getChildren().add(new TreeItem <>(new TreeContentClass(null,"disp2","name2", "value2", new String())));

    }
    
    public void startNewSession(){
        this.getRoot().getChildren().clear();
        setShowRoot(false);
        for(Protocol p : StaticReferences.controller.getCurrentMethod().getProtocols()){
            TreeItem protocolRoot = new TreeItem(new TreeContentClass(null, p.getType(), "", "", p, true, "Protocol"));
            this.getRoot().getChildren().add(protocolRoot);
            for(SettingsCluster c : p.getClusters()){
                TreeItem clusterRoot = new TreeItem(new TreeContentClass(null, c.getName(),  "", "", c, true, c.getDescription()));
                protocolRoot.getChildren().add(clusterRoot);
                for(SettingObject o : c.getSettings()){
                    if(o == null) continue;
                    TreeItem t = new TreeItem <>(new TreeContentClass(o, o.getViewName(), o.getName(), o.getValueAsString(), o.sValue));                    
                    clusterRoot.getChildren().add(t);                    
                }
            }
        }
    }

}

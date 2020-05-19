/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.trees.tivcallbacks;

import com.tivconsultancy.tivGUI.trees.treecells.EditableTextCell;
import com.tivconsultancy.tivGUI.trees.treecells.EditableTextComboBox;
import com.tivconsultancy.tivGUI.trees.treecells.contentclasses.TreeContentClass;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class SettingsTreeFactory implements Callback<TreeView<TreeContentClass>, TreeCell<TreeContentClass>> {

    public TreeCell<TreeContentClass> call(TreeView<TreeContentClass> p) {
            EditableTextComboBox y = new EditableTextComboBox();
            y.setEditable(true);
            return y;
    }

}

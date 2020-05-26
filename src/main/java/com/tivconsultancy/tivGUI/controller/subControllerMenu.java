/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.controller;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface subControllerMenu {
    
    /**
     * Returns the menu items shown in the menu bar.
     * @return Strings that are displayed in the menu bar, e.g. Files, View
     */
    public List<String> getMainItems();
    /**
     * Return the entries to the menu on which the ident points add
     * @param ident identifier for the menu item
     * @return Strings that are displayed in the drop down, e.g. Export, Eew
     */
    public List<String> getMenuEntries(String ident);
    /**
     * Returns the action event that to which ident is pointing add
     * @param ident name of the action event
     * @return action event that should be connected to when a user requests some action, e.g. exporting a file, starting new session
     */
    public EventHandler<ActionEvent> getActionEvent(String ident);
    /**
     * Returns an icon to display at the menu item
     * @param ident name of the icon
     * @return ImageView containing the icon that should be displayed
     */
    public ImageView getIcon(String ident);
    
}

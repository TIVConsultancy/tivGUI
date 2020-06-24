/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.menue;

import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.subControllerMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Thomas Ziegenhein
 */
public class tivMenuBar extends MenuBar {

    public tivMenuBar() {

        subControllerMenu con = StaticReferences.controller.getMenuController(null);        

        for (String s : con.getMainItems()) {
            Menu m;
            if (con.getIcon(s) != null) {
                m = new Menu(s, con.getIcon(s));
            } else {
                m = new Menu(s);
            }

            for (String sub : con.getMenuEntries(s)) {
                MenuItem mi;
                if (con.getIcon(sub) != null) {
                    mi = new MenuItem(sub, con.getIcon(sub));
                } else {
                    mi = new MenuItem(sub);
                }
                m.getItems().add(mi);
                mi.setOnAction(con.getActionEvent(sub));
            }

            getMenus().add(m);
        }

    }

}

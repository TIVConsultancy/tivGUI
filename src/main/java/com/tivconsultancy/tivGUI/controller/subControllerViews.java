/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.controller;

import com.tivconsultancy.tivGUI.viewer.ViewerContainer;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface subControllerViews {
    
    public List<String> getIdentForViews();
    public Node getView(String ident);
    public ViewerContainer getViewContainer(String ident);
    public void update();
}

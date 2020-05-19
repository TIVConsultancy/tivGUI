/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.viewer.tree;

import com.tivconsultancy.tivGUI.viewer.ParentViewer;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class DrawTreeClass extends AnchorPane {

    ViewerRoot root = null;
    
    public DrawTreeClass(){
        root = new ViewerRoot(null, null, null, new ParentViewer());
    }
    
}

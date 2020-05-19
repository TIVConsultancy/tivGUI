/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.viewer.tree;

import com.tivconsultancy.tivGUI.viewer.PaneWithControlBase;
import com.tivconsultancy.tivGUI.viewer.ParentViewer;
import javafx.geometry.Orientation;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class ViewerRoot {

    Orientation splitType = null;
    private ViewerNode Node1 = null;
    private ViewerNode Node2 = null;
    ParentViewer content;
    
    public ViewerRoot(Orientation splitType, ViewerNode Node1, ViewerNode Node2, ParentViewer content) {
        this.splitType = splitType;
        this.Node1 = Node1;
        this.Node2 = Node2;
        this.content = content;
    }
    
}

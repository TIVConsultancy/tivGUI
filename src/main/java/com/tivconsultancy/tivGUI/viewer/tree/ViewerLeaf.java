/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.viewer.tree;

import com.tivconsultancy.tivGUI.viewer.PaneWithControlBase;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class ViewerLeaf {
    PaneWithControlBase o;
    ViewerNode parent;

    public ViewerLeaf(PaneWithControlBase o, ViewerNode parent) {
        this.o = o;
        this.parent = parent;
    }
        
    
}

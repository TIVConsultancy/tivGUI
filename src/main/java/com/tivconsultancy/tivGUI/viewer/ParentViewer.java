/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.viewer;

import java.awt.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class ParentViewer extends AnchorPane {

    private PaneWithControlBase o;
    private String internIdent = "root";

    public ParentViewer() {
        o = new PaneWithControlBase(this, null, -1, -1, -1) {
        };
        o.setCollapsable(false);
        AnchorPane.setTopAnchor(o, 0.0);
        AnchorPane.setRightAnchor(o, 0.0);
        AnchorPane.setBottomAnchor(o, 0.0);
        AnchorPane.setLeftAnchor(o, 0.0);
        getChildren().add(o);
    }

    public PaneWithControlBase getContent() {
        return o;
    }
    
    public void clearSelection(int index, PaneWithControlBase caller){
        o.clearSelection(index, caller);
    }

    public void replacePane(PaneWithControlBase replace) {
        o = replace;
    }

    public String getInternalIdent() {
        return internIdent;
    }

}

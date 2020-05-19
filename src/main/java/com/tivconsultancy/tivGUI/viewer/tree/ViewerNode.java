/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.viewer.tree;

import com.tivconsultancy.tivGUI.viewer.PaneWithControlBase;
import javafx.geometry.Orientation;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class ViewerNode {

    Orientation splitType = null;
    private ViewerNode Node1 = null;
    private ViewerNode Node2 = null;
    PaneWithControlBase content = null;

    public ViewerNode(Orientation splitType, ViewerNode Node1, ViewerNode Node2) {
        this.splitType = splitType;
        this.Node1 = Node1;
        this.Node2 = Node2;
    }

    public ViewerNode(Orientation splitType, ViewerNode Node1, ViewerNode Node2, PaneWithControlBase content) {
        this.splitType = splitType;
        this.Node1 = Node1;
        this.Node2 = Node2;
        this.content = content;
    }

    public ViewerNode getNode1() {
        return Node1;
    }

    public ViewerNode getNode2() {
        return Node2;
    }

    public void setNode1(ViewerNode node1) {
        Node1 = node1;
    }

    public void setNode2(ViewerNode node2) {
        Node2 = node2;
    }

    public void setContent(PaneWithControlBase content) {
        this.content = content;
    }

    public PaneWithControlBase getContent() {
        return this.content;
    }

    public void split(Orientation options) {
        Node1 = new ViewerNode(options, null, null, content);
        Node2 = new ViewerNode(options, null, null, new PaneWithControlBase(null, null, 0, 0, 0) {
                       });
    }

    public void collapse() {
        Node1 = null;
        Node2 = null;
    }

}

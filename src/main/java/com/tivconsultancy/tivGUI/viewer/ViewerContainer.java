/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.viewer;

import com.tivconsultancy.tivGUI.helpfunctions.ImageViewWithExtraFunctions;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public abstract class ViewerContainer {

    private String name;
    private Node toView;

    public abstract void createNode(Object... p);
    public abstract void update(Object... p);
    public abstract void bindWidth(ObservableValue<? extends Number> o, Double initWidth);
    public abstract void bindHeight(ObservableValue<? extends Number> o, Double initWidth);
    public abstract Node getNode();
    
    public static ViewerContainer getDefault(String text){
        ViewerContainer def = new ViewerContainer() {
            @Override
            public void createNode(Object... p) {
            }

            @Override
            public void update(Object... p) {
            }
            
            @Override
            public Node getNode() {
                return new Label(text);
            }

            @Override
            public void bindWidth(ObservableValue<? extends Number> o, Double initWidth) {                
            }

            @Override
            public void bindHeight(ObservableValue<? extends Number> o, Double initWidth) {                
            }


        };
        return def;
    }

}

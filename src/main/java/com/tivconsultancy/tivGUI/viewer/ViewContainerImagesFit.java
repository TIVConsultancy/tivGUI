/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.viewer;

import com.tivconsultancy.opentiv.imageproc.primitives.ImageInt;
import com.tivconsultancy.tivGUI.helpfunctions.ImageViewWithExtraFunctions;
import java.awt.image.BufferedImage;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class ViewContainerImagesFit extends ViewerContainer {

    private String name;
    private ImageViewWithExtraFunctions toView;
    private AnchorPane anchored;

    public ViewContainerImagesFit(BufferedImage img, String name) {
        this.name = name;
        toView = new ImageViewWithExtraFunctions(SwingFXUtils.toFXImage(img, null), name);
        anchored = new AnchorPane(toView);
        AnchorPane.setTopAnchor(toView, 0.0);
        AnchorPane.setRightAnchor(toView, 0.0);
        AnchorPane.setBottomAnchor(toView, 0.0);
        AnchorPane.setLeftAnchor(toView, 0.0);
//        toView.fitWidthProperty().bind(anchored.widthProperty());
//        toView.fitHeightProperty().bind(anchored.heightProperty());
        toView.setPreserveRatio(true);    
        toView.setSmooth(false);
    }

    public ViewContainerImagesFit(ImageInt img, String name) {
        this.name = name;
        if(img == null || img.getBuffImage() == null){
            toView = new ImageViewWithExtraFunctions(SwingFXUtils.toFXImage(new ImageInt(50, 50, 0).getBuffImage(), null), name);
        }else{
            toView = new ImageViewWithExtraFunctions(SwingFXUtils.toFXImage(img.getBuffImage(), null), name);
        }
        
        anchored = new AnchorPane(toView);
        AnchorPane.setTopAnchor(toView, 0.0);
        AnchorPane.setRightAnchor(toView, 0.0);
        AnchorPane.setBottomAnchor(toView, 0.0);
        AnchorPane.setLeftAnchor(toView, 0.0);
//        toView.fitWidthProperty().bind(anchored.widthProperty());
//        toView.fitHeightProperty().bind(anchored.heightProperty());
        toView.setPreserveRatio(true);
    }

    public void createNode(Object... p) {
        Image img = SwingFXUtils.toFXImage((BufferedImage) p[0], null);
        toView.setImage(SwingFXUtils.toFXImage((BufferedImage) p[0], null));
        toView.setViewport(new Rectangle2D(0, 0, img.getWidth(), img.getHeight()));
//        anchored.setPrefWidth(img.getWidth());
//        anchored.setPrefHeight(img.getHeight());
//        toView.fitWidthProperty().setValue(img.getWidth());
//        toView.fitHeightProperty().setValue(img.getHeight());
    }

    public void update(Object... p) {
        createNode(p);
    }

    public Node getNode() {
        return anchored;
    }

    @Override
    public void bindWidth(ObservableValue<? extends Number> o, Double initWidth) {
        toView.fitWidthProperty().unbind();
        if(initWidth != null){
            toView.setFitWidth(initWidth);
        }
        toView.fitWidthProperty().bind(o);              
    }

    @Override
    public void bindHeight(ObservableValue<? extends Number> o, Double initWidth) {
        toView.fitHeightProperty().unbind();
        if(initWidth != null){
            toView.setFitHeight(initWidth);
        }
        toView.fitHeightProperty().bind(o);
    }

}

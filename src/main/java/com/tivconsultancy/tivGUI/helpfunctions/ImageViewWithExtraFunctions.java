/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.helpfunctions;

import com.sun.javafx.sg.prism.NGImageView;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.prism.Graphics;
import com.sun.prism.Texture;
import com.sun.prism.impl.BaseResourceFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * External code snippets from StackOverFlow by unknown author(s)
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254 
 */
public class ImageViewWithExtraFunctions extends ImageView {

    EventHandler<ScrollEvent> zoom_scroll;
    EventHandler<MouseEvent> zoom_drag;
    EventHandler<MouseEvent> zoom_pressed;

    ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    
    boolean pixelZoom = true;

    int MIN_PIXELS = 10;

    public ImageViewWithExtraFunctions(){
        super();
    }
    
    public ImageViewWithExtraFunctions(Image img){
        super(img);
        initEvents();
        setZoomEvents();
        setViewport(new Rectangle2D(0, 0, img.getWidth(), img.getHeight()));        
        fitWidthProperty().setValue(img.getWidth());
        fitHeightProperty().setValue(img.getHeight());
    }
    
    private void setZoomEvents(){
        setOnMousePressed(zoom_pressed);
        setOnMouseDragged(zoom_drag);
        setOnScroll(zoom_scroll);
    }
    
    private void initEvents() {
        
        ImageView o = this;        

        zoom_scroll = new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent e) {
                double width = getImage().getWidth();
                double height = getImage().getHeight();
                double delta = e.getDeltaY();
                Rectangle2D viewport = getViewport();
                
//                double scale = Math.pow(1.01, delta);

                double scale = clamp(Math.pow(1.01, delta),
                                     // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
                                     Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
                                     // don't scale so that we're bigger than image dimensions:
                                     Math.max(width / viewport.getWidth(), height / viewport.getHeight())
                );
                
                Point2D mouse = getImageCoordinates(o, new Point2D(e.getX(), e.getY()));

                double newWidth = viewport.getWidth() * scale;
                double newHeight = viewport.getHeight() * scale;

                double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
                                       0, width - newWidth);
                double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
                                       0, height - newHeight);

                setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
            }
        };

        zoom_drag = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2D dragPoint = getImageCoordinates(o, new Point2D(e.getX(), e.getY()));
                shift(o, dragPoint.subtract(mouseDown.get()));
                mouseDown.set(getImageCoordinates(o, new Point2D(e.getX(), e.getY())));
            }
        };

        zoom_pressed = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2D mousePress = getImageCoordinates(o, new Point2D(e.getX(), e.getY()));
                mouseDown.set(mousePress);
            }
        };
        
       

    }

    // shift the viewport of the imageView by the specified delta, clamping so
    // the viewport does not move off the actual image:
    public void shift(ImageView imageView, Point2D delta) {
        Rectangle2D viewport = imageView.getViewport();

        double width = imageView.getImage().getWidth();
        double height = imageView.getImage().getHeight();

        double maxX = width - viewport.getWidth();
        double maxY = height - viewport.getHeight();

        double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
        double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

        imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
    }

    private double clamp(double value, double min, double max) {

        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    // convert mouse coordinates in the imageView to coordinates in the actual image:
    public static Point2D getImageCoordinates(ImageView imageView, Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

        Rectangle2D viewport = imageView.getViewport();
        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(),
                viewport.getMinY() + yProportion * viewport.getHeight());
    }
    
//    @Override 
//    protected NGNode impl_createPeer() {
//        if(!pixelZoom){
//            return new NGImageView();
//        }
//        return new NGImageView() {
//            private com.sun.prism.Image image;
//
//            @Override 
//            public void setImage(Object img) {
//                super.setImage(img);
//                image = (com.sun.prism.Image) img;
//            }
//
//            @Override 
//            protected void renderContent(Graphics g) {
//                BaseResourceFactory factory = (BaseResourceFactory) g.getResourceFactory();
//                Texture tex = factory.getCachedTexture(image, Texture.WrapMode.CLAMP_TO_EDGE);
//                tex.setLinearFiltering(false);
//                tex.unlock();
//                super.renderContent(g);
//            }
//        };
//    }

}
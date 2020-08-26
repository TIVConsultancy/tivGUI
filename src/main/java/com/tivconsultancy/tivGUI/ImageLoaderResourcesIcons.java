/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class ImageLoaderResourcesIcons {
    
    private String source;
    private Object refObject;
    
    public ImageLoaderResourcesIcons(String source, Object refObject){
        this.source = source;
        this.refObject = refObject;
    }
    
    public ImageView get(String name) throws IOException{
        BufferedImage buff = ImageIO.read(refObject.getClass().getResourceAsStream(source + name));
        return new ImageView(SwingFXUtils.toFXImage(scaleIMG(buff), null));
    }
    
    private BufferedImage scaleIMG(BufferedImage inputImage) {
        int imgHeight = inputImage.getHeight();
        int imgWidth = inputImage.getWidth();
        int leadingSize = Math.max(imgHeight, imgWidth);        
        int scaledWidth = (int) (16 * ((1.0*imgWidth)/(1.0*leadingSize)));
        int scaledHeight = (int) (16 * ((1.0*imgHeight)/(1.0*leadingSize)));
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                                                      scaledHeight, inputImage.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }
    
}

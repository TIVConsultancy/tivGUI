/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI;

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
public class ImageLoaderResources {
    
    private String source;
    private Object refObject;
    
    public ImageLoaderResources(String source, Object refObject){
        this.source = source;
        this.refObject = refObject;
    }
    
    public ImageView get(String name) throws IOException{
        BufferedImage buff = ImageIO.read(refObject.getClass().getResourceAsStream(source + name));
        return new ImageView(SwingFXUtils.toFXImage(buff, null));
    }
    
}

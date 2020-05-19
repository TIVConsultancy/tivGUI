/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI;

import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import com.tivconsultancy.tivGUI.controller.Controller;
import com.tivconsultancy.tivGUI.logging.Windowhandler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.apache.commons.logging.Log;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public final class StaticReferences {

    public static Controller controller = null;
    
    private static Logger logger = Logger.getLogger("tivGUI");

    Windowhandler loggingWindow = Windowhandler.getInstance();
    public static LookUp<ImageView> standardIcons = new LookUp<>();
    public static final long startTime = java.lang.System.currentTimeMillis();    
    
    public static Logger getlog() {
        return logger;
    }

    public static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        return new ImageView(wr).getImage();
    }    
    
    public static void initIcons(Object o) {        
    }
    
    public static void setStandardIcons(Object o, String source){
        standardIcons = new LookUp<>();
        File file = new File(
                o.getClass().getClassLoader().getResource(source).getFile()
        );
        
        for(File f : file.listFiles()){
            standardIcons.add(new NameObject<>(f.getName(), new ImageView(new Image(f.toURI().toString(), 16, 16, true, true))));            
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI;

import com.tivconsultancy.tivGUI.logging.Windowhandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import com.tivconsultancy.tivGUI.controller.ControllerUI;
import java.io.IOException;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public final class StaticReferences {

    public static ControllerUI controller = null;

    private static Logger logger = Logger.getLogger("tivGUI");
    
    Windowhandler loggingWindow = Windowhandler.getInstance();
//    public static LookUp<ImageView> standardIcons = new LookUp<>();
    public static ImageLoaderResourcesIcons standardIcons;
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

    public static void setStandardIcons(Object o, String source) throws IOException {
        standardIcons = new ImageLoaderResourcesIcons(source, o);

    }

//    public static void setStandardIcons(Object o, String source) throws IOException {
//        standardIcons = new LookUp<>();
//        File file = new File(
//                o.getClass().getClassLoader().getResource(source).getFile()
//        );
//
//        final File jarFile = file;
//        System.out.println(jarFile);
//        if (jarFile.isFile()) {
//            try (JarFile jar = new JarFile(jarFile)) {
//                final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
//                while (entries.hasMoreElements()) {
//                    final String name = entries.nextElement().getName();
//                    if (name.startsWith(source + "/")) { //filter according to the path
//                        System.out.println(name);
//                    }
//                }
//            } //gives ALL entries in jar
//        } else { // Run with IDE
//            for (File f : file.listFiles()) {
//                standardIcons.add(new NameObject<>(f.getName(), new ImageView(new Image(f.toURI().toString(), 16, 16, true, true))));
//            }
//        }
//
//    }
    public static BufferedImage scaleIMG(BufferedImage inputImage, int scaledWidth, int scaledHeight) {
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }

}

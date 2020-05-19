/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.plots;

import com.tivconsultancy.opentiv.math.primitives.OrderedPair;
import com.tivconsultancy.opentiv.postproc.pixelplot.PixelDiagram;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Ziegenhein
 */
public class tivGUI_PixelDiagram extends PixelDiagram {

    List<String> legend = new ArrayList<>();
    List<Color> displayColors = new ArrayList<>();

    public tivGUI_PixelDiagram(double iSizeX, double iSizeY) {
        super(iSizeX, iSizeY);
        setDefaults();
    }
    
//    /**
//     * Draws the canvas without data for start up
//     * @param lo The (empty) result objects to be considered
//     * @param iSizeX screen size
//     * @param iSizeY screen size
//     */
//    private void drawEmptyCanvas(double iSizeX, double iSizeY) {
//        setSize(iSizeX, iSizeY);
//        if (lo == null || lo.isEmpty()) {
//            setStretchFactors(iSpaceBetweenTicsX, 1.0);
//        } else {
//            setStretchFactors(iSpaceBetweenTicsX, dMinStretchY);
//        }
//        drawCanvas();
//        drawAxis();
//        if (iSpaceBetweenTicsX > 5) {
//            drawtics();
//        } else {
//            drawYtics();
//        }
//        setLables("", "Composition [%]");
//        drawLabels();
//    }

    /**
     * Resets the graph to default
     */
    private void setDefaults() {
        setDefaultColors();
    }

    /**
     * Clears the color list and resets it to default.
     */
    private void setDefaultColors() {
        displayColors.clear();
        displayColors.add(Color.red);
        displayColors.add(Color.yellow);
        displayColors.add(Color.cyan);
        displayColors.add(Color.pink);
        displayColors.add(Color.green);
    }

    /**
     *
     * @return Legend as list of strings
     */
    public List<String> getLegend() {
        return legend;
    }

    /**
     *
     * @return Colors as list of color objects
     */
    public List<Color> getColors() {
        return displayColors;
    }

    /**
     * Transfers physical points (e.g. m vs microns) to points on the monitor
     *
     * @param loIn physical points (e.g. m vs microns)
     * @return monitor points
     */
    protected List<OrderedPair> transferToDisplayPoints(List<OrderedPair> loIn){
        return null;
    }

    /**
     * Copies the displays data in the plot to the clipboard.
     *
     * @return A complex String object (e.g. tab spaced multi Line String)
     */
    public String getDataForClipboard(){
        return null;
    }

    /**
     * Get the data inside the plot.
     *
     * @param mainFrame Controller of the GUI
     * @param lo Crystals that need to be considered
     * @return physical points (e.g. m vs microns)
     */
    protected List<List<OrderedPair>> getData(){
        return null;
    }

}

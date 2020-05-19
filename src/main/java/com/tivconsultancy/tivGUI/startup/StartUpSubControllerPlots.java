/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.tivGUI.controller.subControllerPlots;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpSubControllerPlots implements subControllerPlots {

    private List<Color> colors = new ArrayList<>();
    private List<String> shapes = new ArrayList<>();
    
    public StartUpSubControllerPlots(){
        initColors();
        initShapes();
    }
    
    private void initColors(){
        colors = new ArrayList<>();
        colors.add(Color.BLACK);
        colors.add(Color.RED);
    }
    
    private void initShapes(){
        shapes = new ArrayList<>();
        shapes.add("");
        shapes.add("-fx-shape: \"M 0 0 L 0 1 L 1 1 L 1 0 Z \";");
        shapes.add("-fx-shape: \"M 0 0 L 1 1 L 1 0 Z \";");
        shapes.add("-fx-shape: \"M 0 0 L 0 1 L 1 0 Z ");
        shapes.add("-fx-shape: \"M 0 0 L 0 1 L 1 1 Z \";");
        shapes.add("-fx-shape: \"M 0 0 L 0 1 L 1 0 L 1 1 L 0 0 Z \";");
    }
            
    @Override
    public String getIndexedPlotXAxisName() {
        return "Index";
    }

    @Override
    public String getIndexedPlotYAxisName() {
        return null;
    }

    @Override
    public List<Color> getLineColors() {
        return colors;
    }
    
    @Override
    public List<String> getLineSymbols() {
        return shapes;
    }

}

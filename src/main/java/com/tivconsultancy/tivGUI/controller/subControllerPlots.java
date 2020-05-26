/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.controller;

import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface subControllerPlots {
    
    public String getIndexedPlotXAxisName();
    public String getIndexedPlotYAxisName();
    public List<Color> getLineColors();
    public List<String> getLineSymbols();
    
}

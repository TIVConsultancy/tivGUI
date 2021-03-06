/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.plots;

import com.tivconsultancy.opentiv.datamodels.Refreshable;
import com.tivconsultancy.opentiv.datamodels.Results1DPlotAble;
import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.opentiv.highlevel.protocols.NameSpaceProtocolResults1D;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class TIVPlotArea extends AnchorPane implements Refreshable {

    protected BorderPane borderPane;
    protected MenuButton availLayers;
    protected MultiClassLinePlot linePlot;
    // Implement operations performed on data: smoothing, derivation, scaling etc.
    protected MultiClassLinePlot Operations;
    protected Region parent;
    protected LookUp<Series> clickedSeries = new LookUp<>();
    protected LookUp<Color> colorOfPlot = new LookUp<>();
    protected LookUp<String> symbolOfPlot = new LookUp<>();
    protected List<String> selectedSeries = new ArrayList<>();
    protected int colorCounter = 0;
    protected int symbolCounter = 0;

    public TIVPlotArea() {
        this(null);
    }

    public TIVPlotArea(Region parent) {
        this.parent = parent;
        setDefault();
        getChildren().add(linePlot);
        getChildren().add(availLayers);

        AnchorPane.setTopAnchor(linePlot, 0.0);
        AnchorPane.setRightAnchor(linePlot, 0.0);
        AnchorPane.setBottomAnchor(linePlot, 0.0);
        AnchorPane.setLeftAnchor(linePlot, 0.0);

        AnchorPane.setTopAnchor(availLayers, 0.0);
        AnchorPane.setRightAnchor(availLayers, 0.0);

        initChoices();

        linePlot.setOnMousePressed((MouseEvent event) -> {

            Point2D mouseSceneCoords = new Point2D(event.getSceneX(), event.getSceneY());
            double x = linePlot.getXAxis().sceneToLocal(mouseSceneCoords).getX();
            double y = linePlot.getYAxis().sceneToLocal(mouseSceneCoords).getY();

//            linePlot.addShape("Vert1", new Line(x, 0, x, 100));
            System.out.println(linePlot.getXAxis().getValueForDisplay(x) + ",  "
                    + linePlot.getYAxis().getValueForDisplay(y));

            linePlot.addLinePlotCoordinates("VertPl", Math.round(Double.valueOf(linePlot.getXAxis().getValueForDisplay(x).toString())), Double.NaN, Math.round(Double.valueOf(linePlot.getXAxis().getValueForDisplay(x).toString())), Double.NaN);
//            linePlot.addLinePlotCoordinates("HoriPl", Double.NaN, Double.valueOf(linePlot.getYAxis().getValueForDisplay(y).toString()), Double.NaN, Double.valueOf(linePlot.getYAxis().getValueForDisplay(y).toString()));
            linePlot.addLinePlotCoordinates("HoriPl", 0, Double.valueOf(linePlot.getYAxis().getValueForDisplay(y).toString()), 10000, Double.valueOf(linePlot.getYAxis().getValueForDisplay(y).toString()));

        });

        linePlot.setAnimated(false);
        linePlot.setLegendSide(Side.LEFT);
        linePlot.getYAxis().setLabel(null);
        linePlot.getXAxis().setLabel("Index");

        StaticReferences.controller.addObjectToRefresh(this);
    }

    private void initChoices() {
        for (Protocol p : StaticReferences.controller.getCurrentMethod().getProtocols()) {
            for (NameSpaceProtocolResults1D e : p.get1DResultsNames()) {
                CheckMenuItem o = new CheckMenuItem(e.toString());
                o.setOnAction(event -> changeLayerSelection(o, event));
                availLayers.getItems().add(o);
            }
        }
    }

    private void changeLayerSelection(CheckMenuItem o, ActionEvent ev) {
        String seriesName = o.getText(); //StaticReferences.controller.getViewControllerPlots(null).getName(o);
        selectedSeries.remove(seriesName);
        if (o.isSelected()) {
            clickedSeries.addDuplFree(new NameObject(seriesName, new Series()));
            selectedSeries.add(seriesName);
            if (colorOfPlot.addDuplFree(new NameObject<>(seriesName, getLineColor(colorCounter)))) {
                colorCounter++;
            }
            if (symbolOfPlot.addDuplFree(new NameObject<>(seriesName, getSymbol(symbolCounter)))) {
                symbolCounter++;
            }
        }
        replot();
    }

    public void activateLayerSelection(String ident) {
        try {
            for(MenuItem m : availLayers.getItems()){
                if(m.getText().equals(ident)){
                    ((CheckMenuItem) m).setSelected(true);
                    ((CheckMenuItem) m).fire();
                }
            }
        } catch (Exception e) {
        }

    }

    public void replot() {
        for (String s : clickedSeries.getNames()) {
            linePlot.getData().remove(clickedSeries.get(s));
        }

        for (String s : selectedSeries) {
            try {

                Series xy = new Series();
                xy.setName(StaticReferences.controller.getViewControllerPlots(null).getName(s));
                clickedSeries.set(s, xy);
//            for (int i = 0; i < StaticReferences.controller.getPlotAbleOverTimeResults().getIndexedResults().getSize(); i++) {
//                try {
//                    Results1DPlotAble b = ((Results1DPlotAble) StaticReferences.controller.getPlotAbleOverTimeResults().getEntry(i));
//                    Integer index = StaticReferences.controller.getPlotAbleOverTimeResults().getIndexedResults().getEntry(i);
//                    Double value = ((Results1DPlotAble) StaticReferences.controller.getPlotAbleOverTimeResults().getRes(index+"")).getRes(s);                    
//                    xy.getData().add(new XYChart.Data(index, value));
//                } catch (Exception e) {
//                    StaticReferences.getlog().log(Level.SEVERE, "Cannot plot data from 1D Result", e);
//                }
//            }
                for (Iterator it = StaticReferences.controller.getPlotAbleOverTimeResults().getAllKeys().iterator(); it.hasNext();) {
                    String ident = (String) it.next();
                    try {
                        Results1DPlotAble b = (Results1DPlotAble) StaticReferences.controller.getPlotAbleOverTimeResults().getRes(ident);
                        Integer index = b.getIndex();
                        Double value = b.getRes(s);
                        xy.getData().add(new XYChart.Data(index, value));
                    } catch (Exception e) {
                        StaticReferences.getlog().log(Level.SEVERE, "Cannot plot data from 1D Result", e);
                    }
                }
                linePlot.getData().add(xy);
            } catch (Exception e) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot plot data from 1D Result", e);
            }
        }
        manageLineAppearance();
    }

    public List<Color> getLineColors() {
        return StaticReferences.controller.getViewControllerPlots(null).getLineColors();
    }

    private Color getLineColor(final int index) {
        List<Color> colors = StaticReferences.controller.getViewControllerPlots(null).getLineColors();

        if (colors.isEmpty()) {
            return Color.BLACK;
        }
        int internalIndex = index;
        while (internalIndex >= colors.size()) {
            internalIndex = internalIndex - colors.size();
        }
        if (index < 0) {
            return Color.BLACK;
        }
        return colors.get(internalIndex);
    }

    private String getSymbol(final int index) {
        List<String> shapes = StaticReferences.controller.getViewControllerPlots(null).getLineSymbols();

        if (shapes.isEmpty()) {
            return "";
        }
        int internalIndex = index;
        while (internalIndex >= shapes.size()) {
            internalIndex = internalIndex - shapes.size();
        }
        if (index < 0) {
            return "";
        }
        return shapes.get(internalIndex);
    }

    protected void manageLineAppearance() {
        String newColor = "";
        for (String s : selectedSeries) {
            newColor = newColor + String.format("CHART_COLOR_" + (selectedSeries.indexOf(s) + 1) + ": %s ;", formatColorToRGB(colorOfPlot.get(s)));
        }
        linePlot.setStyle(newColor);
    }

    private String formatColorToRGB(Color c) {
        if (c == null) {
            return String.format("#%02x%02x%02x", 0, 0, 0);
        }
        int r = (int) (255 * c.getRed());
        int g = (int) (255 * c.getGreen());
        int b = (int) (255 * c.getBlue());

        return String.format("#%02x%02x%02x", r, g, b);
    }

    private void setDefault() {
        linePlot = new MultiClassLinePlot();
        availLayers = new MenuButton();
        setDefaultPlotBehaviour();
    }

    private void setDefaultPlotBehaviour() {
        ((NumberAxis) linePlot.getXAxis()).setForceZeroInRange(false);
        ((NumberAxis) linePlot.getXAxis()).setAutoRanging(true);
        ((NumberAxis) linePlot.getYAxis()).setForceZeroInRange(false);
    }

    @Override
    public void refresh() {
        replot();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.plots;

import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Thomas Ziegenhein
 */
public class MultiClassLinePlot extends LineChart {

    private LookUp<Shape> shapes = new LookUp<>();
    private LookUp<LinePlotCoordinates> lines = new LookUp<>();
    private LookUp<Line> linesInPlot = new LookUp<>();

    public MultiClassLinePlot() {
        this(new NumberAxis(), new NumberAxis());
        this.getXAxis().setLabel("X-Axis");
        this.getYAxis().setLabel("Y-Axis");
    }

    public MultiClassLinePlot(Axis axis, Axis axis1) {
        super(axis, axis1);
    }

    public void addShape(String name, Shape s) {
        shapes.addDuplFree(new NameObject<>(name, s));
        layoutPlotChildren();
    }

    public void addLinePlotCoordinates(String name, double xStart, double yStart, double xEnd, double yEnd) {
        if(!lines.set(name, new LinePlotCoordinates(xStart, yStart, xEnd, yEnd))){
            lines.add(new NameObject<>(name, new LinePlotCoordinates(xStart, yStart, xEnd, yEnd)));
        }
        layoutPlotChildren();
    }
    
    private void updateCoordinateShapes(){
        for(NameObject<LinePlotCoordinates> l: lines.getContent()){
            LinePlotCoordinates lineCoordinates = l.o;
            if(linesInPlot.get(l.name) == null){
                linesInPlot.add(new NameObject<>(l.name, new Line(0, 0, 0, 0)));
            }
            
            double newXStart;
            if(lineCoordinates.xStart == null || lineCoordinates.xStart.isNaN()){
                newXStart = 0;
            }else{
                newXStart = getXAxis().getDisplayPosition(lineCoordinates.xStart);
            }
                        
            double newYStart;
            if(lineCoordinates.yStart == null || lineCoordinates.yStart.isNaN()){
                newYStart = 0;
            }else{
                newYStart = getYAxis().getDisplayPosition(lineCoordinates.yStart);
            }
            
            double newXEnd;
            if(lineCoordinates.xEnd == null || lineCoordinates.xEnd.isNaN()){
                newXEnd = this.getWidth();
            }else{
                newXEnd = getXAxis().getDisplayPosition(lineCoordinates.xEnd);
            }
            
            double newYEnd;
            if(lineCoordinates.yEnd == null || lineCoordinates.yEnd.isNaN()){
                newYEnd = this.getHeight();
            }else{
                newYEnd = getYAxis().getDisplayPosition(lineCoordinates.yEnd);
            }

            // Implement when line is on axis that the axis gets wider
//            if(newXStart == 0 && newXEnd == 0){
//                getYAxis().setStyle("-fx-border-color: greenyellow; -fx-stroke-width: 5px;");
//            }else{
//                getYAxis().setStyle("-fx-stroke-width: 2px;");
//            }
            
            linesInPlot.get(l.name).setStartX(newXStart);
            linesInPlot.get(l.name).setStartY(newYStart);
            linesInPlot.get(l.name).setEndX(newXEnd);
            linesInPlot.get(l.name).setEndY(newYEnd);
        }
    }

    @Override
    public void layoutPlotChildren() {
        super.layoutPlotChildren();
        getPlotChildren().removeAll(shapes.getValues());
        getPlotChildren().addAll(shapes.getValues());
        getPlotChildren().removeAll(linesInPlot.getValues());
        updateCoordinateShapes();
        getPlotChildren().addAll(linesInPlot.getValues());
//                shapes.clear();
//                for (Data<Number, Number> d : series.getData()) {
//                    double x = xAxis.getDisplayPosition(d.getXValue());
//                    double y = yAxis.getDisplayPosition(d.getYValue());
//                    shapes.add(new Circle(x, y, 3, Color.RED));
//                }

    }

    protected class LinePlotCoordinates {

        public Double xStart;
        public Double yStart;
        public Double xEnd;
        public Double yEnd;
        
        public LinePlotCoordinates(Double xStart, Double yStart, Double xEnd, Double yEnd){
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }
        
    }

}

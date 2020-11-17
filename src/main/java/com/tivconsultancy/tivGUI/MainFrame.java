/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI;

import com.tivconsultancy.tivGUI.menue.tivMenuBar;
import com.tivconsultancy.tivGUI.plots.TIVPlotArea;
import com.tivconsultancy.tivGUI.tables.tivSettingsDetails;
import com.tivconsultancy.tivGUI.trees.tivImageTree;
import com.tivconsultancy.tivGUI.viewer.ParentViewer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class MainFrame extends GridPane {

    protected MenuBar menuBar;
    protected GridPane gridContentPane;
    protected tivSettingsDetails tableDetails;
    protected tivTabbedTree tabPane;

    protected ColumnConstraints columnConstraintsMainFame;
    protected ColumnConstraints columnConstraintsTreeAndTreeView;
    protected ColumnConstraints columnConstraintsContentAndLog;
    protected RowConstraints rowConstraintsMenuBar;
    protected RowConstraints rowConstraintsMainFrame;
    protected RowConstraints rowConstraintsContentAndTree;
    protected RowConstraints rowConstraintsLowerContent;

    protected AnchorPane anchorDetailView;

    protected TIVPlotArea anchorTimePlot;

    protected ScrollPane scrollPanDetails;
    protected Pane scrollPaneDataViewer;

    public static List<Image> iconsTIVGUIMainFrame;
    public static Image loadGif;
    public static Image logo;

    protected AnchorPane dataView;

    public MainFrame() {
        initComponents();
        setSize();
        setTreeView();
        setDataView();

        getChildren().add(menuBar);

        gridContentPane.getChildren().add(scrollPaneDataViewer);
        gridContentPane.getChildren().add(anchorTimePlot);
        getChildren().add(gridContentPane);
        setContentToCells();
        setContraints();

    }

    private void initComponents() {
        gridContentPane = new GridPane();
        menuBar = new tivMenuBar();

        tabPane = new tivTabbedTree();
        scrollPanDetails = new ScrollPane();
        tableDetails = new tivSettingsDetails();

        scrollPaneDataViewer = new AnchorPane();
        anchorTimePlot = new TIVPlotArea();

        anchorDetailView = new AnchorPane();
        dataView = new ParentViewer();
    }
    
    public TIVPlotArea getPlotArea(){
        return anchorTimePlot;
    }

    private void setSize() {
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(746.0);
        setPrefWidth(1134.0);
    }

    private void setDetailView() {
//        TreeView o = new tivImageTree();
        scrollPanDetails.setContent(tableDetails);
//        anchorDetailView.getChildren().add(scrollPanDetails);
        AnchorPane.setTopAnchor(scrollPanDetails, 0.0);
        AnchorPane.setLeftAnchor(scrollPanDetails, 0.0);
        AnchorPane.setBottomAnchor(scrollPanDetails, 0.0);
        AnchorPane.setRightAnchor(scrollPanDetails, 0.0);

//        gridContentPane.getChildren().add(anchorDetailView);
    }

    private void setTreeView() {
        tabPane.setTabClosingPolicy(javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE);
        gridContentPane.getChildren().add(tabPane);
    }

    private void setDataView() {
        ((AnchorPane) scrollPaneDataViewer).getChildren().add(dataView);
        AnchorPane.setTopAnchor(dataView, 0.0);
        AnchorPane.setRightAnchor(dataView, 0.0);
        AnchorPane.setBottomAnchor(dataView, 0.0);
        AnchorPane.setLeftAnchor(dataView, 0.0);
    }

    private void setContentToCells() {
        GridPane.setRowSpan(tabPane, 2);
        GridPane.setRowIndex(gridContentPane, 1);
//        GridPane.setRowIndex(anchorDetailView, 1);
        GridPane.setColumnIndex(scrollPaneDataViewer, 1);
        GridPane.setColumnIndex(anchorTimePlot, 1);
        GridPane.setRowIndex(anchorTimePlot, 1);
//        GridPane.setRowIndex(scrollPanDetails, 1);
        GridPane.setColumnIndex(scrollPaneDataViewer, 1);
        GridPane.setColumnIndex(anchorTimePlot, 1);
        GridPane.setRowIndex(anchorTimePlot, 1);
    }

    protected void setContraints() {

        columnConstraintsMainFame = new ColumnConstraints();
        rowConstraintsMenuBar = new RowConstraints();
        rowConstraintsMainFrame = new RowConstraints();
        columnConstraintsTreeAndTreeView = new ColumnConstraints();
        columnConstraintsContentAndLog = new ColumnConstraints();
        rowConstraintsContentAndTree = new RowConstraints();
        rowConstraintsLowerContent = new RowConstraints();

        rowConstraintsMenuBar.setPrefHeight(25.0);
        rowConstraintsMenuBar.setVgrow(javafx.scene.layout.Priority.NEVER);

        rowConstraintsMainFrame.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        rowConstraintsContentAndTree.setMinHeight(50.0);
        rowConstraintsContentAndTree.setPrefHeight(590.0);
        rowConstraintsContentAndTree.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        rowConstraintsLowerContent.setMinHeight(200.0);
        rowConstraintsLowerContent.setPrefHeight(200.0);
        rowConstraintsLowerContent.setVgrow(javafx.scene.layout.Priority.NEVER);

        columnConstraintsMainFame.setHgrow(javafx.scene.layout.Priority.ALWAYS);

        columnConstraintsTreeAndTreeView.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraintsTreeAndTreeView.setPrefWidth(250.0);
        columnConstraintsTreeAndTreeView.setMinWidth(250.0);

        columnConstraintsContentAndLog.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        columnConstraintsContentAndLog.setMinWidth(100.0);
        columnConstraintsContentAndLog.setPrefWidth(900.0);

        getColumnConstraints().add(columnConstraintsMainFame);
        getRowConstraints().add(rowConstraintsMenuBar);
        getRowConstraints().add(rowConstraintsMainFrame);

        gridContentPane.getColumnConstraints().add(columnConstraintsTreeAndTreeView);
        gridContentPane.getColumnConstraints().add(columnConstraintsContentAndLog);
        gridContentPane.getRowConstraints().add(rowConstraintsContentAndTree);
        gridContentPane.getRowConstraints().add(rowConstraintsLowerContent);

    }

    public static void setIcons(Class<?> o, List<String> source) {
        iconsTIVGUIMainFrame = new ArrayList<>();
        for (String s : source) {
            iconsTIVGUIMainFrame.add(new Image(o.getResourceAsStream(s)));
        }
    }
    
    public static void setLogo(Class<?> o, String source) {
        logo = new Image(o.getResourceAsStream(source));
    }
    
    public static Image getLogo() {
        if(logo == null){
            return getIcon().get(0);
        }
        return logo;
    }

    public static void setLoadPicture(Class<?> o, String source) {
        loadGif = new Image(o.getResourceAsStream(source));
    }

    public static List<Image> getIcon() {
//        MainFrame.icons = new ArrayList<>();
//        for(String s : source){
//             icons.add(new Image(o.getResourceAsStream(s)));
//        }
////        icons.add(new Image(o.getResourceAsStream("/com/tivconsultancy/tivGUI/icons/Icon128x128.png")));
////        icons.add(new Image(o.getResourceAsStream("/com/tivconsultancy/tivGUI/icons//Icon64x64.png")));
////        icons.add(new Image(o.getResourceAsStream("/com/tivconsultancy/tivGUI/icons/Icon32x32.png")));
////        icons.add(new Image(o.getResourceAsStream("/com/tivconsultancy/tivGUI/icons/Icon16x16.png")));

        return iconsTIVGUIMainFrame;
    }

    public void startNewSession() {
        tabPane.startNewSession();
    }
    
    public void startNewSettings() {
        tabPane.startNewSettingsTree();
    }

    public File getSelectedFile() {
        return ((tivImageTree.TreeItemFile) tabPane.imageTree.getSelectionModel().getSelectedItem()).getFile();
    }
    
    public void deactivateImageTree(){
        tabPane.imageTree.setDisable(true);        
    }
    
    public void activateImageTree(){
        tabPane.imageTree.setDisable(false);        
    }
    
    public MenuBar getMenueBar(){
        return menuBar;
    }

}

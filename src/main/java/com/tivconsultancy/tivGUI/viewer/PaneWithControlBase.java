package com.tivconsultancy.tivGUI.viewer;

import com.tivconsultancy.opentiv.imageproc.primitives.ImageInt;
import com.tivconsultancy.tivGUI.MainFrame;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.helpfunctions.ImageViewWithExtraFunctions;
import com.tivconsultancy.tivGUI.plots.TIVPlotArea;
import java.io.IOException;
import javafx.scene.control.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class PaneWithControlBase extends ScrollPane {

    protected BorderPane borderPane;
    protected ChoiceBox availLayers;
    private Node contentToViewCenter = null;
    private Pane root;
    private PaneWithControlBase parent;
    private String internIdent = "child";
    private int selectedIndexBeforeSplit = -1;
    private boolean splitActivated = true;
    private boolean collapseActivated = true;
    private ChangeListener choiceListener = null;

    private ImageViewWithExtraFunctions defaultContent;

    public double headerWidth;

    public PaneWithControlBase(Pane root, PaneWithControlBase parent, double height, double width, double headerWidth) {

        this.root = root;
        this.parent = parent;
        borderPane = new BorderPane();
        this.headerWidth = headerWidth;
        generateDefaultContent();
        generateEventHandler();
        setNewContent(height, width, headerWidth);
        bind();
    }

    public PaneWithControlBase(Pane root, PaneWithControlBase parent, double height, double width, double headerWidth, PaneWithControlBase contentToSet) {

        this.root = root;
        this.parent = parent;

        borderPane = new BorderPane();
        this.headerWidth = headerWidth;
        generateDefaultContent();
        generateEventHandler();
        setNewContent(height, width, headerWidth, contentToSet);
        bind();
    }

    private void generateDefaultContent() {
        try {
            defaultContent = new ImageViewWithExtraFunctions(MainFrame.getIcon().get(0), "icon");
        } catch (Exception ex) {
            defaultContent = new ImageViewWithExtraFunctions(SwingFXUtils.toFXImage((new ImageInt(50, 50, 150)).getBuffImage(), null), "default");
            Logger.getLogger(PaneWithControlBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateEventHandler() {
        PaneWithControlBase caller = this;
        choiceListener = new javafx.beans.value.ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue == null) {
                    setCenterContent(defaultContent);
                    setSelecetIndexBeforeSplit(availLayers.getSelectionModel().getSelectedIndex());
                    return;
                }
                if (newValue.toString().equals("Split Horizontal")) {
                    split(Orientation.HORIZONTAL);
                    if (checkValidCoice(oldValue, newValue)) {
                        availLayers.getSelectionModel().select(oldValue);
                    }
                    return;
                }
                if (newValue.toString().equals("Split Vertical")) {
                    split(Orientation.VERTICAL);
                    if (checkValidCoice(oldValue, newValue)) {
                        availLayers.getSelectionModel().select(oldValue);
                    }
                    return;
                }
                if(newValue.toString().equals("Plot")){
                    setCenterContent(new TIVPlotArea());
                    return;
                }
                if (newValue.toString().equals("Collapse") && collapseActivated) {
                    if (collapse()) {
                        return;
                    } else {
                        setCenterContent(StaticReferences.controller.getViewController(null).getView(newValue.toString()));
                        setSelecetIndexBeforeSplit(availLayers.getSelectionModel().getSelectedIndex());
                    }                    
                } else {
                    ((ParentViewer) root).clearSelection(availLayers.getSelectionModel().getSelectedIndex(), caller);
                    ViewerContainer toCenter = StaticReferences.controller.getViewController(null).getViewContainer(newValue.toString());
//                    toCenter.bindHeight(new ObservableWithPadding(caller.getBorderPane().heightProperty(), availLayers.getHeight() + 10));
//                    toCenter.bindWidth(caller.getBorderPane().widthProperty());
//                    toCenter.bind(caller);                    
                    setCenterContent(toCenter.getNode());
                    setSelecetIndexBeforeSplit(availLayers.getSelectionModel().getSelectedIndex());
                }
            }
        };
    }

    private void bind() {
        if (availLayers == null || availLayers.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        ViewerContainer toCenter = StaticReferences.controller.getViewController(null).getViewContainer(availLayers.getSelectionModel().getSelectedItem().toString());
        toCenter.bindHeight(new ObservableWithPadding(this.heightProperty(), availLayers.getHeight()+2), null);
        toCenter.bindWidth(new ObservableWithPadding(this.widthProperty(), 2), null);
    }

    public void clearSelection(int index, PaneWithControlBase caller) {
        if (this.equals(caller)) {
            return;
        }
        if (splitActivated && availLayers.getSelectionModel().getSelectedIndex() == index) {
            availLayers.getSelectionModel().clearSelection();
        } else if (!splitActivated && getCenterContent() instanceof SplitPane) {
            for (Node n : ((SplitPane) getCenterContent()).getItems()) {
                ((PaneWithControlBase) n).clearSelection(index, caller);
            }
        }
    }

    public void setCollapsable(boolean b) {
        collapseActivated = b;
    }

    private void setEventHandler() {
        availLayers.valueProperty().addListener(choiceListener);
    }

    private boolean checkValidCoice(Object oldValue, Object newValue) {
        if (oldValue != null && oldValue.toString() != "Split Horizontal" && oldValue.toString() != "Split Vertical" && oldValue.toString() != "Collapse") {
            return true;
        }
        return false;
    }

    public ChangeListener getEventHandler() {
        return choiceListener;
    }

    public void removeEventHandler() {
        availLayers.valueProperty().removeListener(choiceListener);
    }

    public String getInternalIdent() {
        return internIdent;
    }

    public Node getCenterContent() {
        return contentToViewCenter;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public ChoiceBox getChoices() {
        return availLayers;
    }

    public Pane getroot() {
        return root;
    }

    public ChoiceBox generateChoices(double headerWidth) {
        ChoiceBox choices = new ChoiceBox();
        choices.getItems().add("Split Horizontal");
        choices.getItems().add("Split Vertical");
        choices.getItems().add("Collapse");
        choices.getItems().add("Plot");
        choices.getItems().addAll(StaticReferences.controller.getViewController(null).getIdentForViews());
        choices.valueProperty().addListener(choiceListener);

        //        choices.setMaxHeight(0.0);
        if (headerWidth < 0) {
            choices.setPrefWidth(USE_COMPUTED_SIZE);
//            choices.setPrefWidth(0);
        } else {
            choices.setPrefWidth(headerWidth);
//            choices.setPrefWidth(0);
        }
        choices.setStyle("-fx-background-color: transparent");
        BorderPane.setAlignment(choices, Pos.TOP_RIGHT);
        return choices;
    }

    public int getSelectedIndexBeforeSplit() {
        return selectedIndexBeforeSplit;
    }

    public void setSelecetIndexBeforeSplit(int index) {
        selectedIndexBeforeSplit = index;
    }

    public static List<Integer> forbiddenIndexesForSplit() {
        List<Integer> forbidden = new ArrayList<>();
        forbidden.add(-1);
        forbidden.add(0);
        forbidden.add(1);
        forbidden.add(2);
        return forbidden;
    }

    public void setValue(Object o) {

    }

    public ChoiceBox generateChoices(double headerWidth, ChoiceBox box) {

        box.valueProperty().addListener(choiceListener);

//        if (headerWidth < 0) {
        box.setPrefWidth(USE_COMPUTED_SIZE);
//        } else {
//            box.setPrefWidth(headerWidth);
//        }
        box.setStyle("-fx-background-color: transparent");
        BorderPane.setAlignment(box, Pos.TOP_RIGHT);
        return box;
    }

    public void setCenterContent(Node cont) {
        contentToViewCenter = cont;
        borderPane.setCenter(contentToViewCenter);
        bind();
    }

    private void setNewContent(double height, double width, double headerWidth) {
        availLayers = generateChoices(headerWidth);

        if (height < 0 || width < 0) {
            borderPane.setPrefHeight(580.0);
            borderPane.setPrefWidth(700.0);
        } else {
            borderPane.setPrefHeight(height);
            borderPane.setPrefWidth(width);
        }

        setCenterContent(defaultContent);
        borderPane.setTop(availLayers);

//        setCenter(borderPane);
        setContent(borderPane);
        setFitToHeight(true);
        setFitToWidth(true);
    }

    private void setNewContent(double height, double width, double headerWidth, PaneWithControlBase contentToSet) {
        contentToSet.getChoices().valueProperty().removeListener(contentToSet.getEventHandler());
        availLayers = contentToSet.getChoices(); //generateChoices(headerWidth);
        setEventHandler();

        if (height < 0 || width < 0) {
            borderPane.setPrefHeight(580.0);
            borderPane.setPrefWidth(700.0);
        } else {
            borderPane.setPrefHeight(height);
            borderPane.setPrefWidth(width);
        }

        setCenterContent(contentToSet.getCenterContent());
//        borderPane.setCenter(contentToSet.getCenterContent());

        borderPane.setTop(availLayers);

//        setCenter(borderPane);
        setContent(borderPane);
        setFitToHeight(true);
        setFitToWidth(true);
    }

    private void setNewContent(PaneWithControlBase contentToSet) {
        contentToSet.getChoices().valueProperty().removeListener(contentToSet.getEventHandler());
        availLayers = contentToSet.getChoices();
        generateEventHandler();
        setEventHandler();
        borderPane = new BorderPane();
        borderPane.setPrefHeight(USE_COMPUTED_SIZE);
        borderPane.setPrefWidth(USE_COMPUTED_SIZE);
        setCenterContent(contentToSet.getCenterContent());
//        borderPane.setCenter(contentToSet.getCenterContent());
        borderPane.setTop(availLayers);
//        contentToViewCenter = contentToSet.getCenterContent();
//setCenter(borderPane);
        setContent(borderPane);
        setFitToHeight(true);
        setFitToWidth(true);
    }

    private void split(Orientation options) {
        if (!splitActivated) {
            return;
        }
        SplitPane split = new SplitPane();
        split.setPrefWidth(getWidth());
        if (parent == null) {
//            split.setPrefHeight(getHeight() - 12);
        } else {
            split.setPrefHeight(getHeight());
        }
        split.setOrientation(options);
        removeEventHandler();
        split.getItems().add(new PaneWithControlBase(root, this, 0, 0, -1, this) {
        });
        split.getItems().add(new PaneWithControlBase(root, this, 0, 0, -1) {
        });
        setCenterContent(split);
//        contentToViewCenter = split;
//        borderPane.setCenter(split);
        borderPane.setTop(null);
        this.splitActivated = false;
        for(Node pwcb : split.getItems()){
            ((PaneWithControlBase) pwcb).bind();
        }
    }

    public PaneWithControlBase getTheOther(PaneWithControlBase selected) {

        if (contentToViewCenter instanceof SplitPane) {
            SplitPane split = (SplitPane) contentToViewCenter;
            if (split == null) {
                return null;
            }
            for (Node n : split.getItems()) {
                if (n instanceof PaneWithControlBase && !n.equals(selected)) {
                    return (PaneWithControlBase) n;
                }
            }
        }

//        if (selected instanceof ViewerInterface) {
//            ViewerInterface view = (ViewerInterface) parent;
//            System.out.println(view.getInternalIdent());
//            if (view.getInternalIdent().equals("root")) {
//                System.out.println(((ParentViewer) parent).getContent().getCenter());
//                if (((ParentViewer) parent).getContent().getCenter() instanceof SplitPane) {
//                    SplitPane split = (SplitPane) ((ParentViewer) parent).getContent().getCenter();
//                    System.out.println("split");
//                    System.out.println(split.getItems());
//                    for (Node n : split.getItems()) {
//                        if (n instanceof PaneWithControlBase && !n.equals(selected)) {
//                            return n;
//                        }
//                    }
//                }
//            }
//            if (parent instanceof PaneWithControlBase) {
//                System.out.println(((PaneWithControlBase) parent).getCenter());
////            for (Node n : ((PaneWithControlBase) parent).getCenter().getChildren()) {
////                if (n instanceof SplitPane) {
////                    for (Node nSplit : ((SplitPane) n).getItems()) {
////                        if (nSplit instanceof PaneWithControlBase && !nSplit.equals(this)) {
////                            return (PaneWithControlBase) nSplit;
////                        }
////                    }
////                }
////            }
//            }
//            if (parent instanceof ParentViewer) {
//                System.out.println(((ParentViewer) parent).getContent().getCenter());
//                if (((ParentViewer) parent).getContent().getCenter() instanceof SplitPane) {
//                    SplitPane split = (SplitPane) ((ParentViewer) parent).getContent().getCenter();
//                    System.out.println("split");
//                    System.out.println(split.getItems());
//                    for (Node n : split.getItems()) {
//                        if (n instanceof PaneWithControlBase && !n.equals(selected)) {
//                            return n;
//                        }
//                    }
//                }
//            for (Node n : ((ParentViewer) parent).getContent().getChildren()) {
//                if (n instanceof SplitPane) {
//                    for (Node nSplit : ((SplitPane) n).getItems()) {
//                        if (nSplit instanceof PaneWithControlBase && !nSplit.equals(this)) {
//                            return (PaneWithControlBase) nSplit;
//                        }
//                    }
//                }
//            }
//    }
        return null;
    }

    public void setParent(PaneWithControlBase newParent) {
        this.parent = newParent;
    }

    public boolean collapse() {
        if (parent instanceof PaneWithControlBase) {
            Node theOther = ((PaneWithControlBase) parent).getTheOther(this);
            if (theOther == null) {
                return false;
            } else if (((PaneWithControlBase) theOther).getCenterContent() instanceof SplitPane) {
                ((PaneWithControlBase) parent).setCenterContent(theOther);
                ((PaneWithControlBase) parent).borderPane.setTop(null);
                ((PaneWithControlBase) parent).splitActivated = false;
                if (((PaneWithControlBase) parent).parent == null) {
                    ((PaneWithControlBase) parent).setCollapsable(false);
                }
                return true;
            } else {
                System.out.println(((PaneWithControlBase) theOther).getChoices().getSelectionModel().getSelectedItem());
                ((PaneWithControlBase) parent).setNewContent(((PaneWithControlBase) theOther));
                ((PaneWithControlBase) parent).splitActivated = true;
                return true;
            }
        }
        return false;
    }

    public static enum ViewerPaneOptions {

    }

    public class ObservableWithPadding implements ObservableValue {

        ObservableValue<? extends Number> o;
        Number padding;

        public ObservableWithPadding(ObservableValue<? extends Number> o, java.lang.Number padding) {
            this.o = o;
            this.padding = padding;
        }

        @Override
        public void addListener(ChangeListener listener) {
            o.addListener(listener);
        }

        @Override
        public void removeListener(ChangeListener listener) {
            o.removeListener(listener);
        }

        @Override
        public Object getValue() {
            return Double.valueOf(o.getValue().toString()) - padding.doubleValue();
        }

        @Override
        public void addListener(InvalidationListener listener) {
            o.addListener(listener);
        }

        @Override
        public void removeListener(InvalidationListener listener) {
            o.removeListener(listener);
        }

    }

}

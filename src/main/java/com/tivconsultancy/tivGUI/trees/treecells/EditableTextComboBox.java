/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.trees.treecells;

import com.tivconsultancy.tivGUI.trees.treecells.contentclasses.TreeContentClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Thomas Ziegenhein
 */
public class EditableTextComboBox extends TreeCell<TreeContentClass> {

    private ComboBox combBox;

    public EditableTextComboBox() {
        if (getItem() != null && getItem().isDummyEntry()) {
            setEditable(false);
        }
        if (getItem() != null && getItem().getHints() != null && !getItem().getHints().isEmpty()) {
            
        }        
        
    }

    @Override
    public void startEdit() {
        super.startEdit();
        setComboBox();
        if (getItem() != null && getItem().isDummyEntry()) {
            setTextAfterInput(getDummyText());
            return;
        }

        String[] sa = getItem().getExampleContent().getClass().toString().split("\\.");

        if (sa != null && sa.length > 0) {
            setTextAfterInput(getItem().getDisplay() + "(" + sa[sa.length - 1] + ")");
        } else {
            setTextAfterInput(getItem().getDisplay());
        }
    }

    public void setComboBox() {
        if (getItem() != null && getItem().isDummyEntry()) {
            setTextAfterInput(getDummyText());
            return;
        }
        if (combBox == null) {
            createCombBox();
        }
        combBox.setPromptText(getItem().toString());
        setGraphic(combBox);
        combBox.requestFocus();
        combBox.getEditor().selectAll();        
        setStyle("-fx-cursor: default;");
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(getTreeItem().getGraphic());
        setTextAfterInput(getString());
        if (getItem() != null && !getItem().getHints().isEmpty()) {
            setStyle("-fx-underline: true; -fx-cursor: hand;");
        }else{
            setStyle("-fx-cursor: default;");
        }
    }

    @Override
    public void updateItem(TreeContentClass item, boolean empty) {
        super.updateItem(item, empty);
        if (getItem() != null && !getItem().getHints().isEmpty()) {
            setStyle("-fx-underline: true; -fx-cursor: hand;");
        }else{
            setStyle("-fx-cursor: default;");
        }
        if (getItem() != null && getItem().isDummyEntry()) {
            if (getTreeView().getSelectionModel().getSelectedItem() == getTreeItem()) {
                setTextAfterInput(getDummyText());
                return;
            }
            setTextAfterInput(getString());
            return;
        }
        if (empty) {
            setTextAfterInput(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                setGraphic(combBox);
            } else {
//                setStyle("-fx-cursor: default;"); 
                setGraphic(getTreeItem().getGraphic());
            }
            getItem().updateContent(getItem().getValue());
            setTextAfterInput(getString());
        }
    }

    private void createCombBox() {
        if (getItem() != null && getItem().isDummyEntry()) {
            return;
        }
        ObservableList<String> options;
        if (getItem() != null && getItem().getHints() != null && !getItem().getHints().isEmpty()) {
            options
                    = FXCollections.observableArrayList(
                            getItem().getHints().toArray(new String[]{""})
                    );
        } else {
            options
                    = FXCollections.observableArrayList();
        }

        combBox = new ComboBox(options);
        combBox.setEditable(true);
        combBox.setOnAction((Event ev) -> {
            getItem().setValue(combBox.getSelectionModel().getSelectedItem().toString());
            updateItem(getItem(), false);
            cancelEdit();

        });

        combBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getDummyText() {
        if (getItem().getValue().isEmpty()) {
            return getItem() == null ? "" : getItem().getDisplay() + " (" + getItem().getShortDescription() + ")";
        }
        return getItem() == null ? "" : getItem().getDisplay() + " (" + getItem().getShortDescription() + ") : " + getItem().getValue();
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
    
    private void setTextAfterInput(String text){
        if(getItem() != null && getItem().getExampleContent() != null && getItem().getExampleContent() instanceof Boolean){
            if(text.contains("true") || text.contains("false")){
                setText(text);
            }else{
                setText("false");
            }
        }else{
            setText(text);
        }
    }
}

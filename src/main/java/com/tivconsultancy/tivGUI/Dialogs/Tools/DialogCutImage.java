/*
 * Copyright 2020 TIVConsultancy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tivconsultancy.tivGUI.Dialogs.Tools;

import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.ControllerWithImageInteraction;
import static com.tivconsultancy.tivGUI.startup.StartUpSubControllerImageTools.ListenersImageTools.Cut_Bottom;
import static com.tivconsultancy.tivGUI.startup.StartUpSubControllerImageTools.ListenersImageTools.Cut_Left;
import static com.tivconsultancy.tivGUI.startup.StartUpSubControllerImageTools.ListenersImageTools.Cut_Right;
import static com.tivconsultancy.tivGUI.startup.StartUpSubControllerImageTools.ListenersImageTools.Cut_Top;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class DialogCutImage extends Dialog {
    
    private ButtonType closeButton = new ButtonType("Done", ButtonData.OK_DONE);
    
    private Button cutLeft_b = new Button("Left");
    private TextField cutLeft_t;
    private Button cutTop_b = new Button("Top");
    private TextField cutTop_t;
    private Button cutRight_b = new Button("Right");
    private TextField cutRight_t;
    private Button cutBottom_b = new Button("Bottom");
    private TextField cutBottom_t;
    
    public DialogCutImage() {
        setTitle("Cut Image");
        setHeaderText("Cut edges of the image");
        getDialogPane().getButtonTypes().addAll(closeButton, ButtonType.CANCEL);
        init();
        setButtonActions();
        initModality(Modality.NONE);
    }
    
    private void init() {
        cutLeft_t = new TextField();
        cutLeft_t.setPromptText("left pixel:0");
        HBox leftCut = new HBox(cutLeft_b, cutLeft_t);
        leftCut.setSpacing(5);
        leftCut.setAlignment(Pos.BASELINE_RIGHT);
        
        cutTop_t = new TextField();
        cutTop_t.setPromptText("top pixel:0");
        HBox topCut = new HBox(cutTop_b, cutTop_t);
        topCut.setSpacing(5);
        topCut.setAlignment(Pos.BASELINE_RIGHT);
        
        cutRight_t = new TextField();
        cutRight_t.setPromptText("right pixel:0");
        HBox rightCut = new HBox(cutRight_b, cutRight_t);
        rightCut.setSpacing(5);
        rightCut.setAlignment(Pos.BASELINE_RIGHT);
        
        cutBottom_t = new TextField();
        cutBottom_t.setPromptText("left pixel:0");
        HBox rottomCut = new HBox(cutBottom_b, cutBottom_t);
        rottomCut.setSpacing(5);
        rottomCut.setAlignment(Pos.BASELINE_RIGHT);
        
        VBox vBox = new VBox();
        vBox.getChildren().add(leftCut);
        vBox.getChildren().add(rightCut);
        vBox.getChildren().add(topCut);
        vBox.getChildren().add(rottomCut);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.BASELINE_RIGHT);
        
        vBox.setSpacing(5);
        
        getDialogPane().setContent(vBox);
        
        Platform.runLater(() -> leftCut.requestFocus());
    }
    
    private void setButtonActions() {
        cutLeft_b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cutLeft_t.setText("Click on 'Read In' picture");
                ((ControllerWithImageInteraction) StaticReferences.controller).getsubControllerImageTools(null).setListener(Cut_Left);           
            }
        });
        cutRight_b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cutRight_t.setText("Click on 'Read In' picture");
                ((ControllerWithImageInteraction) StaticReferences.controller).getsubControllerImageTools(null).setListener(Cut_Right);           
            }
        });
        
        cutTop_b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cutTop_t.setText("Click on 'Read In' picture");
                ((ControllerWithImageInteraction) StaticReferences.controller).getsubControllerImageTools(null).setListener(Cut_Top);           
            }
        });
        
        cutBottom_b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cutBottom_t.setText("Click on 'Read In' picture");
                ((ControllerWithImageInteraction) StaticReferences.controller).getsubControllerImageTools(null).setListener(Cut_Bottom);           
            }
        });
        
    }
    
    public void setFieldText(String text, FieldNames field){
        if(field.equals(FieldNames.LEFT)){
            cutLeft_t.setText(text);
        }
        if(field.equals(FieldNames.TOP)){
            cutTop_t.setText(text);
        }
        if(field.equals(FieldNames.RIGHT)){
            cutRight_t.setText(text);
        }
        if(field.equals(FieldNames.BOTTOM)){
            cutBottom_t.setText(text);
        }
    }
    
    public enum FieldNames {
        LEFT, RIGHT, TOP, BOTTOM
    }
}

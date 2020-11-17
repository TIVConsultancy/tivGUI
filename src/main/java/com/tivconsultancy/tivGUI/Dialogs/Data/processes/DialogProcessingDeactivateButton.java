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
package com.tivconsultancy.tivGUI.Dialogs.Data.processes;

import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.ThreadControl;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class DialogProcessingDeactivateButton extends Dialog {

    ButtonType stopB = new ButtonType("Stop", ButtonData.CANCEL_CLOSE);
    BooleanProperty ableToStop = new SimpleBooleanProperty();
    Label label = new Label();

    public DialogProcessingDeactivateButton(String sState1) {
        setTitle("Processing");
        ableToStop.setValue(true);
//        setHeaderText("Executing current task");     
        getDialogPane().getButtonTypes().addAll(stopB);
        init();
        label.setText(sState1);
        initModality(Modality.NONE);
        setResultConverter(dialogButton -> {
            try {
                if (StaticReferences.controller instanceof ThreadControl) {
                    ((ThreadControl) StaticReferences.controller).getRunningThread().stop();
                }
                StaticReferences.controller.releaseUIAfterProceess();

                return null;
            } catch (Exception e) {
            }
            return null;

        });
        getDialogPane().lookupButton(stopB).disableProperty().bind(ableToStop);
    }

    private void init() {

        label.setText("processing");

        ProgressBar pb = new ProgressBar();
        pb.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

        ProgressIndicator pin = new ProgressIndicator();
        pin.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(label, pb);

        getDialogPane().setContent(hb);
    }

    public void activateStopButton(String sState2) {
        ableToStop.setValue(false);
        label.setText(sState2);
    }
}

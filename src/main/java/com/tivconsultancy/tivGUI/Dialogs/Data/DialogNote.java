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

package com.tivconsultancy.tivGUI.Dialogs.Data;


import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DialogNote extends Dialog<Map<Enum, String>> {
  private ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);

  public DialogNote(String note) {
    setTitle("Notification");
    setHeaderText(note);

    getDialogPane().getButtonTypes().addAll(okButton);
    
  }
  
}

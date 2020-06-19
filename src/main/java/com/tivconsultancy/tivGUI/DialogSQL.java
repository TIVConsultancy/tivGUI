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

package com.tivconsultancy.tivGUI;


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

public class DialogSQL extends Dialog<Map<Enum, String>> {
  private TextField hostName;
  private TextField databaseName;
  private TextField userName;
  private PasswordField passwordField;

  public DialogSQL() {
    setTitle("SQl Connection");
    setHeaderText("Connection to SQL Database");

    ButtonType connectButton = new ButtonType("Connect", ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().addAll(connectButton, ButtonType.CANCEL);

    Label hostNameL = new Label("Host: ");
    hostName = new TextField();
    hostName.setText("localhost");
    hostName.setPromptText("host:localhost");
    HBox hostNameB = new HBox(hostNameL, hostName);
    hostNameB.setSpacing(5);
    hostNameB.setAlignment(Pos.BASELINE_RIGHT);
    
    Label databaseNameL = new Label("Datbase: ");
    databaseName = new TextField();
    databaseName.setText("localpiv");
    databaseName.setPromptText("database:localpiv");
    HBox databaseNameB = new HBox(databaseNameL, databaseName);
    databaseNameB.setSpacing(5);
    databaseNameB.setAlignment(Pos.BASELINE_RIGHT);
    
    Label userNameL = new Label("User: ");
    userName = new TextField();
    userName.setText("adminpiv");
    userName.setPromptText("user:adminpiv"); 
    HBox userNameB = new HBox(userNameL, userName);
    userNameB.setSpacing(5);
    userNameB.setAlignment(Pos.BASELINE_RIGHT);
    
    Label passwordL = new Label("Password: ");
    passwordField = new PasswordField();
    passwordField.setText("default");
    passwordField.setPromptText("password:default");
    HBox passwordB = new HBox(passwordL, passwordField);
    passwordB.setSpacing(5);
    passwordB.setAlignment(Pos.BASELINE_RIGHT);

    VBox vBox = new VBox();
    vBox.getChildren().add(hostNameB);
    vBox.getChildren().add(databaseNameB);
    vBox.getChildren().add(userNameB);
    vBox.getChildren().add(passwordB);
    vBox.setPadding(new Insets(20));
    vBox.setAlignment(Pos.BASELINE_RIGHT);

    vBox.setSpacing(5);

    getDialogPane().setContent(vBox);

    Platform.runLater(() -> hostName.requestFocus());

    setResultConverter(dialogButton -> {
      if (dialogButton == connectButton) {
          Map<Enum, String> entries = new HashMap<>();
          entries.put(fieldNames.hostname, hostName.getText());
          entries.put(fieldNames.user, userName.getText());
          entries.put(fieldNames.database, databaseName.getText());
          entries.put(fieldNames.password, passwordField.getText());
        return entries;
      }
      return null;
    });
  }
  
  public enum fieldNames{
      hostname, database, user, password
  }

  public PasswordField getPasswordField() {
    return passwordField;
  }
}

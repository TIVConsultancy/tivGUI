/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.tables;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class tivSettingsDetails extends TableView {

    protected TableColumn tableColumnSettingsName;
    protected TableColumn tableColumnValue;

    public tivSettingsDetails() {

        tableColumnSettingsName = new TableColumn();
        tableColumnValue = new TableColumn();

        tableColumnSettingsName.setPrefWidth(143.0);
        tableColumnSettingsName.setText("Name");        

        tableColumnValue.setPrefWidth(105.0);
        tableColumnValue.setText("Value");

        setPrefHeight(198.0);
        setPrefWidth(248.0);
        getColumns().add(tableColumnSettingsName);
        getColumns().add(tableColumnValue);
    }

}

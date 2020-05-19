/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.logging;

import javafx.scene.control.TextArea;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class LogTextArea extends TextArea {

    private static LogTextArea logArea = null;

    private LogTextArea() {
        super();
    }

    public static LogTextArea getInstance() {
        if (logArea == null) {
            logArea = new LogTextArea();
        }
        return logArea;
    }

    public void showInfo(String msg) {
        this.appendText(msg);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface ControllerWithImageInteraction extends ControllerUI {
    public void clickOnImage(int i, int j, MouseEvent evt, String ident);
    public void buttonPressed(KeyEvent evt, String ident);
    public subControllerImageTools getsubControllerImageTools(String ident);
}

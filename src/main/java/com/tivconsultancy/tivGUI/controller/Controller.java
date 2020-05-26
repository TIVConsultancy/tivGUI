/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.controller;

import com.tivconsultancy.opentiv.datamodels.IndexedResults;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.tivGUI.MainFrame;
import java.io.File;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Window;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface Controller {               
    
    public void setScene(Scene s);
    public void startNewMethod(Method newMethod);
    public void setGUI(MainFrame mainFrame);
    public void setSelectedFile(File f);
    
    public IndexedResults getOverTimeResults();
    public List<String> getHints(String name);
    public List<File> getInputFiles(String name);
    public Method getCurrentMethod();
    public Window getMainWindows();
    public MainFrame getMainFrame();
    public File getCurrentFileSelected();
    
    public subControllerViews getViewController(String ident);
    public subControllerPlots getViewControllerPlots(String ident);
    public subControllerMenu getMenuController(String ident);
    public subControllerLogging getLogController(String ident);
    
    public void importSettings(File f);
    public void exportSettings(File f);
    
    public void loadSession(File f);
    public void startNewSession(File f);

    public void runCurrentStep();
    public void run();
    
    
}

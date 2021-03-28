/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.controller;

import com.tivconsultancy.opentiv.datamodels.Refreshable;
import com.tivconsultancy.opentiv.highlevel.controller.Controller;
import com.tivconsultancy.tivGUI.MainFrame;
import com.tivconsultancy.tivGUI.StaticReferences;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface ControllerUI extends Controller {               
    
    public void setScene(Scene s);
    public void setGUI(MainFrame mainFrame);
    
    public void addObjectToRefresh(Refreshable ref);

    public Window getMainWindows();
    public MainFrame getMainFrame();    
    
    public subControllerViews getViewController(String ident);
    public subControllerPlots getViewControllerPlots(String ident);
    public subControllerMenu getMenuController(String ident);
    public subControllerLogging getLogController(String ident);
    public subControllerSQL getSQLControler(String ident);
    
    public void refreshSettings();
    
    public Dialog getDialog(Enum ident);
    default public List<Dialog> getAllDialog(){
        StaticReferences.getlog().log(Level.WARNING, "Method not implemented, empty returned");
        return new ArrayList<>();
    }
    public void setDialog(Enum ident, Dialog dialogBox);
    
    public void blockUIForProceess();
    public void releaseUIAfterProceess();
    
    public void storeTempData();
    
    public static enum DialogNames_Default{
        SQL, CUT, PROCESS
    }    
            
            

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.controller;

import com.tivconsultancy.opentiv.datamodels.IndexedResults;
import com.tivconsultancy.opentiv.helpfunctions.settings.SettingObject;
import com.tivconsultancy.opentiv.helpfunctions.settings.Settings;
import com.tivconsultancy.opentiv.helpfunctions.settings.SettingsCluster;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import com.tivconsultancy.tivGUI.MainFrame;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.startup.Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.Scene;
import javafx.stage.Window;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public abstract class BasicController implements Controller{
    
    protected Method currentMethod;
    protected subControllerViews subViews;
    protected subControllerPlots subPlots;
    protected subControllerMenu subMenu;
    protected subControllerLogging subLog;

    protected Settings hints;
    protected List<String> excludeHints;

    protected Window main;
    protected MainFrame mainFrame;
    protected Scene scene;

    protected Database data;
    
    protected File selectedFile = null;
    
    protected void createHints(Method newMethod) {
        hints = new Settings() {
            @Override
            public String getType() {
                return "hints";
            }

            @Override
            public void buildClusters() {
            }
        };
        for (Protocol p : newMethod.getProtocols()) {
            for (SettingsCluster c : p.lsClusters) {
                for (SettingObject o : c.getSettings()) {
                    if(o.ident.equals(SettingObject.SettingsType.Boolean)){
                        hints.addSettingsObject(o);
                        hints.addSettingsObject(new SettingObject(o.getName(), !((Boolean) o.sValue), SettingObject.SettingsType.Boolean));
                    }else{
                        hints.addSettingsObject(o);
                    }                    
                }
            }
        }
    }
    
    @Override
    public Method getCurrentMethod() {
        return currentMethod;
    }

    @Override
    public subControllerViews getViewController(String ident) {
        return subViews;
    }

    @Override
    public IndexedResults getOverTimeResults() {
        return data.getIndexedResults();
    }

    @Override
    public subControllerPlots getViewControllerPlots(String ident) {
        return subPlots;
    }

    @Override
    public subControllerMenu getMenuController(String ident) {
        return subMenu;
    }

    @Override
    public Window getMainWindows() {
        return main;
    }

    @Override
    public void setScene(Scene s) {
        this.scene = s;
        this.main = s.getWindow();
    }

    @Override
    public subControllerLogging getLogController(String ident) {
        return subLog;
    }
    
    @Override
    public void importSettings(File loadFile) {
        List<SettingObject> loSettings = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(loadFile); ObjectInputStream ois = new ObjectInputStream(fis);) {
            loSettings = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            HashSet loHash = new HashSet(loSettings);
            loSettings = new ArrayList<>(loHash);

            for (Protocol p : currentMethod.getProtocols()) {
                p.setReadIn(loSettings);
            }
        } catch (IOException ioe) {
            StaticReferences.getlog().log(Level.SEVERE, "Cannot load settings file", ioe);
        } catch (ClassNotFoundException c) {
            StaticReferences.getlog().log(Level.SEVERE, "Settings file corrupt or old version", c);
        }

    }

    @Override
    public void exportSettings(File saveFile) {
        List<SettingObject> allSettings = new ArrayList<>();
        for (Protocol p : currentMethod.getProtocols()) {
            allSettings.addAll(p.getAllSettings());
        }

        try (FileOutputStream fos = new FileOutputStream(saveFile); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(allSettings);
        } catch (Exception ioe) {
            StaticReferences.getlog().log(Level.SEVERE, "Cannot save settings", ioe);
        }
    }
    
    @Override
    public void setGUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    @Override
    public File getCurrentFileSelected() {
        if (selectedFile == null) {
            return mainFrame.getSelectedFile();
        }
        return selectedFile;
    }
    
    @Override
    public List<String> getHints(String s) {        
        if (hints == null || (excludeHints != null && excludeHints.contains(s))) {
            return new ArrayList<>();
        }
        List<Object> lo = hints.getALLSettingsValues(s);
        List<String> ls = new ArrayList<>();
        for (Object o : lo) {
            ls.add(o.toString());
        }
        return ls;
    }
    
}

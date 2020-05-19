/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.tivGUI.controller.BasicController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpController extends BasicController {    

    public StartUpController() {
        this.main = null;
        initDatabase();
        currentMethod = new StartUpMethod();
        createHints(currentMethod);
        setExcludeHints();
        initSubControllers();
    }

    private void initDatabase() {
        data = new Database();
    }

    private void initSubControllers() {
        subViews = new StartUpSubControllerViews(this);
        subPlots = new StartUpSubControllerPlots();
        subMenu = new StartUpSubControllerMenu();
        subLog = new StartUpSubControllerLog();
    }

    @Override
    public void startNewMethod(Method newMethod) {
        currentMethod = newMethod;
        initDatabase();
    }        

    private void setExcludeHints() {
        excludeHints = new ArrayList<>();
        this.excludeHints.add("SartUp_NoEdit");
    }        

    @Override
    public void loadSession(File f) {
        StaticReferences.getlog().log(Level.SEVERE, "Load Session not implemented");
    }

    @Override
    public void startNewSession(File f) {
        startNewMethod(new StartUpMethod());
    }

    @Override
    public void runCurrentStep() {
        int currentStep = 0;
        new Thread() {
            @Override
            public void run() {
                try {
                    getCurrentMethod().run();
                    data.set1DRes(currentStep, getCurrentMethod().get1DResults());
                    subViews.update();
                } catch (Exception ex) {
                    StaticReferences.getlog().log(Level.SEVERE, "Unable to run : " + ex.getMessage(), ex);
                }
            }
        }.start();
    }

    @Override
    public void run() {
        new Thread() {
            @Override
            public void run() {
                timeline:
                for (int i = 0; i < 10; i++) {
                    try {
                        getCurrentMethod().run();
                        data.set1DRes(i, getCurrentMethod().get1DResults());
                        subViews.update();
                    } catch (Exception ex) {
                        StaticReferences.getlog().log(Level.SEVERE, "Unable to run : " + ex.getMessage(), ex);
                    }
                }
            }
        }.start();
    }

    

    @Override
    public List<File> getInputFiles(String name) {
        File fFolder = new File("D:\\ThomasBubsize\\Pos0\\3slpm\\_0");
        List<File> ReadInFile = new ArrayList<>();
        for (File f : fFolder.listFiles()) {
            if (f.isDirectory()) {
                continue;
            }
            ReadInFile.add(f);
        }
        return ReadInFile;
    }   

    @Override
    public void setSelectedFile(File f) {
        this.selectedFile = f;
        this.getCurrentMethod().setFiles(new File[]{f});
        try {            
            getCurrentMethod().readInFileForView(f);            
        } catch (Exception ex) {
            Logger.getLogger(StartUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subViews.update();
    }

}

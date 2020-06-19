/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.datamodels.overtime.DatabaseRAM;
import com.tivconsultancy.opentiv.datamodels.Refreshable;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.opentiv.highlevel.protocols.NameSpaceProtocolResults1D;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import com.tivconsultancy.opentiv.datamodels.Result1D;
import com.tivconsultancy.opentiv.datamodels.Results1DPlotAble;
import com.tivconsultancy.tivGUI.controller.BasicController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.tivconsultancy.opentiv.datamodels.overtime.DataBaseEntry;
import com.tivconsultancy.opentiv.datamodels.overtime.Database;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpController extends BasicController {

    protected Result1D results1D;

    public StartUpController() {
        this.main = null;
        initDatabase();
        currentMethod = new StartUpMethod();
        createHints(currentMethod);
        setExcludeHints();
        initSubControllers();
    }

    private void initDatabase() {
        data = new DatabaseRAM();
    }

    private void initSubControllers() {
        subViews = new StartUpSubControllerViews(this);
        subPlots = new StartUpSubControllerPlots();
        subMenu = new StartUpSubControllerMenu();
        subLog = new StartUpSubControllerLog();
        subSQL = new StartUpSubControllerSQL();
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
                    data.setRes(currentStep+"", (DataBaseEntry) get1DResults());
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
                        data.setRes(i+"", (DataBaseEntry) get1DResults());
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
        System.out.println(System.getProperty("user.home") + File.separatorChar + "My Documents");
        File fFolder = new File(System.getProperty("user.home") + File.separatorChar + "Pictures");
//        File fFolder = new File("D:\\ThomasBubsize\\Pos0\\3slpm\\_0");
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

    @Override
    public Results1DPlotAble get1DResults() {
        return results1D;
    }

    private int getSelecedIndex() {
        return 0;
    }
    
    @Override
    public void startNewIndexStep() {
        results1D = new Result1D(getSelecedIndex());
        for (Protocol pro : getCurrentMethod().getProtocols()) {
            for (NameSpaceProtocolResults1D e : pro.get1DResultsNames()) {
                results1D.addResult(e.toString(), Double.NaN);
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(StartUpMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Database getDataBase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import delete.com.tivconsultancy.opentiv.devgui.main.ImagePath;
import com.tivconsultancy.opentiv.helpfunctions.strings.StringWorker;
import com.tivconsultancy.opentiv.highlevel.methods.Method;
import com.tivconsultancy.opentiv.highlevel.protocols.NameSpaceProtocolResults1D;
import com.tivconsultancy.opentiv.highlevel.protocols.Prot_PreProcessor;
import com.tivconsultancy.opentiv.highlevel.protocols.Prot_ReadIMGFiles;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import com.tivconsultancy.opentiv.datamodels.Result1D;
import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import com.tivconsultancy.tivGUI.StaticReferences;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpMethod implements Method {

    private File currentFile = null;

    LookUp<Protocol> methods;

    public StartUpMethod() {
        initProtocols();
    }

    private void initProtocols() {
        methods = new LookUp<>();
        methods.add(new NameObject<>("startup", new StartUpProtocol()));
        methods.add(new NameObject<>("read", new Prot_ReadIMGFiles()));
        methods.add(new NameObject<>("preproc", new Prot_PreProcessor()));
    }

//    private void startNewIndexStep() {
//        StaticReferences.controller results1D = new Result1D();
//        for (Protocol pro : getProtocols()) {
//            for (NameSpaceProtocolResults1D e : pro.get1DResultsNames()) {
//                results1D.addResult(e.toString(), Double.NaN);
//            }
//        }
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(StartUpMethod.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public List<ImagePath> getInputImages() {
        return new ArrayList<>();
    }

    @Override
    public List<Protocol> getProtocols() {
        return methods.getValues();
    }    

    @Override
    public void readInFileForView(File f) throws Exception {
        List<String> nameOfFileSep = StringWorker.cutElements2(f.getName(), ".");
        if (nameOfFileSep.isEmpty()) {
            return;
        }

        if (nameOfFileSep.contains("png") || nameOfFileSep.contains("jpg") || nameOfFileSep.contains("jpeg") || nameOfFileSep.contains("bmp")) {
            for (Protocol p : getProtocols()) {
                try {
                    if (p instanceof Prot_ReadIMGFiles) {
                        p.run(f);
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        }
    }

    public void setFiles(File[] f) {
        currentFile = f[0];
    }

    @Override
    public void run() throws Exception {
        for (Protocol p : getProtocols()) {
            try {
                getProtocol("read").run(currentFile);
                getProtocol("preproc").run(getProtocol("read").getResults());
            } catch (Exception ex) {
                throw ex;
            }
            for (NameSpaceProtocolResults1D e : p.get1DResultsNames()) {
                StaticReferences.controller.get1DResults().setResult(e.toString(), p.getOverTimesResult(e));
            }
        }
    }

    @Override
    public Protocol getProtocol(String ident) {
        return methods.get(ident);
    }
}

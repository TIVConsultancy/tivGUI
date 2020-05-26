/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.subControllerLogging;
import com.tivconsultancy.tivGUI.logging.Windowhandler;
import java.util.logging.Logger;

/**
 *
 * @author Thomas Ziegenhein
 */
public class StartUpSubControllerLog implements subControllerLogging {

    public StartUpSubControllerLog(){
        StaticReferences.getlog().addHandler(Windowhandler.getInstance());
    }
    
    @Override
    public void connectToLog(Logger log) {
        log.addHandler(Windowhandler.getInstance());
    }
 
    
    
}

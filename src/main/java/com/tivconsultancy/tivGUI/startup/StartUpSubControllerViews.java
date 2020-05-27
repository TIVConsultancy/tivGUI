/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import com.tivconsultancy.tivGUI.controller.subControllerViews;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import com.tivconsultancy.opentiv.imageproc.primitives.ImageInt;
import com.tivconsultancy.tivGUI.viewer.ViewContainerImagesFit;
import com.tivconsultancy.tivGUI.viewer.ViewerContainer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import com.tivconsultancy.tivGUI.controller.ControllerUI;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpSubControllerViews implements subControllerViews {

    public ControllerUI main;
    private LookUp<ViewerContainer> lookupTable;

    public StartUpSubControllerViews(ControllerUI main) {
        this.main = main;
        buildLookUp();
    }

    private void buildLookUp() {
        lookupTable = new LookUp<>();
        for (Protocol p : main.getCurrentMethod().getProtocols()) {
            for ( Object s : p.getIdentForViews()) {
                lookupTable.add(new NameObject<>(s.toString(), new ViewContainerImagesFit(p.getView(s.toString()), s.toString()) {
                                         }));
            }
        }
    }

    @Override
    public Node getView(String ident) {
        if (lookupTable.get(ident) != null) {
            return lookupTable.get(ident).getNode();
        }
        return new Label("Reference not found: " + ident);
    }
    
    @Override
    public ViewerContainer getViewContainer(String ident) {
        if (lookupTable.get(ident) != null) {
            return lookupTable.get(ident);
        }
        return ViewerContainer.getDefault("Cannot find label: " + ident);
    }

    @Override
    public List<String> getIdentForViews() {
        List<String> output = new ArrayList<>();
        if (main == null) {
            return output;
        }
        for (Protocol p : main.getCurrentMethod().getProtocols()) {
            output.addAll(p.getIdentForViews());
        }
        return output;
    }

    @Override
    public void update() {
        for (Protocol p : main.getCurrentMethod().getProtocols()) {            
            for (Object s : p.getIdentForViews()) {
                if(p.getView(s.toString()) == null){
                    lookupTable.get(s.toString()).update(new ImageInt(50, 50, 0).getBuffImage());
                }else{
                    lookupTable.get(s.toString()).update(p.getView(s.toString()));
                }
                
            }
        }
    }

}

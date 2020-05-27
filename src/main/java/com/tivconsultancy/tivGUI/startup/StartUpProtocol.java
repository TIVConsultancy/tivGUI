/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.helpfunctions.settings.FactorySettingsCluster;
import com.tivconsultancy.opentiv.helpfunctions.settings.SettingObject;
import com.tivconsultancy.opentiv.imageproc.primitives.ImageInt;
import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import com.tivconsultancy.opentiv.highlevel.protocols.NameSpaceProtocolResults1D;
import com.tivconsultancy.opentiv.highlevel.protocols.Protocol;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpProtocol extends Protocol {    
    
    protected LookUp<BufferedImage> outPutImages;
    
    public StartUpProtocol() {
        buildLookUp();
        initSettings();
    }

    private void buildLookUp() {
        outPutImages = new LookUp<>();
        outPutImages.add(new NameObject<>("StartUp1", new ImageInt(50, 50, 0.0).getBuffImage()));
        outPutImages.add(new NameObject<>("StartUp2", new ImageInt(50, 50, 0.0).getBuffImage()));
    }    
    
    private void initSettings(){
        this.loSettings.clear();
        this.loSettings.add(new SettingObject("Editable Settings", "StartUp_Edit", "EditMe",
                                              SettingObject.SettingsType.String));
        this.loSettings.add(new SettingObject("Non-Editable Settings", "SartUp_NoEdit", "CannotBeEdited",
                                              SettingObject.SettingsType.String));
        buildClusters();
    }

    @Override
    public void buildClusters() {
        lsClusters.clear();
        lsClusters.add(
                FactorySettingsCluster.getStandardCluster("Cluster For Settings",
                                                          new String[]{"StartUp_Edit",
                                                              "SartUp_NoEdit"},
                                                          "Description for this cluster",
                                                          this));
    }
    

    @Override
    public String getType() {
        return "StartUp";
    }

    @Override
    public List<String> getIdentForViews() {
        return outPutImages.getNames();
    }

    @Override
    public BufferedImage getView(String identFromViewer) {
        return outPutImages.get(identFromViewer);
    }

    @Override
    public NameSpaceProtocolResults1D[] get1DResultsNames() {
        return NameSpaceProtocol.class.getEnumConstants();
    }

    @Override
    public Double getOverTimesResult(NameSpaceProtocolResults1D ident) {
        if(NameSpaceProtocol.Result1.equals(ident)){
            return Math.random();
        }
        if(NameSpaceProtocol.Result12.equals(ident)){
            return Math.random()+1.0;
        }
        return null;
    }

    @Override
    public void run(Object... input) {        
    }

    @Override
    public Object[] getResults() {
        return new Object[]{new ImageInt(50, 50, 0.0)};
    }

    @Override
    public void setImage(BufferedImage bi) {
        for(String s : getIdentForViews()){
            outPutImages.set(s, bi);
        } 
    }
    
    private enum NameSpaceProtocol implements NameSpaceProtocolResults1D{
       Result1, Result12
    }

}

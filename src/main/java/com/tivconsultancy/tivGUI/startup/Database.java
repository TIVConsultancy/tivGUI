/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.datamodels.IndexedResults;
import com.tivconsultancy.opentiv.highlevel.protocols.Result1D;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class Database {
    protected IndexedResults overTime1DResuls;

    
    public Database(){
        overTime1DResuls = new IndexedResults();
    }
        
    public IndexedResults getIndexedResults(){
        return overTime1DResuls;
    }
    
    public void set1DRes(int iStep, Result1D res){
        overTime1DResuls.replaceOrAdd(iStep, res);
    }
    
    public Result1D get1DRes(int iStep){
        return overTime1DResuls.get(iStep);
    }
    
}

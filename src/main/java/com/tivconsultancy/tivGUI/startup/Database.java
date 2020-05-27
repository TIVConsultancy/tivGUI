/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.datamodels.IndexableResults;
import com.tivconsultancy.opentiv.datamodels.IndexedResults;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 * @param <T>
 */
public class Database<T extends IndexableResults> {
    protected IndexedResults<T> overTime1DResuls;
    
    public Database(){
        overTime1DResuls = new IndexedResults<>();
    }

    public IndexedResults getIndexedResults(){
        return overTime1DResuls;
    }
    
    public void setRes(int iStep, T res){
        overTime1DResuls.replaceOrAdd(iStep, res);
    }
    
    public T getRes(int iStep){
        return overTime1DResuls.get(iStep);
    }
    
}

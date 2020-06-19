/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.trees.treecells.contentclasses;

import com.tivconsultancy.opentiv.helpfunctions.settings.SettingObject;
import com.tivconsultancy.tivGUI.StaticReferences;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 * @param <Z>
 */
public class TreeContentClass {

    private String display;
    private final String name;
    protected String value;
    private final Object example;
    private Object content;
    private final boolean dummyEntry;
    public String shortDescription;
    List<String> hints;

    public TreeContentClass(Object content, String display, String name, String value, Object example) {
        this.content = content;
        this.display = display;
        this.name = name;
        this.value = value;
        this.example = example;
        hints = StaticReferences.controller.getHints(name);
        if (hints.isEmpty()) {
            this.dummyEntry = true;
            this.shortDescription = contentStatus.fixed.toString();
        } else {
            this.dummyEntry = false;
            this.shortDescription = "";
        }
    }

    public TreeContentClass(Object content, String display, String name, String value, Object example, boolean dummyEntry) {
        this.content = content;
        this.display = display;
        this.name = name;
        this.value = value;
        this.example = example;
        this.dummyEntry = dummyEntry;
        if (dummyEntry) {
            hints = new ArrayList<>();
            this.shortDescription = contentStatus.fixed.toString();
        } else {
            hints = StaticReferences.controller.getHints(name);
            this.shortDescription = "";
        }
    }

    public TreeContentClass(Object content, String display, String name, String value, Object example, boolean dummyEntry, String shortDescr) {
        this.content = content;
        this.display = display;
        this.name = name;
        this.value = value;
        this.example = example;
        this.dummyEntry = dummyEntry;
        this.shortDescription = shortDescr;
        if (dummyEntry) {
            hints = new ArrayList<>();
        } else {
            hints = StaticReferences.controller.getHints(name);
        }
    }

    public boolean isDummyEntry() {
        return dummyEntry;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getContent(){
        return content;
    }
    
    public Object getExampleContent() {
        return example;
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        return display;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getValue() {
        return value;
    }
    
    public List<String> getHints(){
        return hints;
    }
    
    public void updateHints(){
        if (dummyEntry) {
//            hints = new ArrayList<>();
//            this.shortDescription = contentStatus.fixed.toString();
        } else {
            hints = StaticReferences.controller.getHints(name);
            this.shortDescription = "";
        }
    }        

    @Override
    public String toString() {
//        if (dummyEntry) {
//            return display;
//        } 
        if (value.isEmpty()) {
            return display;
        }
        return display + " : " + value;
    }
    
    public void updateContent(String text){
        if(getContent() instanceof SettingObject){
            ((SettingObject) getContent()).setValue(text);
        }
    }
    
    private enum contentStatus{
        fixed
    }
}

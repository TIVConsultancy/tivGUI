/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.math.specials.LookUp;
import com.tivconsultancy.opentiv.math.specials.NameObject;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.subControllerMenu;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpSubControllerMenu implements subControllerMenu {

    LookUp<EventHandler<ActionEvent>> actionEvents;
    LookUp<List<String>> subMenuEntries;
    LookUp<ImageView> icons;
    List<String> mainMenu;

    public StartUpSubControllerMenu() {
        StaticReferences.initIcons(this);
        initMainItems();
        initMainEntries();
        initIcons();
        initActionEvents();
    }

    private void initMainItems() {
        mainMenu = new ArrayList<>();
        mainMenu.add(dictionary(MainItems.Session));
        mainMenu.add(dictionary(MainItems.Run));
    }

    private void initMainEntries() {
        subMenuEntries = new LookUp<>();
        List<String> SessionEntries = new ArrayList<>();
        SessionEntries.add(dictionary(MenuEntries.New));
        SessionEntries.add(dictionary(MenuEntries.Load));
        SessionEntries.add(dictionary(MenuEntries.ImportSettings));
        SessionEntries.add(dictionary(MenuEntries.ExportSettings));
        subMenuEntries.add(new NameObject<>(dictionary(MainItems.Session), SessionEntries));

        List<String> RunEntries = new ArrayList<>();
        RunEntries.add(dictionary(MenuEntries.OneStep));
        RunEntries.add(dictionary(MenuEntries.RunAll));
        subMenuEntries.add(new NameObject<>(dictionary(MainItems.Run), RunEntries));

    }

    private void initIcons() {
        icons = new LookUp<>();
        icons.add(new NameObject<>(dictionary(MenuEntries.New), StaticReferences.standardIcons.get("plus2.png")));
        icons.add(new NameObject<>(dictionary(MenuEntries.Load), StaticReferences.standardIcons.get("folderOpen2.png")));
        icons.add(new NameObject<>(dictionary(MenuEntries.ImportSettings), StaticReferences.standardIcons.get("export2.png")));
        icons.add(new NameObject<>(dictionary(MenuEntries.ExportSettings), StaticReferences.standardIcons.get("import2.png")));

        icons.add(new NameObject<>(dictionary(MenuEntries.OneStep), StaticReferences.standardIcons.get("walking.png")));
        icons.add(new NameObject<>(dictionary(MenuEntries.RunAll), StaticReferences.standardIcons.get("runningMult.png")));
    }

    private void initActionEvents() {
        actionEvents = new LookUp<>();
        EventHandler<ActionEvent> newSession = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();                
                fileChooser.setTitle("Start New Session");
                File selectedFile = fileChooser.showOpenDialog(StaticReferences.controller.getMainWindows());
                StaticReferences.controller.startNewSession(selectedFile);
            }
        };
        
        EventHandler<ActionEvent> loadSession = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();                
                fileChooser.setTitle("Load Session");
                File selectedFile = fileChooser.showOpenDialog(StaticReferences.controller.getMainWindows());
                StaticReferences.controller.loadSession(selectedFile);
            }
        };
        
        EventHandler<ActionEvent> importSettings = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();                
                fileChooser.setTitle("Import Settings");
                File selectedFile = fileChooser.showOpenDialog(StaticReferences.controller.getMainWindows());
                StaticReferences.controller.importSettings(selectedFile);
            }
        };
        
        EventHandler<ActionEvent> exportSettings = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();                
                fileChooser.setTitle("Export Settings");
                File selectedFile = fileChooser.showOpenDialog(StaticReferences.controller.getMainWindows());
                StaticReferences.controller.exportSettings(selectedFile);
            }
        };
        
        EventHandler<ActionEvent> runOneStep = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                StaticReferences.controller.runCurrentStep();
            }
        };
        
        EventHandler<ActionEvent> runAll = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                StaticReferences.controller.run();
            }
        };  
        
        actionEvents.add(new NameObject<>(dictionary(MenuEntries.New), newSession));
        actionEvents.add(new NameObject<>(dictionary(MenuEntries.Load), loadSession));
        actionEvents.add(new NameObject<>(dictionary(MenuEntries.ImportSettings), importSettings));
        actionEvents.add(new NameObject<>(dictionary(MenuEntries.ExportSettings), exportSettings));
        actionEvents.add(new NameObject<>(dictionary(MenuEntries.OneStep), runOneStep));
        actionEvents.add(new NameObject<>(dictionary(MenuEntries.RunAll), runAll));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMainItems() {
        return mainMenu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventHandler<ActionEvent> getActionEvent(String ident) {
        return actionEvents.get(ident);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMenuEntries(String ident) {
        return subMenuEntries.get(ident) == null ? new ArrayList<>() : subMenuEntries.get(ident);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getIcon(String ident) {
        return icons.get(ident);
    }

    private enum MainItems {
        Session, Run
    }

    private enum MenuEntries {
        New, Load, OneStep, RunAll, ImportSettings, ExportSettings
    }

    private String dictionary(Enum e) {
        if (e == MenuEntries.New) {
            return "New Session";
        }
        if (e == MenuEntries.Load) {
            return "Load Session";
        }
        if (e == MenuEntries.OneStep) {
            return "One Step";
        }
        if (e == MenuEntries.RunAll) {
            return "All";
        }
        return e.toString();
    }

    private Enum dictionary(String s) {
        if (s.equals("New Session")) {
            return MenuEntries.New;
        }
        for (Enum e : MainItems.values()) {
            if (e.toString().equals(e)) {
                return e;
            }
        }
        for (Enum e : MenuEntries.values()) {
            if (e.toString().equals(e)) {
                return e;
            }
        }
        return null;
    }

}

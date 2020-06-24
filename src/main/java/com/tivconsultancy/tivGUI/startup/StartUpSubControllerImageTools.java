/*
 * Copyright 2020 TIVConsultancy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.imageproc.primitives.ImagePointInt;
import com.tivconsultancy.tivGUI.Dialogs.Tools.DialogCutImage;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.ControllerUI;
import com.tivconsultancy.tivGUI.controller.subControllerImageTools;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.stage.Stage;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpSubControllerImageTools implements subControllerImageTools {

    ListenersImageTools sListener = null;
    List<ImagePointIntString> lpis = new ArrayList<>();

    @Override
    public void clickOnImage(int i, int j, String ident) {
        lpis.add(new ImagePointIntString(i, j, ident));
        performAction();
    }

    @Override
    public void setListener(Enum ident) {
        if (ident instanceof ListenersImageTools) {
            sListener = (ListenersImageTools) ident;
        }
    }

    private void performAction() {
        System.out.println(lpis.get(lpis.size() - 1).j);
        if (sListener != null && sListener.equals(ListenersImageTools.Cut_Left)) {
            int cutLeftValue = lpis.get(lpis.size() - 1).j;
            try {                
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("BcutxLeft", true);
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("cutxLeft", cutLeftValue);
                StaticReferences.controller.getMainFrame().startNewSettings();
            } catch (Exception e) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot set settings", new Throwable());
            }

            try {
                StaticReferences.controller.getCurrentMethod().runParts("preproc");
                StaticReferences.controller.getViewController(null).update();
            } catch (Exception ex) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot update image", ex);
            }

            ((DialogCutImage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT)).setFieldText(cutLeftValue+"", DialogCutImage.FieldNames.LEFT);
            Stage stage = (Stage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT).getDialogPane().getScene().getWindow();
            stage.toFront();
            sListener = null;

        }
        
        if (sListener != null && sListener.equals(ListenersImageTools.Cut_Right)) {
            int cutRightValue = lpis.get(lpis.size() - 1).j;
            try {                
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("BcutxRight", true);
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("cutxRight", cutRightValue);
                StaticReferences.controller.getMainFrame().startNewSettings();
            } catch (Exception e) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot set settings", new Throwable());
            }

            try {
                StaticReferences.controller.getCurrentMethod().runParts("preproc");
                StaticReferences.controller.getViewController(null).update();
            } catch (Exception ex) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot update image", ex);
            }

            ((DialogCutImage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT)).setFieldText(cutRightValue+"", DialogCutImage.FieldNames.RIGHT);
            Stage stage = (Stage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT).getDialogPane().getScene().getWindow();
            stage.toFront();
            sListener = null;
        }
        
        if (sListener != null && sListener.equals(ListenersImageTools.Cut_Top)) {
            int cutTopValue = lpis.get(lpis.size() - 1).i;
            try {                
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("BcutyTop", true);
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("cutyTop", cutTopValue);
                StaticReferences.controller.getMainFrame().startNewSettings();
            } catch (Exception e) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot set settings", new Throwable());
            }

            try {
                StaticReferences.controller.getCurrentMethod().runParts("preproc");
                StaticReferences.controller.getViewController(null).update();
            } catch (Exception ex) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot update image", ex);
            }

            ((DialogCutImage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT)).setFieldText(cutTopValue+"", DialogCutImage.FieldNames.TOP);
            Stage stage = (Stage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT).getDialogPane().getScene().getWindow();
            stage.toFront();
            sListener = null;
        }
        
        if (sListener != null && sListener.equals(ListenersImageTools.Cut_Bottom)) {
            int cutBottomValue = lpis.get(lpis.size() - 1).i;
            try {                
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("BcutyBottom", true);
                StaticReferences.controller.getCurrentMethod().getProtocol("preproc").setSettingsValue("cutyBottom", cutBottomValue);
                StaticReferences.controller.getMainFrame().startNewSettings();
            } catch (Exception e) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot set settings", new Throwable());
            }

            try {
                StaticReferences.controller.getCurrentMethod().runParts("preproc");
                StaticReferences.controller.getViewController(null).update();
            } catch (Exception ex) {
                StaticReferences.getlog().log(Level.SEVERE, "Cannot update image", ex);
            }

            ((DialogCutImage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT)).setFieldText(cutBottomValue+"", DialogCutImage.FieldNames.BOTTOM);
            Stage stage = (Stage) StaticReferences.controller.getDialog(ControllerUI.DialogNames_Default.CUT).getDialogPane().getScene().getWindow();
            stage.toFront();
            sListener = null;
        }
        
    }

    public enum ListenersImageTools {
        Cut_Left, Cut_Top, Cut_Right, Cut_Bottom
    }

    public class ImagePointIntString extends ImagePointInt {

        private static final long serialVersionUID = 471153592204546635L;
        String ident = "";

        public ImagePointIntString(int i, int j, String ident) {
            super(i, j);
        }

    }

}

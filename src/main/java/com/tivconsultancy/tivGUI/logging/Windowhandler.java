/*
 *  
 *   TECHNOBIS CONFIDENTIAL
 *  __________________
 *  
 *   [2020] Technobis 
 *   All Rights Reserved.
 *  
 *  All information contained herein is, and remains the property of Technobis.
 *  The intellectual and technical concepts contained herein are proprietary to
 *  Technobis and may be covered by U.S. and Foreign Patents, patents in 
 *  process, and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material is 
 *  strictly forbidden unless prior written permission is obtained from 
 *  Technobis.
 *  
 *  __________________
 *  
 *  This code is developed in the "Project1" between TIVConsultancy and 
 *  Technobis. All information herein are the sole property of Technobis 
 *  and are subject to a pending exclusive rights agreement.
 *  
 *  @Project Management Thomas Ziegenhein   ThomasZiegenhein@TIVConsultancy.com 
 *                                          +1 480 494 7254
 *                                          TIVConsultancy
 *                                          Tempe, Arizona
 *  @Project Management Stephan van Banning stephan.vanbanning@technobis.com 
 *                                          +31 (0)6 3434 7095
 *                                          Technobis
 *                                          Alkmaar,  The Netherlands
 */
package com.tivconsultancy.tivGUI.logging;

import com.tivconsultancy.tivGUI.StaticReferences;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class Windowhandler extends Handler {

    private LogTextArea window = null;
    private static Windowhandler handler = null;
    private static Level logLVL = Level.INFO;

    private Windowhandler() {
        LogManager manager = LogManager.getLogManager();
        String className = this.getClass().getName();
        String level = manager.getProperty(className + ".level");
        setLevel(Level.ALL);
//        setLevel(level != null ? Level.parse(level) : Level.INFO);
        if (window == null) {
               window = LogTextArea.getInstance(); 
            }
        setFormatter(new Formatter() {

            @Override
            public String format(LogRecord record) {
                String sLevel = record.getLevel().getName();
                String sMessage = record.getMessage();
                String sParameters = "";
                if (record.getParameters() != null) {
                    for (Object o : record.getParameters()) {
                        if (o != null) {
                            sParameters = sParameters + o.toString();
                        }

                    }
                }

                double time = 1.66667e-5 * (record.getMillis() - StaticReferences.startTime);

                if (record.getLevel().intValue() >= Level.ALL.intValue()) {
                    return String.format("%2.02f", time) + ": " + sLevel + ": " + sMessage + "\n" + "\t"
                            + "" + sParameters + "\n";
                }
                return null;
            }
        });
    }

    public LogTextArea getFrame() {
        return window;
    }

    public static synchronized Windowhandler getInstance() {
        if (handler == null) {
                handler = new Windowhandler();
            }
        return handler;
    }

    @Override
    public synchronized void publish(LogRecord record) {
        String message = null;
        if (!isLoggable(record)) {
            return;
        }
        message = getFormatter().format(record);
        if (message == null || message.isEmpty()) {
        } else {
            window.showInfo(message);
        }

    }

    public static void log(Level lvl, String msg, Throwable o) {

        StaticReferences.getlog().
                log(lvl,
                        msg);

//        if (o instanceof Exception && lvl.equals(Level.SEVERE)) {
//            Exception e = (Exception) o;
//            Logger.getLogger("tivGUI").
//                    log(lvl,
//                        "Please contact support and copy this output:" + "\n"
//                        + msg + "\n" + e.toString() + "\n"
//                        + Arrays.asList(e.getStackTrace())
//                                .stream()
//                                .map(Objects::toString)
//                                .collect(Collectors.joining("\n")),
//                        new Object[]{e.getLocalizedMessage()});
//        } else if (lvl.intValue() >= logLVL.intValue()) {
//            Logger.getLogger("tivGUI").
//                    log(lvl,
//                        msg,
//                        new Object[]{});
//        }
    }

    public void close() {
    }

    public void flush() {
    }
}

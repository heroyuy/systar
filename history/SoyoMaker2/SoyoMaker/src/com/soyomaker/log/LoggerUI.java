/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import javax.swing.JTabbedPane;

/**
 *
 * @author Administrator
 */
public class LoggerUI {

    private JTabbedPane jTabbedPane;

    private LoggerUI() {
    }
    private static LoggerUI instance = new LoggerUI();

    /**
     *
     * @return
     */
    public static LoggerUI getInstance() {
        return instance;
    }

    /**
     *
     * @param jTabbedPane
     */
    public void setLoggerContainer(JTabbedPane jTabbedPane) {
        if (jTabbedPane == null) {
            throw new IllegalArgumentException("null JTabbedPane");
        }
        this.jTabbedPane = jTabbedPane;
    }

    /**
     *
     * @param ilogger
     */
    public void addLogger(ILoggerUI ilogger) {
        if (jTabbedPane == null) {
            throw new IllegalArgumentException("null JTabbedPane");
        }
        jTabbedPane.add(ilogger.getTitle(), ilogger.getTextComponentContainer());
    }

    /**
     *
     * @param ilogger
     */
    public void removeLogger(ILoggerUI ilogger) {
        if (jTabbedPane == null) {
            throw new IllegalArgumentException("null JTabbedPane");
        }
        jTabbedPane.remove(ilogger.getTextComponentContainer());
    }
}

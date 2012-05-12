/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import java.util.ArrayList;

/**
 * 日志打印机
 * @author like
 */
public class LogPrinter {

    private ArrayList<ILogDisplayer> logDiaplayers = new ArrayList<ILogDisplayer>();
    private ArrayList<LogPrinterListener> listeners = new ArrayList<LogPrinterListener>();
    private AbLogFormater logFormater;

    /**
     *
     * @param listener
     */
    public void addLogPrinterListener(LogPrinterListener listener) {
        listeners.add(listener);
    }

    /**
     *
     * @param listener
     */
    public void removeLogPrinterListener(LogPrinterListener listener) {
        listeners.remove(listener);
    }

    /**
     *
     * @param logDisplayer
     */
    public void addLogDisplayer(ILogDisplayer logDisplayer) {
        logDiaplayers.add(logDisplayer);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).logDisplayerAdded(logDisplayer);
        }
    }

    /**
     *
     * @param logDisplayer
     */
    public void removeLogDisplayer(ILogDisplayer logDisplayer) {
        logDiaplayers.remove(logDisplayer);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).logDisplayerRemoved(logDisplayer);
        }
    }

    /**
     *
     * @return
     */
    public AbLogFormater getLogFormater() {
        return logFormater;
    }

    /**
     *
     * @param logFormater
     */
    public void setLogFormater(AbLogFormater logFormater) {
        this.logFormater = logFormater;
    }

    /**
     *
     * @param content
     */
    public void d(Object content) {
        Log log = new Log();
        log.setContent(content);
        LogAttribute logAttribute = new LogAttribute();
        logAttribute.setLevel(LogAttribute.D);
        log.setAttribute(logAttribute);
        logFormater.format(log);
        for (int i = 0; i < logDiaplayers.size(); i++) {
            logDiaplayers.get(i).displayLog(log);
        }
    }

    /**
     *
     * @param content
     */
    public void v(Object content) {
        Log log = new Log();
        log.setContent(content);
        LogAttribute logAttribute = new LogAttribute();
        logAttribute.setLevel(LogAttribute.V);
        log.setAttribute(logAttribute);
        logFormater.format(log);
        for (int i = 0; i < logDiaplayers.size(); i++) {
            logDiaplayers.get(i).displayLog(log);
        }
    }

    /**
     *
     * @param content
     */
    public void w(Object content) {
        Log log = new Log();
        log.setContent(content);
        LogAttribute logAttribute = new LogAttribute();
        logAttribute.setLevel(LogAttribute.W);
        log.setAttribute(logAttribute);
        logFormater.format(log);
        for (int i = 0; i < logDiaplayers.size(); i++) {
            logDiaplayers.get(i).displayLog(log);
        }
    }

    /**
     *
     * @param content
     */
    public void e(Object content) {
        Log log = new Log();
        log.setContent(content);
        LogAttribute logAttribute = new LogAttribute();
        logAttribute.setLevel(LogAttribute.E);
        log.setAttribute(logAttribute);
        logFormater.format(log);
        for (int i = 0; i < logDiaplayers.size(); i++) {
            logDiaplayers.get(i).displayLog(log);
        }
    }
}

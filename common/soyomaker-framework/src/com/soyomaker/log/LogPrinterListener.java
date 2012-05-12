/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 *
 * @author Administrator
 */
public interface LogPrinterListener {

    /**
     *
     * @param displayer
     */
    public void logDisplayerAdded(ILogDisplayer displayer);

    /**
     *
     * @param displayer
     */
    public void logDisplayerRemoved(ILogDisplayer displayer);
}

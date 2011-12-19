/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import javax.swing.JComponent;

/**
 *
 * @author Administrator
 */
public interface ILoggerUI {

    /**
     *
     * @param content
     */
    public void d(Object content);

    /**
     *
     * @param content
     */
    public void v(Object content);

    /**
     *
     * @param content
     */
    public void w(Object content);

    /**
     *
     * @param content
     */
    public void e(Object content);

    /**
     *
     * @return
     */
    public String getTitle();

    /**
     *
     * @return
     */
    public JComponent getTextComponentContainer();
}

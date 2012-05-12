/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 * 日志显示器
 * @author like
 */
public interface ILogDisplayer {

    /**
     * 调用此方法将在日志显示器上显示日志
     * @param log
     */
    public void displayLog(Log log);
}

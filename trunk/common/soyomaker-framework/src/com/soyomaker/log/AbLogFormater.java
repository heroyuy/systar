/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 * 日志格式类
 * @author like
 */
public abstract class AbLogFormater {

    /**
     *
     */
    protected boolean printHead = true;
    /**
     *
     */
    protected boolean printTime = true;
    /**
     *
     */
    protected Log log;

    /**
     * 返回经过格式化后的日志内容
     * @param log
     * @return
     */
    public abstract Log format(Log log);

    /**
     *
     * @return
     */
    public Log getLog() {
        return log;
    }

    /**
     * 设置要格式的日志
     * @param log
     */
    public void setLog(Log log) {
        this.log = log;
    }

    /**
     *
     * @return
     */
    public boolean isPrintHead() {
        return printHead;
    }

    /**
     * 设置是否打印日志头，即<D> <V> <W> <E>
     * @param printHead
     */
    public void setPrintHead(boolean printHead) {
        this.printHead = printHead;
    }

    /**
     * 
     * @return
     */
    public boolean isPrintTime() {
        return printTime;
    }

    /**
     * 设置是否打印日志时间
     * @param printTime
     */
    public void setPrintTime(boolean printTime) {
        this.printTime = printTime;
    }
}

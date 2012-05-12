/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 * 日志参数
 * @author like
 */
public class LogAttribute {

    /**
     * 默认的显示参数为D参数
     */
    public final static int DEFAULT = 1;
    /**
     *
     */
    public final static int D = 1;
    /**
     *
     */
    public final static int V = 2;
    /**
     *
     */
    public final static int W = 3;
    /**
     *
     */
    public final static int E = 4;
    private int level = DEFAULT;

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置日志级别
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
        if (level < D || level > E) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     */
    public LogAttribute() {
    }

    /**
     *
     * @param level
     */
    public LogAttribute(int level) {
        this.level = level;
        if (level < D || level > E) {
            throw new IllegalArgumentException();
        }
    }
}

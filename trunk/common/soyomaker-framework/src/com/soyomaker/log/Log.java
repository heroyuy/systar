/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 * 日志类
 * @author like
 */
public class Log {

    private Object content;
    private LogAttribute attribute;

    /**
     *
     * @return
     */
    public Object getContent() {
        return content;
    }

    /**
     * 设置日志内容
     * @param content
     */
    public void setContent(Object content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public LogAttribute getAttribute() {
        return attribute;
    }

    /**
     * 设置日志属性
     * @param attribute
     */
    public void setAttribute(LogAttribute attribute) {
        this.attribute = attribute;
    }
}

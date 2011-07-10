/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app.event;

/**
 * 动作监听器
 * @author wp_g4
 */
public interface ActionListener<T> {

    /**
     * 当按钮被按下时调用
     * @param source 事件源，即被按下的按钮
     */
    public void actionPerformed(T source);
}

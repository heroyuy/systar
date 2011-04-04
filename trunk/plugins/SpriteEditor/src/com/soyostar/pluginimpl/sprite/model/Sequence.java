/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

/**
 *
 * @author Administrator
 */
public class Sequence {

    private Frame frame;
    private int delay;
    private String name = "新建序列";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
}

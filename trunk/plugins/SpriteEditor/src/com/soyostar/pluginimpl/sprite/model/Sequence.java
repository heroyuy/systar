/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.awt.Graphics;

/**
 *
 * @author Administrator
 */
public class Sequence {

    private Frame frame;
    private int delay = 100;
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

    public void paint(Graphics g, int x, int y, int zoom) {
        if (frame != null) {
            frame.paint(g, x, y, zoom);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.animation;

import java.util.ArrayList;

/**
 * 
 * @author Administrator
 */
public class Animation {

    private int index;//动画的序号，动画的唯一性标识
    private String name;//可选，动画的名称
    private ArrayList<Frame> frames;//帧序列

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

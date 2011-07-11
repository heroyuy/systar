/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.animation;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Frame {

    int delay;//单帧延迟时间

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getScreenPoint() {
        return screenPoint;
    }

    public void setScreenPoint(Point screenPoint) {
        this.screenPoint = screenPoint;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
    String name;//可选，帧的名称
    Point screenPoint;//帧相对于屏幕的位置
    int w;//帧的宽度
    int h;//帧的高度
    ArrayList<Cell> cells;//所使用的模块集
    ArrayList<Action> actions;//所使用的动作集
}

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
public class Cell implements Cloneable {

    private Point framePoint;//元件相对于帧的位置

    public int getTransparency() {
        return Transparency;
    }

    public void setTransparency(int Transparency) {
        this.Transparency = Transparency;
    }

    public Point getFramePoint() {
        return framePoint;
    }

    public void setFramePoint(Point framePoint) {
        this.framePoint = framePoint;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isMirror() {
        return mirror;
    }

    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    private ArrayList<Tile> tiles;//所使用的图块集
    private float zoom;//元件的缩放 0 不放缩
    private int rotation;//元件的翻转角度 0 不翻转,90 顺时针90,-90 逆时针 90
    private boolean mirror;//元件是否镜像
    private int Transparency;//元件透明度 0~255
    private int w;//元件的宽度
    private int h;//元件的高度
}

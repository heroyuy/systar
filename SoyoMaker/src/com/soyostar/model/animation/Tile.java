/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.animation;

import java.awt.Point;

/**
 *
 * @author Administrator
 */
public class Tile implements Cloneable {

    Point cellPoint;//图块相对于元件的位置

    public Point getCellPoint() {
        return cellPoint;
    }

    public void setCellPoint(Point cellPoint) {
        this.cellPoint = cellPoint;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public Point getSourcePoint() {
        return sourcePoint;
    }

    public void setSourcePoint(Point sourcePoint) {
        this.sourcePoint = sourcePoint;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
    Point sourcePoint;//图块相对于原图的位置
    int rotation;//图块的翻转角度 0 不翻转,90 顺时针90,-90 逆时针 90
    int w;//图块的宽度
    int h;//图块的高度
}

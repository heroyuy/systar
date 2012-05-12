/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

import com.soyomaker.util.PaintParameter;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public class Clip implements Cloneable {

    @Override
    public Clip clone() throws CloneNotSupportedException {
        Clip clone = (Clip) super.clone();
        clone.framePoint = new Point(framePoint);
        clone.sourcePoint = new Point(sourcePoint);
        clone.me = this;//让克隆出的切块留有原始的一个引用
        return clone;
    }
    private Point framePoint = new Point();//图块相对于帧的位置
    private PaintParameter pai = new PaintParameter();
    private Clip me = null;

    /**
     *
     * @return
     */
    public Clip getOriginal() {
        if (me == null) {
            return this;
        }
        return me;
    }

    /**
     *
     * @param g
     * @param x
     * @param y
     */
    public void paint(Graphics g, int x, int y) {
        pai.setRec(new Rectangle(sourcePoint.x, sourcePoint.y, w, h));
        pai.setMirror(mirror);
        pai.setRotate(rotation);
        pai.setZoom(zoom);
        pai.setTransparency(transparency);
        picture.paint(g, pai, framePoint.x - zoom * w / 2 + x, framePoint.y - zoom * h / 2 + y);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean pictureContains(int x, int y) {
        if (x < sourcePoint.x || y < sourcePoint.y || x > sourcePoint.x + w || y > sourcePoint.y + h) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean frameContains(int x, int y) {
        switch (this.rotation) {
            case 0:
            case 180:
                if (x < framePoint.x || y < framePoint.y || x > framePoint.x + w * zoom || y > framePoint.y + h * zoom) {
                    return false;
                }
                break;
            case 90:
            case 270:
                if (x < framePoint.x + (w / 2 - h / 2) * zoom
                        || y < framePoint.y - (w / 2 - h / 2) * zoom
                        || x > framePoint.x + (w / 2 + h / 2) * zoom
                        || y > framePoint.y + (w / 2 + h / 2) * zoom) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public Point getFramePoint() {
        return framePoint;
    }

    /**
     *
     * @param cellPoint
     */
    public void setFramePoint(Point cellPoint) {
        this.framePoint = cellPoint;
    }

    /**
     *
     * @return
     */
    public int getH() {
        return h;
    }

    /**
     *
     * @param h
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     *
     * @return
     */
    public int getRotation() {
        return rotation;
    }

    /**
     *
     * @param rotation
     */
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    /**
     *
     * @return
     */
    public Point getSourcePoint() {
        return sourcePoint;
    }

    /**
     *
     * @param sourcePoint
     */
    public void setSourcePoint(Point sourcePoint) {
        this.sourcePoint = new Point(sourcePoint.x, sourcePoint.y);
    }

    /**
     *
     * @return
     */
    public int getW() {
        return w;
    }

    /**
     *
     * @param w
     */
    public void setW(int w) {
        this.w = w;
    }
    private Point sourcePoint = new Point();//图块相对于原图的位置
    private int rotation;//图块的翻转角度 0 不翻转,90 顺时针90,-90 逆时针 90
    private boolean mirror;//图块是否镜像
    private Picture picture;//图块所属的Picture
    private float zoom;//图块缩放
    private float frameZoom;//帧缩放
    private int transparency;//图块透明度 0~255
//    private int renderType = 0;//渲染方式
    private int w;//图块的宽度
    private int h;//图块的高度

    /**
     *
     * @return
     */
    public float getFrameZoom() {
        return frameZoom;
    }

    /**
     *
     * @param frameZoom
     */
    public void setFrameZoom(float frameZoom) {
        this.frameZoom = frameZoom;
    }

    /**
     *
     * @return
     */
    public float getZoom() {
        return zoom;
    }

    /**
     *
     * @param zoom
     */
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    /**
     *
     * @return
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     *
     * @return
     */
    public boolean isMirror() {
        return mirror;
    }

    /**
     *
     * @param mirror
     */
    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }

//    /**
//     *
//     * @return
//     */
//    public int getRenderType() {
//        return renderType;
//    }
//
//    /**
//     *
//     * @param renderType
//     */
//    public void setRenderType(int renderType) {
//        this.renderType = renderType;
//    }
    /**
     *
     * @return
     */
    public int getTransparency() {
        return transparency;
    }

    /**
     *
     * @param Transparency
     */
    public void setTransparency(int Transparency) {
        this.transparency = Transparency;
    }
}

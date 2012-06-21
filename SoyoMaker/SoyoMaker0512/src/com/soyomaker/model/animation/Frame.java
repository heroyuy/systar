/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Frame implements Cloneable {

    private int delay = 100;//单帧延迟时间,默认为100ms
    private String name = "新建帧";//可选，帧的名称
    private ArrayList<Clip> tiles = new ArrayList<Clip>();//所使用的模块集
    private Animation animation;
    private int width = 384;
    private int height = 384;
    private int pngX = 0;//在生成的动画图片中，该帧的x坐标
    private int pngY = 0;//在生成的动画图片中，该帧的y坐标
    private int pngWidth = 0;//在生成的动画图片中，该帧的宽度（计算所有Clip所得到的实际宽度）
    private int pngHeight = 0;//在生成的动画图片中，该帧的高度（计算所有Clip所得到的实际高度）
    private int offsetX = 0;//针对这个实际宽度，与原先设定的帧的宽度之间的偏移值
    private int offsetY = 0;//针对这个实际高度，与原先设定的帧的高度之间的偏移值

    @Override
    public Object clone() throws CloneNotSupportedException {
        Frame clone = (Frame) super.clone();
        clone.tiles = new ArrayList<Clip>();
        for (int i = 0; i < this.tiles.size(); i++) {
            clone.tiles.add((Clip) tiles.get(i).clone());
        }
        return clone;
    }

    public int getPngHeight() {
        return pngHeight;
    }

    public void setPngHeight(int pngHeight) {
        this.pngHeight = pngHeight;
    }

    public int getPngWidth() {
        return pngWidth;
    }

    public void setPngWidth(int pngWidth) {
        this.pngWidth = pngWidth;
    }

    public int getPngX() {
        return pngX;
    }

    public void setPngX(int pngX) {
        this.pngX = pngX;
    }

    public int getPngY() {
        return pngY;
    }

    public void setPngY(int pngY) {
        this.pngY = pngY;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getID() {
        if (animation != null) {
            return animation.getFrames().indexOf(this);
        }
        return 0;
    }

    /**
     *
     * @param ani
     */
    public void setAnimation(Animation ani) {
        this.animation = ani;
    }

    /**
     *
     * @return
     */
    public Animation getAnimation() {
        return this.animation;
    }

    /**
     *
     * @param g
     * @param x
     * @param y
     */
    public void paint(Graphics g, int x, int y) {
        for (int i = 0; i < this.tiles.size(); i++) {
            this.tiles.get(i).paint(g, x, y);
        }
    }

    /**
     *
     * @param index
     */
    public void swapClipBottom(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                    "Can't swap down when already at the bottom.");
        }
        Clip clip = getTile(index);
        tiles.remove(clip);
        tiles.add(0, clip);
    }

    /**
     *
     * @param index
     */
    public void swapClipTop(int index) {
        if (index + 1 == tiles.size()) {
            throw new RuntimeException(
                    "Can't swap up when already at the top.");
        }
        Clip clip = getTile(index);
        tiles.remove(clip);
        tiles.add(clip);
    }

    /**
     *
     * @param index
     */
    public void swapClipUp(int index) {
        if (index + 1 == tiles.size()) {
            throw new RuntimeException(
                    "Can't swap up when already at the top.");
        }
        Clip clip = getTile(index + 1);
        tiles.set(index + 1, getTile(index));
        tiles.set(index, clip);
    }

    /**
     *
     * @param index
     */
    public void swapClipDown(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                    "Can't swap down when already at the bottom.");
        }
        Clip clip = getTile(index - 1);
        tiles.set(index - 1, getTile(index));
        tiles.set(index, clip);
    }

    /**
     *
     * @return
     */
    public ArrayList<Clip> getTiles() {
        return tiles;
    }

    /**
     *
     * @param id
     * @return
     */
    public Clip getTile(int id) {
        if (id < 0 || id > tiles.size() - 1) {
            return null;
        }
        return tiles.get(id);
    }

    /**
     *
     * @param act
     */
    public void addTile(Clip act) {
        tiles.add(act);
    }

    /**
     *
     * @param id
     */
    public void removeTile(int id) {
        tiles.remove(id);
    }

    /**
     *
     * @return
     */
    public int getDelay() {
        return delay;
    }

    /**
     *
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}

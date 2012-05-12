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
    private String name = "";//可选，帧的名称
    private ArrayList<Clip> tiles = new ArrayList<Clip>();//所使用的模块集
    private Animation animation;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Frame clone = (Frame) super.clone();
        clone.tiles = new ArrayList<Clip>();
        for (int i = 0; i < this.tiles.size(); i++) {
            clone.tiles.add((Clip) tiles.get(i).clone());
        }
        return clone;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

import java.util.ArrayList;

/**
 * 
 * @author Administrator
 */
public class Animation {

    private int index;//动画的序号，动画的唯一性标识
    private String name = "";//可选，动画的名称
    private ArrayList<Frame> frames = new ArrayList<Frame>();//帧序列
    private ArrayList<Action> actions = new ArrayList<Action>();//动作序列
    private int width = 384;
    private int height = 384;
    private int frameDelay = 100;

    @Override
    public String toString() {
        return this.getName() + " ID:" + this.getIndex() + "";
    }

    /**
     *
     * @return
     */
    public int getFrameDelay() {
        return frameDelay;
    }

    /**
     *
     * @param frameDelay
     */
    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     *
     * @return
     */
    public ArrayList<Frame> getFrames() {
        return frames;
    }

    /**
     *
     * @return
     */
    public ArrayList<Picture> getUsedPictures() {
        ArrayList<Picture> pics = new ArrayList<Picture>();
        for (Frame frame : frames) {
            for (Clip clip : frame.getTiles()) {
                if (!pics.contains(clip.getPicture())) {
                    pics.add(clip.getPicture());
                }
            }
        }
        return pics;
    }

    /**
     *
     * @param id
     * @return
     */
    public Action getAction(int id) {
        if (id < 0 || id > actions.size() - 1) {
            return null;
        }
        return actions.get(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public Frame getFrame(int id) {
        if (id < 0 || id > frames.size() - 1) {
            return null;
        }
        return frames.get(id);
    }

    /**
     *
     * @param index
     */
    public void swapFrameDown(int index) {
        if (index + 1 == frames.size()) {
            throw new RuntimeException(
                    "Can't swap up when already at the top.");
        }
        Frame f = getFrame(index + 1);
        frames.set(index + 1, getFrame(index));
        frames.set(index, f);
    }

    /**
     *
     * @param index
     */
    public void swapFrameUp(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                    "Can't swap down when already at the bottom.");
        }
        Frame hold = getFrame(index - 1);
        frames.set(index - 1, getFrame(index));
        frames.set(index, hold);
    }

    /**
     *
     * @param action
     */
    public void addAction(Action action) {
        action.setAnimation(this);
        actions.add(action);
    }

    /**
     *
     * @param id
     */
    public void removeAction(int id) {
        actions.remove(id);
    }

    /**
     *
     * @param actions
     */
    public void setAction(ArrayList<Action> actions) {
        this.actions = actions;
    }

    /**
     *
     * @param frame
     */
    public void addFrame(Frame frame) {
        frame.setAnimation(this);
        frames.add(frame);
    }

    /**
     *
     * @param id
     */
    public void removeFrame(int id) {
        frames.remove(id);
    }

    /**
     *
     * @param frames
     */
    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
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

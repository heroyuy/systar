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
    private ArrayList<Action> actions = new ArrayList<Action>();//所使用的动作集

    @Override
    public String toString() {
        return this.getName() + " ID:" + this.getIndex() + "";
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
     * @param actions
     */
    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
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
     * @param act
     */
    public void addAction(Action act) {
        actions.add(act);
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
     * @return
     */
    public ArrayList<Frame> getFrames() {
        return frames;
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

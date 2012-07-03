/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @author Administrator
 */
public class Animation {

    private int index;//动画的序号，动画的唯一性标识
    private String name = "";//可选，动画的名称
    private ArrayList<Frame> frames = new ArrayList<Frame>();//帧序列
    private ArrayList<Action> actions = new ArrayList<Action>();//动作序列
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
        //保证动画中每个帧的播放时间相同
        for (int i = 0; i < frames.size(); i++) {
            frames.get(i).setDelay(frameDelay);
        }
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

    class FrameHeightComparator implements Comparator {

        public int compare(Object o1, Object o2) {
            Frame t1 = (Frame) o1;
            Frame t2 = (Frame) o2;
            int result = t1.getPngHeight() > t2.getPngHeight() ? 1 : (t1.getPngHeight() == t2.getPngHeight() ? 0 : -1);
            return result;
        }
    }

    /**
     * FIXME 需要优化
     * @return
     */
    public BufferedImage getPngBufferedImage() {
        int w = 0;
        int h = -Integer.MAX_VALUE;
        ArrayList<Frame> frameList = new ArrayList<Frame>();
        for (Frame frame : frames) {
            frameList.add(frame);
        }
        Collections.sort(frameList, new FrameHeightComparator());//根据帧高度从大到小排列帧列表
        BufferedImage image = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_ARGB);//先创建一个默认支持的最大的图像
        Graphics ig = image.getGraphics();
        int dx = 0;
        int dy = 0;
        int hh = frameList.get(0).getPngHeight();
        int ww = 0;
        for (Frame frame : frameList) {
            if (dx + frame.getPngWidth() < 2048) {
                frame.setPngX(dx);
                frame.setPngY(dy);
                ig.drawImage(frame.getPngBufferedImage(), dx, dy, null);
                dx += frame.getPngWidth();
            } else {
                if (dx > ww) {
                    ww = dx;
                }
                dx = 0;
                dy = hh;
                frame.setPngX(dx);
                frame.setPngY(dy);
                ig.drawImage(frame.getPngBufferedImage(), dx, dy, null);
                dx += frame.getPngWidth();
                hh += frame.getPngHeight();
            }
        }
//        System.out.println("w:" + Math.max(ww, dx) + " h:" + hh);
//
//        for (Frame frame : frames) {
//            w += frame.getPngWidth();
//            if (h < frame.getPngHeight()) {
//                h = frame.getPngHeight();
//            }
//        }
////        System.out.println("w:" + w + " h:" + h);
//        BufferedImage buffImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//        Graphics g = buffImage.getGraphics();
//        int x = 0;
//        int y = 0;
//        for (Frame frame : frames) {
//            frame.setPngX(x);
//            frame.setPngY(y);
//            g.drawImage(frame.getPngBufferedImage(), x, y, null);
//            x += frame.getPngWidth();
//        }
//        return buffImage;
        return image.getSubimage(0, 0, Math.max(ww, dx), hh);
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

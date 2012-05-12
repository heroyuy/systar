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
public class Action {

    /**
     *
     */
    public static final byte NOTHING = 0;//播放音乐
    /**
     *
     */
    public static final byte SCEEN_TWINKLE = 2;//画面闪烁
    /**
     *
     */
    public static final byte PLAY_TWINKLE = 1;//主角闪烁
    /**
     *
     */
    public static final byte PLAY_DISAPPEAR = 3;//主角消失
    private int frameId;//所属于的帧Id
    private String musicFile = "";//背景音乐
    private byte type;//特效类型

    /**
     *
     * @return
     */
    public String getMusicFile() {
        return musicFile;
    }

    /**
     *
     * @param musicFile
     */
    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }

    /**
     *
     * @return
     */
    public int getFrameId() {
        return frameId;
    }

    /**
     *
     * @param frameId
     */
    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    /**
     *
     * @return
     */
    public byte getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(byte type) {
        this.type = type;
    }
    private int red = 255;

    /**
     *
     * @return
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     *
     * @param alpha
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     *
     * @return
     */
    public int getBlue() {
        return blue;
    }

    /**
     *
     * @param blue
     */
    public void setBlue(int blue) {
        this.blue = blue;
    }

    /**
     *
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     */
    public int getGreen() {
        return green;
    }

    /**
     *
     * @param green
     */
    public void setGreen(int green) {
        this.green = green;
    }

    /**
     *
     * @return
     */
    public int getRed() {
        return red;
    }

    /**
     *
     * @param red
     */
    public void setRed(int red) {
        this.red = red;
    }
    private int green = 255;
    private int blue = 255;
    private int alpha = 255;
    private int duration = 1;
}

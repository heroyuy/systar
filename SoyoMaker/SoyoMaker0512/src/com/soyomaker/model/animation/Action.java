/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

/**
 *
 * @author Administrator
 */
public class Action {

    private Animation animation;
    /**
     *
     */
    public static final int NOTHING = 0;//无
    /**
     *
     */
    public static final int SCREEN_FLICKER = 1;//全屏闪烁
    /**
     *
     */
    public static final int OBJECT_FLICKER = 2;//对象闪烁
    /**
     *
     */
    public static final int OBJECT_DISAPPEAR = 3;//对象消失
    private int startFrameIndex = 0;//开始帧
    private int lastFrameCount = 1;//持续时间
    private int effectType = 0;//光影效果
    private String musicName = "";
    /**
     *
     */
    public static final String[] EFFECTS = {"无", "全屏闪烁", "对象闪烁", "对象消失"};
    private int sr = 1;
    private int sg = 1;
    private int sb = 1;
    private int tr = 0;
    private int tg = 0;
    private int tb = 0;

    /**
     *
     * @return
     */
    public int getSb() {
        return sb;
    }

    /**
     *
     * @param sb
     */
    public void setSb(int sb) {
        this.sb = sb;
    }

    /**
     *
     * @return
     */
    public int getSg() {
        return sg;
    }

    /**
     *
     * @param sg
     */
    public void setSg(int sg) {
        this.sg = sg;
    }

    /**
     *
     * @return
     */
    public int getSr() {
        return sr;
    }

    /**
     *
     * @param sr
     */
    public void setSr(int sr) {
        this.sr = sr;
    }

    /**
     *
     * @return
     */
    public int getTb() {
        return tb;
    }

    /**
     *
     * @param tb
     */
    public void setTb(int tb) {
        this.tb = tb;
    }

    /**
     *
     * @return
     */
    public int getTg() {
        return tg;
    }

    /**
     *
     * @param tg
     */
    public void setTg(int tg) {
        this.tg = tg;
    }

    /**
     *
     * @return
     */
    public int getTr() {
        return tr;
    }

    /**
     *
     * @param tr
     */
    public void setTr(int tr) {
        this.tr = tr;
    }

    /**
     *
     * @return
     */
    public String getMusicName() {
        return musicName;
    }

    /**
     *
     * @param musicName
     */
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    /**
     *
     * @return
     */
    public int getEffectType() {
        return effectType;
    }

    /**
     *
     * @param effectType
     */
    public void setEffectType(int effectType) {
        this.effectType = effectType;
    }

    /**
     *
     * @return
     */
    public int getLastFrameCount() {
        return lastFrameCount;
    }

    /**
     *
     * @param lastFrameCount
     */
    public void setLastFrameCount(int lastFrameCount) {
        this.lastFrameCount = lastFrameCount;
    }

    /**
     *
     * @return
     */
    public int getStartFrameIndex() {
        return startFrameIndex;
    }

    /**
     *
     * @param startFrameIndex
     */
    public void setStartFrameIndex(int startFrameIndex) {
        this.startFrameIndex = startFrameIndex;
    }

    /**
     *
     * @param animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * 
     * @return
     */
    public Animation getAnimation() {
        return animation;
    }
}

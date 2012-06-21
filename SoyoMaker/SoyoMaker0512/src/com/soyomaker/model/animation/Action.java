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
    public static final int NOTHING = 0;//无
    public static final int SCREEN_FLICKER = 1;//全屏闪烁
    public static final int OBJECT_FLICKER = 2;//对象闪烁
    public static final int OBJECT_DISAPPEAR = 3;//对象消失
    private int startFrameIndex = 0;//开始帧
    private int lastFrameCount = 1;//持续时间
    private int effectType = 0;//光影效果
    private String musicName = "";
    public static final String[] EFFECTS = {"无", "全屏闪烁", "对象闪烁", "对象消失"};
    private int sr = 1;
    private int sg = 1;
    private int sb = 1;
    private int tr = 0;
    private int tg = 0;
    private int tb = 0;

    public int getSb() {
        return sb;
    }

    public void setSb(int sb) {
        this.sb = sb;
    }

    public int getSg() {
        return sg;
    }

    public void setSg(int sg) {
        this.sg = sg;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public int getTb() {
        return tb;
    }

    public void setTb(int tb) {
        this.tb = tb;
    }

    public int getTg() {
        return tg;
    }

    public void setTg(int tg) {
        this.tg = tg;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getEffectType() {
        return effectType;
    }

    public void setEffectType(int effectType) {
        this.effectType = effectType;
    }

    public int getLastFrameCount() {
        return lastFrameCount;
    }

    public void setLastFrameCount(int lastFrameCount) {
        this.lastFrameCount = lastFrameCount;
    }

    public int getStartFrameIndex() {
        return startFrameIndex;
    }

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

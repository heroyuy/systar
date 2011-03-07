/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;

/**
 *
 * ¶¯»­
 */
public class Animation {

    public int index = 0;// ±àºÅ
    public String name = "";// Ãû³Æ
    public String soundName = "";// ÒôÐ§Ãû³Æ
    public String imageName = "";// ¶¯»­Ô´Í¼Æ¬Ãû³Æ
    public Image image = null;
    public int frameWidth = 0;// Ö¡¿í
    public int frameHeight = 0;// Ö¡¸ß
    public int frameNum = 0;// Ö¡Êý
    public Frame[] frames = null;// Ö¡
    private int curIndex = 0;// µ±Ç°Ö¡ºÅ

    public Frame getCurFrame() {
        Frame frame = null;
        if (curIndex >= 0 && curIndex < frameNum) {
            frame = frames[curIndex];
        }
        return frame;
    }

    public Frame getFrame(int index) {
        Frame frame = null;
        if (index >= 0 && index < frameNum) {
            frame = frames[index];
        }
        return frame;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public int getCurIndex() {
        return curIndex;
    }
}

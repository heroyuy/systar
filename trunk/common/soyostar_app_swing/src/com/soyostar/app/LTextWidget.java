/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

/**
 *
 * @author Administrator
 */
public class LTextWidget extends Widget {

    private int textSize = 12;
    private int textColor = Color.BLACK;
    private int marginTop = 0;
    private int marginBottum = 0;
    private int marginLeft = 0;
    private int marginRight = 0;
    private int leading = 0;

    public int getLeading() {
        return leading;
    }

    public void setLeading(int leading) {
        this.leading = leading;
    }

    public int getMarginBottum() {
        return marginBottum;
    }

    public void setMarginBottum(int marginBottum) {
        this.marginBottum = marginBottum;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }


    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}

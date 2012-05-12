/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * 字体的属性类
 */
public class LogFontAttribute {

    /**
     *
     */
    public final static int D_ATTRIBUTE = 1;
    /**
     *
     */
    public final static int V_ATTRIBUTE = 2;
    /**
     *
     */
    public final static int W_ATTRIBUTE = 3;
    /**
     *
     */
    public final static int E_ATTRIBUTE = 4;
    private SimpleAttributeSet attrSet = null; // 属性集
    private String name = null; // 要输入的文本和字体名称
    private boolean bold = false;
    private boolean italic = false;
    private int size; // 样式和字号
    private Color foreColor = Color.BLACK, backColor = Color.WHITE; // 文字颜色和背景颜色

    /**
     *
     */
    public LogFontAttribute() {
    }

    /**
     *
     * @param type
     */
    public LogFontAttribute(int type) {
        switch (type) {
            case D_ATTRIBUTE:
                this.setForeColor(Color.BLACK);
                this.setBold(true);
                this.setItalic(false);
                this.setSize(13);
                break;
            case V_ATTRIBUTE:
                this.setForeColor(Color.BLACK);
                this.setBold(false);
                this.setItalic(false);
                this.setSize(13);
                break;
            case W_ATTRIBUTE:
                this.setForeColor(Color.BLUE);
                this.setBold(false);
                this.setItalic(false);
                this.setSize(13);
                break;
            case E_ATTRIBUTE:
                this.setForeColor(Color.RED);
                this.setBold(false);
                this.setItalic(false);
                this.setSize(13);
                break;
        }
    }

    /**
     * 返回属性集
     *
     * @return
     */
    public SimpleAttributeSet getAttrSet() {
        attrSet = new SimpleAttributeSet();
        if (name != null) {
            StyleConstants.setFontFamily(attrSet, name);
        }
        StyleConstants.setBold(attrSet, this.bold);
        StyleConstants.setItalic(attrSet, this.italic);
        StyleConstants.setFontSize(attrSet, size);

        if (foreColor != null) {
            StyleConstants.setForeground(attrSet, foreColor);
        }
        if (backColor != null) {
            StyleConstants.setBackground(attrSet, backColor);
        }
        return attrSet;
    }

    /**
     * 设置属性集
     *
     * @param attrSet
     */
    public void setAttrSet(SimpleAttributeSet attrSet) {
        this.attrSet = attrSet;
    }

    /**
     *
     * @return
     */
    public Color getForeColor() {
        return foreColor;
    }

    /**
     *
     * @param foreColor
     */
    public void setForeColor(Color foreColor) {
        this.foreColor = foreColor;
    }

    /**
     *
     * @return
     */
    public Color getBackColor() {
        return backColor;
    }

    /**
     *
     * @param backColor
     */
    public void setBackColor(Color backColor) {
        this.backColor = backColor;
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

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the bold
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * @param bold the bold to set
     */
    public void setBold(boolean bold) {
        this.bold = bold;
    }

    /**
     * @return the italic
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * @param italic the italic to set
     */
    public void setItalic(boolean italic) {
        this.italic = italic;
    }
}

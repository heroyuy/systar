/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

/**
 *进度条
 * @author Administrator
 */
public class LProgressBar extends Widget {

    private int ProgressBarcolor = Color.YELLOW;
    private int value = 0;   //长度
    private int maxvalue = 0;//最大值
    private int x = 0;
    private int y = 0;
 
    public int getValue() {

        return value;

    }


    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void paint(Painter painter) {
        painter.setColor(getBackground());
        painter.fillRoundRect(0, 0, getWidth(), getHeight(), 10);
        painter.setColor(ProgressBarcolor);
        painter.fillRoundRect(0, 0, (int) (getValue() * 1.0) / getMaxvalue() * getWidth(), getHeight(), 10);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

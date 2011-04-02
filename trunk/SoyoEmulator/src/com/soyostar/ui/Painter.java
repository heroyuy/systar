package com.soyostar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 绘图类，相当于android的Canvas+Paint,j2me和swing中的Graphics
 * 
 * @author Administrator
 * 
 */
public class Painter {

    public static final byte LT = 0;
    public static final byte LV = 1;
    public static final byte LB = 2;
    public static final byte HT = 3;
    public static final byte HV = 4;
    public static final byte HB = 5;
    public static final byte RT = 6;
    public static final byte RV = 7;
    public static final byte RB = 8;
    public static final int STYLE_PLAIN = Font.PLAIN;//普通
    public static final int STYLE_BOLD = Font.BOLD;//加粗
    public static final int STYLE_ITALIC = Font.ITALIC;//斜体
    private Graphics g = null;
    private Font font = null;

    protected void setGraphics(Graphics g) {
        this.g = g;
    }

    protected Painter() {
        font = new Font(Font.DIALOG, STYLE_PLAIN, 12);
    }

    /**
     * 设置画笔的颜色
     * @param color
     */
    public void setColor(Color color) {
        g.setColor(color);
    }

      public void drawString(String str, int x, int y, int anchor) {
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(str);
        int h = fm.getHeight();
        int[] xy = convertOrdinate(x, y, w, h, anchor);
        g.drawString(str, xy[0], xy[1]+fm.getHeight());
    }



    /**
     * 填充矩形区域
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    /**
     * 填充矩形区域
     * @param rect
     */
    public void fillRect(Rect rect) {
        fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

   
    /**
     * 画直线
     * @param startX
     * @param startY
     * @param stopX
     * @param stopY
     */
    public void drawLine(int startX, int startY, int stopX, int stopY) {
        g.drawLine(startX, startY, stopX, stopY);
    }

    /**
     * 画直线
     * @param line
     */
    public void drawLine(Line line) {
        g.drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(),
                line.getEndPoint().getX(), line.getEndPoint().getY());
    }

    /**
     * 根据锚点进行坐标转换
     * @param x
     * @param y
     * @param w
     * @param h
     * @param anchor
     * @return
     */
    private int[] convertOrdinate(int x, int y, int w, int h, int anchor) {
        int[] xy = new int[2];
        switch (anchor) {
            case LT:
                xy[0] = x;
                xy[1] = y;
                break;
            case LV:
                xy[0] = x;
                xy[1] = y - h / 2;
                break;
            case LB:
                xy[0] = x;
                xy[1] = y - h;
                break;
            case HT:
                xy[0] = x - w / 2;
                xy[1] = y;
                break;
            case HV:
                xy[0] = x - w / 2;
                xy[1] = y - h / 2;
                break;
            case HB:
                xy[0] = x - w / 2;
                xy[1] = y - h;
                break;
            case RT:
                xy[0] = x - w;
                xy[1] = y;
                break;
            case RV:
                xy[0] = x - w;
                xy[1] = y - h / 2;
                break;
            case RB:
                xy[0] = x - w;
                xy[1] = y - h;
                break;
        }
        return xy;
    }
}

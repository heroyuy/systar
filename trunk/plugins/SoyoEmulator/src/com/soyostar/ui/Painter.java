package com.soyostar.ui;

import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.data.access.ImageManager;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

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
    public static final byte DIALOG_DEEP = 0;
    public static final byte DIALOG_LIGHT = 1;
    public static final byte NODIALOG = 2;
    public static final byte SCROLLBAR_VERTICAL = 0;
    public static final byte SCROLLBAR_HORIZONTAL = 1;
    public static final byte CELL_DEEP = 0;
    public static final byte CELL_LIGHT = 1;
    private Graphics g = null;
    private Font font = null;

    protected void setGraphics(Graphics g) {
        this.g = g;
        g.setFont(font);
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

    public void setFontSize(int size) {
        font = new Font(Font.DIALOG, font.getStyle(), size);
        g.setFont(font);
    }

    public void setFontStyle(int style) {
        font = new Font(Font.DIALOG, style, font.getSize());
        g.setFont(font);
    }

    public void drawString(String str, int x, int y, int anchor) {
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(str);
        int h = fm.getHeight();
        int[] xy = convertOrdinate(x, y, w, h, anchor);
        g.drawString(str, xy[0], xy[1] + fm.getHeight());
    }

    public int stringWidth(String str) {
        return g.getFontMetrics().stringWidth(str);
    }

    public int getFontHeight() {
        return g.getFontMetrics().getHeight();
    }

    public void drawImage(Image image, int x, int y, int anchor) {

        int w = image.getWidth();
        int h = image.getHeight();
        int[] xy = convertOrdinate(x, y, w, h, anchor);
        g.drawImage(image.getContext(), xy[0], xy[1], null);
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
     * 绘制矩形框
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }

    /**
     * 绘制矩形框
     * @param rect
     */
    public void drawRect(Rect rect) {
        drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public void drawRoundRect(int x, int y, int w, int h, int aw, int ah) {
        g.drawRoundRect(x, y, w, h, aw, ah);

    }

    public void fillRoundRect(int x, int y, int w, int h, int aw, int ah) {
        g.fillRoundRect(x, y, w, h, aw, ah);

    }

    public void drawRoundRect(Rect rect, int aw, int ah) {
        g.drawRoundRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), aw, ah);

    }

    public void fillRoundRect(Rect rect, int aw, int ah) {
        g.fillRoundRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), aw, ah);

    }
   private GameEngine ge = GameEngine.getInstance();
    /**
     * 在指定位置绘制指定大小的对话框
     * @param g 画笔
     * @param x x坐标
     * @param y y坐标
     * @param w 宽度
     * @param h 高度
     * @param type 类型，为Painter.DIALOG_DEEP时画深色框 其它则画浅色框
     */
    public static void drawDialog(int x, int y, int w, int h, byte type) {
        if (type == Painter.DIALOG_DEEP || type == Painter.DIALOG_LIGHT) {
            Image img = ge.getImageManager().getImage(type == Painter.DIALOG_DEEP ? Const.ImagePath.DIALOG_DEEP : Const.ImagePath.DIALOG_LIGHT);
            drawRectFrame(img, x, y, w, h);
        } else {
            System.out.println("参数type不对，只能为0或1");
        }
    }

    public static void drawRectFrame(Image img, int x, int y, int w, int h) {
        setClip(x, y, w, h);
        int rowNum = h / (img.getHeight() / 2) + 1;
        int colNum = w / (img.getWidth() / 2) + 1;
        //绘制底色
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                drawRegion(img, img.getWidth() / 2, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2, 0, x + img.getWidth() / 2 * i, y + img.getHeight() / 2 * j, 0);
            }
        }
        //绘制边框 [先水平，后竖直]
        for (int i = 0; i < colNum; i++) {
            drawRegion(img, img.getWidth() / 2, 0, img.getWidth() / 2, img.getHeight() / 2, 0, x + img.getWidth() / 2 * i, y, 0);
            drawRegion(img, img.getWidth() / 2, 0, img.getWidth() / 2, img.getHeight() / 2, 1, x + img.getWidth() / 2 * i, y + h - img.getHeight() / 2, 0);
        }
        for (int i = 0; i < rowNum; i++) {
            drawRegion(img, 0, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2, 0, x, y + img.getHeight() / 2 * i, 0);
            drawRegion(img, 0, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2, 3, x + w - img.getWidth() / 2, y + img.getHeight() / 2 * i, 0);

        }
        //四个角
        drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 0, x, y, 0);
        drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 2, x + w - img.getWidth() / 2, y, 0);
        drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 1, x, y + h - img.getHeight() / 2, 0);
        drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 3, x + w - img.getWidth() / 2, y + h - img.getHeight() / 2, 0);

        setClip(0, 0, GameData.getGameData().screenWidth, GameData.getGameData().screenHeight);
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

    public void setClip(int x, int y, int w, int h) {
        g.setClip(x, y, w, h);
    }

    public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) {
        int[] xy = convertOrdinate(x_dest, y_dest, width, height, anchor);
        Image temp = Image.createImage(width, height);
        Graphics gg = temp.getPainter().g;
        gg.drawImage(src.getContext(), 0, 0, width, height, x_src, y_src, x_src + width, y_src + height, null);
        switch (transform) {
            case Image.TRANS_NONE:
                g.drawImage(temp.getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_MIRROR:
                g.drawImage(Image.effect_mirror(temp).getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_MIRROR_ROT180:
                g.drawImage(Image.rotate(Image.effect_mirror(temp), 180).getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_ROT180:
                g.drawImage(Image.rotate(temp, 180).getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_MIRROR_ROT270:
                g.drawImage(Image.rotate(Image.effect_mirror(temp), 270).getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_ROT270:
                g.drawImage(Image.rotate(temp, 270).getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_ROT90:
                g.drawImage(Image.rotate(temp, 180).getContext(), xy[0], xy[1], null);
                break;
            case Image.TRANS_MIRROR_ROT90:
                g.drawImage(Image.rotate(Image.effect_mirror(temp), 90).getContext(), xy[0], xy[1], null);
                break;
        }
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

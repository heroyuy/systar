package com.soyostar.app;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Administratorh
 */
class GraphicsPainter implements Painter {

    private Graphics graphics = null;
    private Point point = null;
    private Rect curClip = null;

    GraphicsPainter(Graphics graphics) {
        this.graphics = graphics;
        point = new Point(0, 0);

    }

    Graphics getGraphics() {
        return graphics;
    }

    void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void setTextSize(int size) {
        graphics.setFont(new Font(Font.DIALOG, Font.PLAIN, size));

    }

    @Override
    public int getTextSize() {
        return graphics.getFont().getSize();
    }

    @Override
    public int stringWidth(String str) {
        return graphics.getFontMetrics().stringWidth(str);
    }

    @Override
    public void drawString(String str, int x, int y, int anchor) {
        int[] xy = convert(x, y, stringWidth(str), getTextSize(), anchor);
        graphics.drawString(str, xy[0], xy[1] - graphics.getFontMetrics().getDescent() + getTextSize());
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawImage(Image img, int x, int y, int anchor) {
        if (img == null) {
            return;
        }
        int[] xy = convert(x, y, img.getWidth(), img.getHeight(), anchor);
        graphics.drawImage(img.getContent(), xy[0], xy[1], null);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcSize) {
        graphics.drawRoundRect(x, y, width, height, arcSize, arcSize);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(x, y, width, height);
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcSize) {
        graphics.fillRoundRect(x, y, width, height, arcSize, arcSize);
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        graphics.fillOval(x, y, width, height);
    }

    public void setClip(int x, int y, int width, int height) {
        if (curClip != null) {
            forceClip(curClip);
        }
        graphics.clipRect(x, y, width, height);
    }

    public void setClip(Rect rect) {
        if (curClip != null) {
            forceClip(curClip);
        }
        graphics.clipRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    void clipRect(int x, int y, int width, int height) {
        graphics.clipRect(x, y, width, height);
    }

    void clipRect(Rect rect) {
        graphics.clipRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    void forceClip(Rect clip) {
        graphics.setClip(clip.getX(), clip.getY(), clip.getWidth(), clip.getHeight());
    }

    public Rect getClip() {
        Rectangle rectangle = graphics.getClipBounds();
        return new Rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void setColor(int color) {
        java.awt.Color c = new java.awt.Color(color, true);
        graphics.setColor(c);

    }

    public int getColor() {
        return graphics.getColor().getRGB();
    }

    void setBasePoint(int x, int y) {
        graphics.translate(x, y);
        point.setX(point.getX() + x);
        point.setY(point.getY() + y);

    }

    void setBasePoint(Point point) {
        graphics.translate(point.getX(), point.getY());
        this.point.setX(this.point.getX() + point.getX());
        this.point.setY(this.point.getY() + point.getY());
    }

    Point getBasePoint() {
        return new Point(point);
    }

    Rect getCurClip() {
        return curClip;
    }

    void setCurClip(Rect curClip) {
        this.curClip = curClip;
    }

    /**
     * 辅助方法，转换坐标
     * @param x
     * @param y
     * @param width
     * @param height
     * @param anchor
     * @return
     */
    private int[] convert(int x, int y, int width, int height, int anchor) {
        int[] xy = {0, 0};
        switch (anchor) {
            case Painter.LT: {
                xy[0] = x;
                xy[1] = y;
            }
            break;
            case Painter.LV: {
                xy[0] = x;
                xy[1] = y - height / 2;
            }
            break;
            case Painter.LB: {
                xy[0] = x;
                xy[1] = y - height;
            }
            break;
            case Painter.HT: {
                xy[0] = x - width / 2;
                xy[1] = y;
            }
            break;
            case Painter.HV: {
                xy[0] = x - width / 2;
                xy[1] = y - height / 2;
            }
            break;
            case Painter.HB: {
                xy[0] = x - width / 2;
                xy[1] = y - height;
            }
            break;
            case Painter.RT: {

                xy[0] = x - width;
                xy[1] = y;
            }
            break;
            case Painter.RV: {
                xy[0] = x - width;
                xy[1] = y - height / 2;
            }
            break;
            case Painter.RB: {
                xy[0] = x - width;
                xy[1] = y - height;
            }
            break;
        }
        return xy;
    }
}

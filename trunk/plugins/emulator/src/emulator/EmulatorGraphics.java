package emulator;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class EmulatorGraphics {

    public static final int LT = 0;
    public static final int LV = 1;
    public static final int LB = 2;
    public static final int HT = 3;
    public static final int HV = 4;
    public static final int HB = 5;
    public static final int RT = 6;
    public static final int RV = 7;
    public static final int RB = 8;
    private Graphics g = null;
    private EmulatorFont ef = null;

    public EmulatorGraphics(Graphics g) {
        this.g = g;
        ef = EmulatorFont.getDefaultEmulatorFont();
    }

    public void drawString(String str, int x, int y, int anchor) {
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(str);
        int h = fm.getHeight();
        int[] xy = convertOrdinate(x, y, w, h, anchor);
        g.drawString(str, xy[0], xy[1]+ef.getHeight());
    }

    public void drawImage(EmulatorImage ei, int x, int y, int anchor) {

        int w = ei.getWidth();
        int h = ei.getHeight();
        int[] xy = convertOrdinate(x, y, w, h, anchor);
        g.drawImage(ei, xy[0], xy[1], null);
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void setEmulatorFont(EmulatorFont ef) {
        g.setFont(ef.getFont());


    }

    public void drawRect(int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
    }

    public void fillRect(int x, int y, int w, int h) {
        g.fillRect(x, y, w, h);
    }

    public void drawRoundRect(int x, int y, int w, int h, int aw, int ah) {
        g.drawRoundRect(x, y, w, h, aw, ah);

    }

    public void fillRoundRect(int x, int y, int w, int h, int aw, int ah) {
        g.fillRoundRect(x, y, w, h, aw, ah);

    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void setClip(int x, int y, int w, int h) {
        g.setClip(x, y, w, h);
    }

    public EmulatorFont getEmulatorFont() {
        return ef;
    }

    public void drawRegion(EmulatorImage src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) {
        int[] xy = convertOrdinate(x_dest, y_dest, width, height, anchor);
        EmulatorImage temp = EmulatorImage.createImage(width, height);
        Graphics gg = temp.getGraphics();
        gg.drawImage(src, 0, 0, width, height, x_src, y_src, x_src + width, y_src + height, null);
        switch (transform) {
            case EmulatorImage.TRANS_NONE:
                g.drawImage(temp, xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_MIRROR:
                g.drawImage(Painter.effect_mirror(temp), xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_MIRROR_ROT180:
                g.drawImage(EmulatorImage.rotateImage(Painter.effect_mirror(temp), 180), xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_ROT180:
                g.drawImage(EmulatorImage.rotateImage(temp, 180), xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_MIRROR_ROT270:
                g.drawImage(EmulatorImage.rotateImage(Painter.effect_mirror(temp), 270), xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_ROT270:
                g.drawImage(EmulatorImage.rotateImage(temp, 270), xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_ROT90:
                g.drawImage(EmulatorImage.rotateImage(temp, 180), xy[0], xy[1], null);
                break;
            case EmulatorImage.TRANS_MIRROR_ROT90:
                g.drawImage(EmulatorImage.rotateImage(Painter.effect_mirror(temp), 90), xy[0], xy[1], null);
                break;
        }
    }

    public void drawChar(char ch, int x, int y, int anchor) {
        drawString(ch + "", x, y, anchor);
    }

    public int getClipX() {
        return (int) g.getClip().getBounds().getX();
    }

    public int getClipY() {
        return (int) g.getClip().getBounds().getY();
    }

    public int getClipWidth() {
        return (int) g.getClip().getBounds().getWidth();
    }

    public int getClipHeight() {
        return (int) g.getClip().getBounds().getHeight();
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

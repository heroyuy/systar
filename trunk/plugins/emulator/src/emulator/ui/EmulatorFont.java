package emulator.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class EmulatorFont {

    public static final int FACE_SYSTEM = 0;
    public static final int STYLE_PLAIN = Font.PLAIN;
    public static final int STYLE_BOLD = Font.BOLD;
    public static final int SIZE_SMALL = 12;
    public static final int SIZE_MEDIUM = 14;
    public static final int SIZE_LARGE = 16;
    private Font font = null;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    private EmulatorFont(int face, int style, int size) {
        font = new Font(Font.DIALOG, style, size);
    }

    public static EmulatorFont getEmulatorFont(int face, int style, int size) {
        return new EmulatorFont(face, style, size);

    }

    public static EmulatorFont getDefaultEmulatorFont() {
        return new EmulatorFont(0, STYLE_PLAIN, SIZE_SMALL);

    }

    public int stringWidth(String str) {
        EmulatorImage ei = EmulatorImage.createImage(1, 1);
        Graphics g = ei.getGraphics();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        return fm.stringWidth(str);
    }

    public int getHeight() {
        EmulatorImage ei = EmulatorImage.createImage(1, 1);
        Graphics g = ei.getGraphics();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        return fm.getHeight();
    }

    public int charWidth(char ch) {
        EmulatorImage ei = EmulatorImage.createImage(1, 1);
        Graphics g = ei.getGraphics();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        return fm.charWidth(ch);
    }
}

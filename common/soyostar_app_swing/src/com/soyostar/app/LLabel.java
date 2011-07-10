package com.soyostar.app;

/**
 *
 * @author Administrator
 */
public class LLabel extends Widget {

    private String text = "";
    private int textAnchor = Painter.LT;
    private int textColor = Color.BLACK;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextAnchor() {
        return textAnchor;
    }

    public void setTextAnchor(int textAnchor) {
        this.textAnchor = textAnchor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        painter.setColor(textColor);
        switch (textAnchor) {
            case Painter.LT:
                painter.drawString(text, 0, 0, textAnchor);
                break;
            case Painter.LV:
                painter.drawString(text, 0, getHeight() / 2, textAnchor);
                break;
            case Painter.LB:
                painter.drawString(text, 0, getHeight(), textAnchor);
                break;
            case Painter.HT:
                painter.drawString(text, getWidth() / 2, 0, textAnchor);
                break;
            case Painter.HV:
                painter.drawString(text, getWidth() / 2, getHeight() / 2, textAnchor);
                break;
            case Painter.HB:
                painter.drawString(text, getWidth() / 2, getHeight(), textAnchor);
                break;
            case Painter.RT:
                painter.drawString(text, getWidth(), 0, textAnchor);
                break;
            case Painter.RV:
                painter.drawString(text, getWidth(), getHeight() / 2, textAnchor);
                break;
            case Painter.RB:
                painter.drawString(text, getWidth(), getHeight(), textAnchor);
                break;
        }
    }
}

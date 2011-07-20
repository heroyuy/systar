/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import com.soyostar.app.event.TouchEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 文字区域
 * @author wp_g4
 */
public class LTextArea extends Widget {

    private String text = "";
    private int totalOffsetY = 0;//总偏移量
    private int curOffsetY = 0;//当前偏移量
    private List<String> texts = new ArrayList<String>();
    private boolean needUpdate = false;
    private int prevY = 0;
    private int marginTop = 0;
    private int marginBottom = 0;
    private int marginLeft = 0;
    private int marginRight = 0;
    private int textSize = -1;
    private int textColor = Color.BLACK;
    private int leading = 0;//行距

    public LTextArea(String text) {
        this.text = text;
        needUpdate = true;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        needUpdate = true;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getLeading() {
        return leading;
    }

    public void setLeading(int leading) {
        this.leading = leading;
    }

    public void setMargin(int marginTop, int marginBottom, int marginLeft, int marginRight) {
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        needUpdate = true;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        needUpdate = true;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        needUpdate = true;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        if (textSize > 0) {
            painter.setTextSize(textSize);
        }
        if (needUpdate) {
            texts.clear();
            analyse(painter, text);
//            for (String str : texts) {
//                System.out.println(str);
//            }
            curOffsetY = 0;
            totalOffsetY = texts.size() * painter.getTextSize() + (texts.size() - 1) * leading - (getHeight() - marginTop - marginBottom);
            needUpdate = false;
        }
        painter.setColor(textColor);
        painter.setClip(0, 0, getWidth(), getHeight() - marginBottom);

        for (int i = 0; i < texts.size(); i++) {
            painter.drawString(texts.get(i), marginLeft, marginTop + i * (painter.getTextSize() + leading) + curOffsetY,
                    Painter.LT);
        }
        painter.setClip(0, 0, getWidth(), getHeight());
    }

    @Override
    public boolean dispatchTouchEvent(TouchEvent te) {
        switch (te.getType()) {
            case TouchEvent.TOUCH_DOWN: {
                prevY = te.getY();
            }
            break;

            case TouchEvent.TOUCH_MOVE: {
                int distance = te.getY() - prevY;
                if (curOffsetY + distance >= -totalOffsetY && curOffsetY + distance <= 0) {
                    curOffsetY += distance;
                    prevY = te.getY();
                }
            }
            break;

        }
        return super.dispatchTouchEvent(te);
    }

    private void analyse(Painter painter, String text) {
        if (text == null || text.equals("")) {
            return;
        }
        int index = text.length();
        while (index > 0) {
            String temp = text.substring(0, index);
            if (painter.stringWidth(temp) > getWidth() - marginLeft - marginRight) {
                index--;
            } else {
                texts.add(temp);
                if (index < text.length()) {
                    analyse(painter, text.substring(index));
                }
                break;
            }
        }
    }
}

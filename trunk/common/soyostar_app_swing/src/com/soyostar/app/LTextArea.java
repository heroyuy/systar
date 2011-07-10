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

    public LTextArea(String text) {
        this.text = text;
        needUpdate = true;
    }

    @Override
    public void paint(Painter painter) {
        if (needUpdate) {
            texts.clear();
            analyse(painter, text);
            for (String str : texts) {
                System.out.println(str);
            }
            curOffsetY = 0;
            totalOffsetY = texts.size() * painter.getTextSize() - getHeight();
            needUpdate = false;
        }
        for (int i = 0; i < texts.size(); i++) {
            painter.drawString(texts.get(i), 0, i * painter.getTextSize() + curOffsetY, Painter.LT);
        }
    }

    @Override
    boolean onTouchEvent(TouchEvent te) {
        System.out.println("te.getY():" + te.getY());
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
        return true;
    }

    private void analyse(Painter painter, String text) {
        if (text == null || text.equals("")) {
            return;
        }
        int index = text.length();
        while (index > 0) {
            String temp = text.substring(0, index);
            if (painter.stringWidth(temp) > getWidth()) {
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

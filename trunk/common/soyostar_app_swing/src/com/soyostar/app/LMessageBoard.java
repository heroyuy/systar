/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class LMessageBoard extends LTextWidget {

    private List<Message> msgList = new LinkedList<Message>();
    private boolean needReload = false;
    private int maxRowNum = 0;
    private int ticker = 0;

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        needReload = true;
    }

    @Override
    public void setMarginBottum(int marginBottum) {
        super.setMarginBottum(marginBottum);
        needReload = true;
    }

    @Override
    public void setMarginTop(int marginTop) {
        super.setMarginTop(marginTop);
        needReload = true;
    }

    public void append(String msg) {
        msgList.add(new Message(msg));
        if (msgList.size() > maxRowNum) {
            msgList.remove(0);
        }
    }

    public void append(String[] msg) {
    }

    public void append(List<String> msg) {
    }

    public void clear() {
        msgList.clear();
    }

    @Override
    public void paint(Painter painter) {
        if (needReload) {
            reload();
            needReload = false;
        }
        super.paint(painter);
        painter.setTextSize(getTextSize());
        for (int i = 0; i < msgList.size(); i++) {
            //确定颜色
            int color = Color.getColor(msgList.get(i).alpha, Color.getRed(getTextColor()), Color.getGreen(getTextColor()), Color.getBlue(getTextColor()));
            painter.setColor(color);
            painter.drawString(msgList.get(i).message, getMarginLeft(), getMarginTop() + i * (getTextSize() + getLeading()), Painter.LT);
        }

        update();

    }

    private void reload() {
        //计算可以显示的总条目数
        maxRowNum = (int) Math.ceil((getHeight() - getMarginTop() - getMarginBottum()) * 1.0 / (getTextSize() + getLeading()));
        while (msgList.size() > maxRowNum) {
            msgList.remove(0);
        }
    }

    private void update() {
        ticker++;
        if (ticker % 2 == 0) {
            for(Message msg:msgList){
                msg.alpha--;
            }
        }
    }

    private class Message {

        public Message(String message) {
            this.message = message;
        }
        public String message = null;
        public int alpha = 0xff;
    }
}

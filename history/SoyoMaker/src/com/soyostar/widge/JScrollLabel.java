package com.soyostar.widge;

import javax.swing.JLabel;

public class JScrollLabel extends JLabel implements Runnable {

    private int fps = 5;

    public int getScrollSpeed() {
        return fps;
    }

    public void setScrollSpeed(int speed) {
        fps = speed;
    }

    public JScrollLabel() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public JScrollLabel(String s) {
        setText(s);
        Thread thread = new Thread(this);
        thread.start();
    }
    private boolean isTextRuning = true;

    public boolean isTextRuning() {
        return isTextRuning;
    }

    public void setTextRuning(boolean isTextRuning) {
        this.isTextRuning = isTextRuning;
    }
    String text;

    public void run() {
        try {

            while (isTextRuning) {
                text = getText();
                long time = System.currentTimeMillis();
                if (text.length() > 1) {
                    text = text.substring(1, text.length()) + text.charAt(0);
                    setText(text);
                    repaint();
                }
                time = System.currentTimeMillis() - time;
                if (time < 1000 / fps) {
                    Thread.sleep(1000 / fps - time);//保证fps维持一个定值
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

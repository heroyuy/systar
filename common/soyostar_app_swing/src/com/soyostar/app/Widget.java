package com.soyostar.app;

import com.soyostar.app.event.KeyEvent;
import com.soyostar.app.event.KeyListener;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;

/**
 * 轻量级组件超类
 * @author wp_g4
 */
public class Widget {

    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private int bgColor = 0x00000000;
    private Image backgroundImage = null;
    private boolean visibility = false;
    private TouchListener touchListener = null;
    private KeyListener keyListener = null;

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public TouchListener getTouchListener() {
        return touchListener;
    }

    public void setTouchListener(TouchListener touchListener) {
        this.touchListener = touchListener;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setBackground(int color) {
        this.bgColor = color;
    }

    public int getBackground() {
        return bgColor;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setVisible(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isVisible() {
        return visibility;
    }

    /**
     * 绘制组件。应用程序不应直接调用 paint，而是应该使用 repaint 方法来安排重绘组件。
     * @param painter 在其中进行绘制的 Painter 上下文
     */
    public void paint(Painter painter) {
        painter.setColor(bgColor);
        painter.fillRect(0, 0, width, height);
        painter.drawImage(backgroundImage, 0, 0, Painter.LT);
    }

    /**
     * 触屏事件发生时调用
     * @param touchEvent 触屏事件
     * @return 若事件处理完成不需要传递到下一层继续处理则返回true，否则返回false
     */
    boolean onTouchEvent(TouchEvent touchEvent) {
        if (touchListener != null) {
            return touchListener.onTouchEvent(this, touchEvent);
        } else {
            return true;
        }
    }

    /**
     * 按键事件发生时调用
     * @param keyEvent 按键事件
     * @return 若事件处理完成不需要传递到下一层继续处理则返回true，否则返回false
     */
    boolean onKeyEvent(KeyEvent keyEvent) {
        if (keyListener != null) {
            return keyListener.onKeyEvent(this, keyEvent);
        } else {
            return true;
        }
    }
}

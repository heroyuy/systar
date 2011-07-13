package com.soyostar.app;

import com.soyostar.app.event.TouchEvent;

/**
 * 进度条
 * @author vv
 */
public class LProgressBar extends Widget {

    private int foreground = Color.YELLOW;
    private int value = 0;   //长度
    private int maxValue = 0;//最大值
    private boolean drawable = false;

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    public int getForeground() {
        return foreground;
    }

    public void setForeground(int foregroundColor) {
        this.foreground = foregroundColor;
    }

    public int getValue() {
        return value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean dispatchTouchEvent(TouchEvent te) {
        if (drawable) {
            value = (int) (te.getX() * 1.0 / getWidth() * maxValue);
            if (value >= maxValue) {
                value = maxValue;
            }
        }
        return super.dispatchTouchEvent(te);
    }

    @Override
    public void paint(Painter painter) {
        painter.setColor(getBackground());
        painter.fillRoundRect(0, 0, getWidth(), getHeight(), 10);
        painter.setColor(foreground);
        painter.fillRoundRect(0, 0, (int) ((getValue() * 1.0) / getMaxValue() * getWidth()), getHeight(), 10);
    }
}

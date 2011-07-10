package com.soyostar.app.event;

/**
 * 触屏事件
 * @author wp_g4
 */
public class TouchEvent {

    /**
     * 触屏按下
     */
    public static final byte TOUCH_DOWN = 0;
    /**
     * 触屏移动
     */
    public static final byte TOUCH_MOVE = 1;
    /**
     * 触屏离开
     */
    public static final byte TOUCH_UP = 2;
    private int x = 0;
    private int y = 0;
    private byte type = -1;

    /**
     * 创建指定位置、指定类型的触屏事件
     * @param x 触屏事件发生的 x 坐标
     * @param y 触屏事件发生的 y 坐标
     * @param type 触屏事件的类型
     */
    public TouchEvent(int x, int y, byte type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * 获取触屏事件发生的 x 坐标
     * @return 触屏事件发生的 x 坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 获取触屏事件发生的 y 坐标
     * @return 触屏事件发生的 y 坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 获取触屏事件的类型
     * @return 触屏事件的类型
     */
    public byte getType() {
        return type;
    }
}

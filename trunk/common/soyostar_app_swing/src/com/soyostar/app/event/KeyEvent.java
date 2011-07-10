package com.soyostar.app.event;

/**
 * 按键事件
 * @author wp_g4
 */
public class KeyEvent {

    /**
     * 按键按下
     */
    public static final byte KEY_DOWN = 0;
    /**
     *  按键弹起
     */
    public static final byte KEY_UP = 1;
    private int code = -1;
    private byte type = -1;

    /**
     * 构造指定类型、指定键值的按键事件
     * @param code 按键事件的键值
     * @param type 按键事件的类型
     */
    public KeyEvent(int code, byte type) {
        this.code = code;
        this.type = type;
    }

    /**
     * 获取按键事件的键值
     * @return 按键事件的键值
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取按键事件的类型
     * @return 按键事件的类型
     */
    public byte getType() {
        return type;
    }
}

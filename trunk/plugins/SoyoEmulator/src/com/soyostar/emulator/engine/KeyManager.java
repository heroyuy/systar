package com.soyostar.emulator.engine;

/**
 *
 * 按键管理器
 */
public class KeyManager {

    public static final int KEY_NULL = 0;//无按键
    public static final int KEY_UP = -1;// 上键
    public static final int KEY_DOWN = -2;// 下键
    public static final int KEY_LEFT = -3;// 左键
    public static final int KEY_RIGHT = -4;// 右键
    public static final int KEY_MID = -5;// 中键
    public static final int KEY_LS = -6;// 左软键
    public static final int KEY_RS = -7;// 右软键
    public static final int KEY_0 = 48;// 0键
    public static final int KEY_1 = 49;// 1键
    public static final int KEY_2 = 50;// 2键
    public static final int KEY_3 = 51;// 3键
    public static final int KEY_4 = 52;// 4键
    public static final int KEY_5 = 53;// 5键
    public static final int KEY_6 = 54;// 6键
    public static final int KEY_7 = 55;// 7键
    public static final int KEY_8 = 56;// 8键
    public static final int KEY_9 = 57;// 9键
    public static final int KEY_STAR = 42;// *键
    public static final int KEY_POUND = 35;// #键
    private int key = KEY_NULL;

    protected KeyManager() {
    }

    public boolean isAnyKeyPressed() {
        return key != KEY_NULL;
    }

    public boolean isPressKey(int key) {
        return this.key == key;
    }

    protected void pressKey(int key) {
        this.key = key;
    }

    public void clearKeyBuffer() {
        key = KEY_NULL;
    }
}

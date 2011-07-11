package emulator.engine;

import emulator.engine.script.Event;

/**
 *
 * Ïàµ±ÓÚMVCÖĞµÄ¿ØÖÆÆ÷£¨C£©
 */
public interface Control {

//    public static final int KEY_UP = -1;// ÉÏ¼ü
//    public static final int KEY_DOWN = -2;// ÏÂ¼ü
//    public static final int KEY_LEFT = -3;// ×ó¼ü
//    public static final int KEY_RIGHT = -4;// ÓÒ¼ü
//    public static final int KEY_FIRE = -5;// ÖĞ¼ü
//    public static final int KEY_LS = -6;// ×óÈí¼ü
//    public static final int KEY_RS = -7;// ÓÒÈí¼ü
//    public static final int KEY_0 = 48;// 0¼ü
//    public static final int KEY_1 = 49;// 1¼ü
//    public static final int KEY_2 = 50;// 2¼ü
//    public static final int KEY_3 = 51;// 3¼ü
//    public static final int KEY_4 = 52;// 4¼ü
//    public static final int KEY_5 = 53;// 5¼ü
//    public static final int KEY_6 = 54;// 6¼ü
//    public static final int KEY_7 = 55;// 7¼ü
//    public static final int KEY_8 = 56;// 8¼ü
//    public static final int KEY_9 = 57;// 9¼ü
//    public static final int KEY_STAR = 42;// *¼ü
//    public static final int KEY_POUND = 35;// #¼ü

    public void keyPressed(View view, int key);

    public void dealEvent(View view, Event event);
}

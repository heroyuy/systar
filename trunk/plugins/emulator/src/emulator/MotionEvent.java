/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator;

/**
 *
 * @author Administrator
 */
public class MotionEvent {

    public static final byte MOTION_DOWN = 0;
    public static final byte MOTION_MOVE = 1;
    public static final byte MOTION_UP = 2;
    private int x;
    private int y;
    private byte type;

    public MotionEvent(int x, int y, byte type) {
        if (type > MOTION_UP || type < MOTION_DOWN) {
            throw new IllegalArgumentException("类型不正确");
        }
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public byte getType() {
        return type;
    }
}

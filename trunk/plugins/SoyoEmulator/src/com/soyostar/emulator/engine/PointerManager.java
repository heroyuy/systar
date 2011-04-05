package com.soyostar.emulator.engine;

import com.soyostar.ui.Rect;
import java.util.ArrayList;

/**
 *
 * 触控管理器
 */
public class PointerManager {

    public final static int STATE_NULL = 0;
    public final static int STATE_DOWN = 1;
    public final static int STATE_MOVE = 2;
    public final static int STATE_UP = 3;
    private int state = STATE_NULL;
    private ArrayList rects = null;
    private int x = -1;
    private int y = -1;

    protected PointerManager() {
        rects = new ArrayList();
    }

    protected void onMotion(int state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;
    }

    public boolean isScreenTouched() {
        return x != -1 && y != -1;
    }

    public int getTouchIndex() {
        int index = -1;
        for (int i = 0; i < rects.size(); i++) {
            if (((Rect) rects.get(i)).containsPoint(x, y)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addRect(Rect rect) {
        rects.add(rect);
    }

    public void addRect(Rect[] rectArray) {
        for (int i = 0; i < rectArray.length; i++) {
            rects.add(rectArray[i]);
        }
    }

    public void clear() {
        x = -1;
        y = -1;
        state = STATE_NULL;
    }

    public int getState() {
        return state;
    }

    public void release() {
        clear();
        rects.clear();
    }
}

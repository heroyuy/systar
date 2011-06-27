/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

import com.soyostar.app.Rect;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TouchDelegate {

    private static TouchDelegate defaultTouchDelegate = new TouchDelegate();

    public static TouchDelegate getDefaultTouchDelegate() {
        return defaultTouchDelegate;
    }
    private ArrayList<Rect> rects = new ArrayList<Rect>();

    public void addTouchRect(Rect rect) {
        rects.add(rect);
    }

    public void addTouchRect(Rect[] rects) {
        for (Rect rect : rects) {
            this.rects.add(rect);
        }
    }

    public void clearAllTouchRect() {
        rects.clear();
    }

    public int getTouchRectIndex(int x, int y) {
        int index = -1;
        for (int i = 0; i < rects.size(); i++) {
            if (isInRect(x, y, rects.get(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean isInRect(int x, int y, Rect rect) {
        return x > rect.getX() && x < rect.getX() + rect.getWidth() && y > rect.getY() && y < rect.getY() + rect.getHeight();
    }
}

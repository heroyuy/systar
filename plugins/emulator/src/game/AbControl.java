/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import emulator.EmulatorGraphics;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public abstract class AbControl implements Control {

    private ArrayList<View> views = null;

    public AbControl() {
        views = new ArrayList<View>();
    }

    public void addView(View view) {
        views.add(view);
    }

    public void removeView(View view) {
        views.remove(view);
    }

    public void removeView(int index) {
        views.remove(index);
    }

    public void removeAllView() {
        views.clear();
    }

    public void updateView(EmulatorGraphics g) {
        System.out.println("更新视图");
        for (View view : views) {
            view.paint(g);
        }
    }

    public void onObtain() {
        System.out.println(this.getClass().getName() + "获取游戏控制权");
        for (View view : views) {
            view.init();
        }
    }

    public void onLose() {
        System.out.println(this.getClass().getName() + "失去游戏控制权");
        for (View view : views) {
            view.release();
        }
    }

    private class ViewNode {

        public int id = -1;
        public String name = null;
        public View view = null;
    }
}

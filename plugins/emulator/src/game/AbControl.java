/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import emulator.ui.EmulatorGraphics;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public abstract class AbControl implements Control {

    private Map<Integer, ViewNode> views = null;

    public AbControl() {
        views = new HashMap<Integer, ViewNode>();
    }

    public void addView(int id, String name) {
        views.put(id, new ViewNode(id, name));
    }

    public View getView(int id) {
        return views.get(id).view;
    }

    public View getView(String name) {
        View view = null;
        for (ViewNode vn : views.values()) {
            if (vn.name.equals(name)) {
                view = vn.view;
                break;
            }
        }
        return view;
    }

    public void removeView(int id) {
        views.remove(id);
    }

    public void removeView(String name) {
        for (ViewNode vn : views.values()) {
            if (vn.name.equals(name)) {
                views.remove(vn.id);
            }
        }
    }

    public void removeAllView() {
        views.clear();
    }

    public void updateView(EmulatorGraphics g) {
//        System.out.println("更新视图");
        for (ViewNode vn : views.values()) {
            vn.view.paint(g);
        }
    }

    public void onObtain() {
        System.out.println(this.getClass().getName() + "获取游戏控制权");
        for (ViewNode vn : views.values()) {
            vn.view.init();
        }
    }

    public void onLose() {
        System.out.println(this.getClass().getName() + "失去游戏控制权");
        for (ViewNode vn : views.values()) {
            vn.view.release();
        }
    }

    private class ViewNode {

        public ViewNode(int id, String name) {
            this.id = id;
            this.name = name;
            try {
                this.view = (View) Class.forName(name).newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(AbControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AbControl.class.getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AbControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public int id = -1;
        public String name = null;
        public View view = null;
    }
}

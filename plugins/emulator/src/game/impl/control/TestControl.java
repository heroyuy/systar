/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.control;

import emulator.MotionEvent;
import engine.script.GameEvent;
import game.AbControl;

/**
 *
 * @author Administrator
 */
public class TestControl extends AbControl {

    public void dealKeyEvent(int key) {
        System.out.println("按键事件：" + key);
    }

    public void onTouchEvent(MotionEvent me) {
        System.out.println("触屏事件：x=" + me.getX() + " y=" + me.getY() + " 类型：" + me.getType());
    }

    public void dealGameEvent(GameEvent event) {
        System.out.println("游戏事件：" + event);
    }

    public void updateModel() {
        System.out.println("控制器更新Model");
    }
}

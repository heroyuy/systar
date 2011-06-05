package engine;

import emulator.ui.EmulatorGraphics;
import emulator.MotionEvent;

/**
 * 游戏抽象类，所有游戏实体必需继承自此类
 * @author g4 
 *
 */
public abstract class Game {

    /**
     * 通过脚本ID运行脚本
     * @param id
     */
    public void runScript(int id) {
    }

    /**
     * 开始游戏
     *
     */
    public abstract void start();

    /**
     * 更新游戏，每个心跳周期由游戏引擎调用一次，先于render(Graphics g) 被调用
     */
    public abstract void update();

    /**
     * 处理触屏事件的回调方法
     */
    public abstract void onTouchEvent(MotionEvent me);

    /**
     * 处理按键事件的回调方法
     */
    public abstract void dealKeyEvent(int key);

    /**
     * 渲染游戏，每个心跳周期由游戏引擎调用一次
     * @param g
     */
    public abstract void render(EmulatorGraphics g);

    /**
     * 退出游戏
     *
     */
    public abstract void exit();
}

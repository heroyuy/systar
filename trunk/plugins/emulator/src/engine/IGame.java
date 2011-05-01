package engine;

import emulator.EmulatorGraphics;
import emulator.MotionEvent;
import engine.script.GameEvent;

/**
 *
 * @author g4 游戏接口，所有游戏实体必需实现
 *
 */
public interface IGame {

    /**
     * 开始游戏
     *
     */
    public void start();

    /**
     * 更新游戏，每个心跳周期由游戏引擎调用一次，先于render(Graphics g) 被调用
     */
    public void update();

    /**
     * 处理游戏事件的回调方法
     * @param event 待处理的游戏事件
     * @return 如果事件得到处理则返回true,否则返回false
     */
    public boolean dealGameEvent(GameEvent event);

    /**
     * 处理按键事件的回调方法
     */
    public void dealKeyEvent(int key);

    /**
     * 处理触屏事件的回调方法
     */
    public void onTouchEvent(MotionEvent me);

    /**
     * 退出游戏
     *
     */
    public void exit();

    /**
     * 渲染游戏，每个心跳周期由游戏引擎调用一次
     * @param g
     */
    public void render(EmulatorGraphics g);
}

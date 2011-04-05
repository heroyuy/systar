package com.soyostar.emulator.engine;

import com.soyostar.emulator.engine.script.Event;

/**
 *
 * 相当于MVC中的控制器（C）
 */
public interface Control {

    /**
     * 按键事件
     */
    public void dealKeyEvent();

    /**
     * 触屏事件
     */
    public void dealMotion();

    /**
     * 游戏事件
     * @param event
     */
    public void dealGameEvent(Event event);
}

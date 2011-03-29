package com.soyostar.emulator.engine;

import com.soyostar.emulator.engine.script.Event;
import com.soyostar.ui.Painter;

/**
 *
 * 视图接口，相当于MVC中的V
 */
public interface View {

    /**
     * 初始化视图
     */
    public void init();

    /**
     * 释放视图
     */
    public void release();

    /**
     * 绘制视图
     * @param painter
     */
    public void paint(Painter painter);

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

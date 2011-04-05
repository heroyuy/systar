package com.soyostar.emulator.engine;


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

}

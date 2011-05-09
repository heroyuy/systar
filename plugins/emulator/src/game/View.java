package game;

import emulator.ui.EmulatorGraphics;


/**
 *
 * 视图接口，相当于MVC中的V
 */
public interface View {


    /**
     * 初始化视图
     *
     */
    public void init();

    /**
     * 绘制视图
     *
     * @param g
     *            画笔
     */
    public void paint(EmulatorGraphics eg);

    /**
     * 释放视图资源
     *
     */
    public void release();

}

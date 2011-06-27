package game;

import com.soyostar.app.Painter;



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
    public void paint(Painter p);

    /**
     * 释放视图资源
     *
     */
    public void release();

}

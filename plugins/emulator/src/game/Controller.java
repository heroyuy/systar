package game;

import engine.Render;

/**
 *
 * 相当于MVC中的控制器（C）
 */
public interface Controller extends Render {

    /**
     * 获取控制权时的回调方法
     */
    public void onObtain();

    /**
     * 失去控制权时的回调方法
     */
    public void onLose();

    /**
     * 更新Model
     */
    public void updateModel();
}

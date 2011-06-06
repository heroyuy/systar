package game;

import emulator.ui.EmulatorGraphics;
import emulator.MotionEvent;

/**
 *
 * 相当于MVC中的控制器（C）
 */
public interface Control {

    /**
     * 获取控制权时的回调方法
     */
    public void onObtain();

    /**
     * 失去控制权时的回调方法
     */
    public void onLose();

    /**
     * 处理按键事件
     * @param key
     */
    public void dealKeyEvent(int key);

    /**
     * 处理触屏事件
     * @param me
     */
    public void onTouchEvent(MotionEvent me);


    /**
     * 更新Model
     */
    public void updateModel();

    /**
     * 更新View
     */
    public void updateView(EmulatorGraphics g);
}

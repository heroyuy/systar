package game;

import com.soyostar.app.KeyEvent;
import com.soyostar.app.Painter;
import com.soyostar.app.TouchEvent;

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
     * 处理触屏事件
     * @param me
     */
    public void onTouchEvent(TouchEvent te);

    /**
     * 更新Model
     */
    public void updateModel();

    /**
     * 更新View
     */
    public void updateView(Painter p);

    /**
     * 处理按键事件
     * @param ke
     */
    public void onKeyEvent(KeyEvent ke);
}

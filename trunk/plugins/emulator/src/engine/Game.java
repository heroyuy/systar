package engine;

import com.soyostar.app.Component;
import com.soyostar.app.Widget;

/**
 * 游戏抽象类，所有游戏实体必需继承自此类
 * @author g4 
 *
 */
public abstract class Game implements Render {

    private Render render = null;

    public Game(Render render) {
        this.render = render;
    }

    /**
     * 开始游戏
     *
     */
    public abstract void start();

    /**
     * 更新游戏
     */
    public abstract void update();

    /**
     * 退出游戏
     *
     */
    public abstract void exit();

    /**
     * 添加组件
     * @param component 待添加的组件
     */
    public void addComponent(Component component) {
        render.addComponent(component);
    }

    ;
    /**
     * 移除组件
     * @param component 待移除的组件
     */
    public void removeComponent(Component component) {
        render.removeComponent(component);
    }

    ;
    /**
     * 移除所有组件
     * 
     */
    public void removeAllComponents() {
        render.removeAllComponents();
    }

    ;
    /**
     * 添加小部件
     * @param component 待添加的小部件
     */
    public void addWidget(Widget widget) {
        render.addWidget(widget);
    }

    ;
    /**
     * 移除小部件
     * @param component 待移除的小部件
     */
    public void removeWidget(Widget widget) {
        render.removeWidget(widget);
    }

    ;
    /**
     * 移除所有小部件
     */
    public void removeAllWidgets() {
        render.removeAllWidgets();
    }

    /**
     * 获取当前Render对象
     * @return render对象
     */
    public Render getRender() {
        return render;
    }

    /**
     * 设置当前render对象
     * @param render
     */
    public void setRender(Render render) {
        this.render = render;
    }
}

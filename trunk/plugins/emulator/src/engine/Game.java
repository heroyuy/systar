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

    public void addComponent(Component component) {
        render.addComponent(component);
    }

    ;

    public void removeComponent(Component component) {
        render.removeComponent(component);
    }

    ;

    public void removeAllComponents() {
        render.removeAllComponents();
    }

    ;

    public void addWidget(Widget widget) {
        render.addWidget(widget);
    }

    ;

    public void removeWidget(Widget widget) {
        render.removeWidget(widget);
    }

    ;

    public void removeAllWidgets() {
        render.removeAllWidgets();
    }

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }
}

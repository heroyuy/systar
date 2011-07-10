package com.soyostar.app;

import com.soyostar.app.event.TouchEvent;

/**
 * 容器。一个可包含其他组件的组件。
 * @author wp_g4
 */
public class Container extends Component {

    private Layer layer = null;

    public Container() {
        layer = new Layer();
        layer.setBackground(Color.GRAY);
        layer.setVisible(true);
    }

    /**
     * 将指定组件追加到此容器的尾部。
     * @param component 要添加的组件 
     */
    public void addComponent(Component component) {
        content.add(component.content);
    }

    /**
     * 从此容器中移除指定组件。
     * @param component 要移除的组件
     */
    public void removeComponent(Component component) {
        content.remove(component.content);
    }

    /**
     * 从此容器中移除所有组件。
     */
    public void removeAllComponents() {
        content.removeAll();
    }

    /**
     * 为容器添加指定的层
     * @param widget 要添加到容器的层
     */
    public void addWidget(Widget widget) {
        this.layer.addWidget(widget);
    }

    /**
     * 从容器中移除指定的层
     * @param widget 要移除的层
     */
    public void removeWidget(Widget widget) {
        this.layer.removeWidget(widget);
    }

    /**
     * 移除容器中的所有层
     */
    public void removeAllWidgets() {
        this.layer.removeAllWidgets();
    }

    /**
     * 调整组件的大小，使其宽度为 width。
     * @param width 组件的新宽度，单位是像素
     */
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        layer.setWidth(width);
    }

    /**
     *  调整组件的大小，使其高度为 height。
     * @param height 组件的新高度，单位是像素
     */
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        layer.setHeight(height);
    }

    /**
     * 调整组件的大小，使其宽度为 width，高度为 height。
     * @param width 组件的新宽度，单位是像素
     * @param height 组件的新高度，单位是像素
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        layer.setSize(width, height);
    }

    /**
     * 绘制组件。应用程序不应直接调用 paint，而是应该使用 repaint 方法来安排重绘组件。
     * 此方法会调用 paintSelf(Painter painter)
     * @param painter 在其中进行绘制的 Painter 上下文
     */
    @Override
    public final void paint(Painter painter) {
        paintSelf(painter);
        paintLayer(painter);
    }

    /**
     * 触屏事件发生时调用
     * @param touchEvent 触屏事件
     */
    @Override
    public final void onTouchEvent(TouchEvent touchEvent) {
        if (!onTouchEventLayer(touchEvent)) {
            onTouchEventSelf(touchEvent);
        }
    }

    /**
     * 绘制组件自身。Container的子类不应该覆盖paint方法，而是覆盖paintSelf方法
     * @param painter 画笔
     */
    protected void paintSelf(Painter painter) {
    }

    protected void onTouchEventSelf(TouchEvent touchEvent) {
    }

    private void paintLayer(Painter painter) {
        layer.paint(painter);
    }

    private boolean onTouchEventLayer(TouchEvent touchEvent) {
        return layer.onTouchEvent(touchEvent);
    }
}

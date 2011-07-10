package com.soyostar.app;

import com.soyostar.app.event.KeyEvent;
import com.soyostar.app.event.TouchEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 层
 * @author wp_g4
 */
public class Layer extends Widget {

    private List<Widget> widgets = new ArrayList<Widget>();

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    public void removeWidget(Widget widget) {
        widgets.remove(widget);
    }

    public void removeAllWidgets() {
        widgets.clear();
    }

    @Override
    public final void paint(Painter painter) {

        super.paint(painter);
        //实现类的绘制自身
        paintSelf(painter);
        //绘制子Layer
        paintWidgets(painter);


    }

    protected void paintSelf(Painter painter) {
    }

    private void paintWidgets(Painter painter) {
        for (Widget widget : widgets) {
            if (widget.isVisible()) {
                //设置坐标系
                ((GraphicsPainter) painter).setBasePoint(widget.getX(), widget.getY());
                //设置裁剪区
                Rect clip = ((GraphicsPainter) painter).getClip();
                ((GraphicsPainter) painter).clipRect(0, 0, widget.getWidth(), widget.getHeight());
                widget.paint(painter);
                //还原裁剪区
                ((GraphicsPainter) painter).setClip(clip);
                //还原坐标系
                ((GraphicsPainter) painter).setBasePoint(-widget.getX(), -widget.getY());
            }
        }
    }

    @Override
    boolean onTouchEvent(TouchEvent touchEvent) {
        boolean state = false;
        if (!onTouchEventWidgets(touchEvent)) {
            state = onTouchEventSelf(touchEvent);
        }
        return state;
    }

    private boolean onTouchEventSelf(TouchEvent touchEvent) {
        boolean state = false;
        if (getTouchListener() != null) {
            state = getTouchListener().onTouchEvent(this, touchEvent);
        }
        return state;
    }

    private boolean onTouchEventWidgets(TouchEvent touchEvent) {

        //事件处理从上到下，后添加到list中的在上
        boolean state = false;
        //从最上层开始遍历
        for (int i = widgets.size() - 1; i >= 0; i--) {
            //如果遍历到的层可见并且事件发这个层的范围内
            if (widgets.get(i).isVisible() && isTouchEventInLayer(widgets.get(i), touchEvent)) {
                state = widgets.get(i).onTouchEvent(new TouchEvent(touchEvent.getX() - widgets.get(i).getX(), touchEvent.getY() - widgets.get(i).getY(), touchEvent.getType()));
                //如果事件处理完成
                if (state) {
                    break;
                }
            }
        }

        return state;
    }

    @Override
    boolean onKeyEvent(KeyEvent keyEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean isTouchEventInLayer(Widget layer, TouchEvent touchEvent) {

        return touchEvent.getX() >= layer.getX() && touchEvent.getX() <= layer.getX() + layer.getWidth()
                && touchEvent.getY() >= layer.getY() && touchEvent.getY() <= layer.getY() + layer.getHeight();
    }
}

package com.soyostar.app;

import com.soyostar.app.event.TouchEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 层
 * @author wp_g4
 */
public class Layer extends Widget {

    private List<Widget> widgets = new ArrayList<Widget>();
    private Widget focusWidget = null;

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
    public void paintWidget(Painter painter) {
        super.paintWidget(painter);
        paintChildren(painter);
    }

    private void paintChildren(Painter painter) {
        for (Widget widget : widgets) {
            if (widget.isVisible()) {
                //设置坐标系
                ((GraphicsPainter) painter).setBasePoint(widget.getX(), widget.getY());
                //设置裁剪区
                Rect clip = painter.getClip();
                ((GraphicsPainter) painter).clipRect(0, 0, widget.getWidth(), widget.getHeight());
                ((GraphicsPainter) painter).setCurClip(painter.getClip());
                widget.paintWidget(painter);
                //还原裁剪区
                ((GraphicsPainter) painter).forceClip(clip);
                //还原坐标系
                ((GraphicsPainter) painter).setBasePoint(-widget.getX(), -widget.getY());
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(TouchEvent te) {
//        System.out.println("Layer-dispatchTouchEvent type:" + te.getType() + "  x:" + te.getX() + " y:" + te.getY());
        boolean state = false;
        //容器对事件进行一次拦截处理
        state = interceptTouchEvent(te);
        if (!state) {
            //如果拦截处理没有消费掉事件
            switch (te.getType()) {
                case TouchEvent.TOUCH_DOWN: {
                    //如果是DOWN事件，则寻找焦点组件
                    focusWidget = searchFocusWidget(te);
                    if (focusWidget != null) {
                        //焦点在子组件上
                        if (!focusWidget.dispatchTouchEvent(new TouchEvent(te.getX() - focusWidget.getX(), te.getY() - focusWidget.getY(), te.getType()))) {
                            //如果子组件没有处理完，则本组件继续处理，并且子组件失去焦点
                            focusWidget = null;
                            state = super.dispatchTouchEvent(te);
                        } else {
                            state = true;
                        }

                    } else {
                        //焦点不在子组件上
                        state = super.dispatchTouchEvent(te);
                    }
                }
                break;
                case TouchEvent.TOUCH_MOVE: {
                    if (focusWidget != null) {
                        //焦点在子组件上
                        if (!focusWidget.dispatchTouchEvent(new TouchEvent(te.getX() - focusWidget.getX(), te.getY() - focusWidget.getY(), te.getType()))) {
                            //如果子组件没有处理完，则本组件继续处理，并且子组件失去焦点
                            focusWidget = null;
                            state = super.dispatchTouchEvent(te);
                        } else {
                            state = true;
                        }

                    } else {
                        //焦点不在子组件上
                        state = super.dispatchTouchEvent(te);
                    }
                }
                break;
                case TouchEvent.TOUCH_UP: {
                    if (focusWidget != null) {
                        //焦点在子组件上
                        if (!focusWidget.dispatchTouchEvent(new TouchEvent(te.getX() - focusWidget.getX(), te.getY() - focusWidget.getY(), te.getType()))) {
                            //如果子组件没有处理完，则本组件继续处理，并且子组件失去焦点
                            focusWidget = null;
                            state = super.dispatchTouchEvent(te);
                        } else {
                            //事件处理结束，清空焦点
                            focusWidget = null;
                            state = true;
                        }

                    } else {
                        //焦点不在子组件上
                        state = super.dispatchTouchEvent(te);
                    }
                }
                break;
            }

        }
        return state;
    }

    public boolean interceptTouchEvent(TouchEvent te) {
        return false;
    }

    private Widget searchFocusWidget(TouchEvent te) {
        Widget focus = null;
        //按添加到容器的顺序从上到下依次探查
        for (int i = widgets.size() - 1; i >= 0; i--) {
            if (widgets.get(i).isVisible() && te.getX() >= widgets.get(i).getX() && te.getX() <= widgets.get(i).getX() + widgets.get(i).getWidth()
                    && te.getY() >= widgets.get(i).getY() && te.getY() <= widgets.get(i).getY() + widgets.get(i).getHeight()) {
                focus = widgets.get(i);
                break;
            }
        }
//        System.out.println("focus:" + focus);
        return focus;
    }
}

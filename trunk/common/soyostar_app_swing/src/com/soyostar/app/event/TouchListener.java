package com.soyostar.app.event;

/**
 *
 * @author Administrator
 */
public interface TouchListener<T> {

    /**
     * 触屏事件发生时调用
     * @param touchEvent 触屏事件
     */
    public boolean onTouchEvent(T source, TouchEvent touchEvent);
}

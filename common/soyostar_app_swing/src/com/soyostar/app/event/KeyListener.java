package com.soyostar.app.event;

/**
 *
 * @author Administrator
 */
public interface KeyListener<T> {

    /**
     * 按键事件发生时调用
     * @param keyEvent 按键事件
     */
    public boolean onKeyEvent(T source,KeyEvent keyEvent);
}

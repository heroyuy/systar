package com.soyomaker.observer;

/**
 * 观察者接口
 *
 * @author cokey
 */
public interface Observer {

    /**
     *
     * @param event
     */
    public void handleEvent(Event event);
}

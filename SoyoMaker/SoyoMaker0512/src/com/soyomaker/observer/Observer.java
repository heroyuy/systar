package com.soyomaker.observer;

/**
 *
 * @author Administrator
 */
public interface Observer {

    /**
     *
     * @param event
     */
    public void handleEvent(Event event);
}

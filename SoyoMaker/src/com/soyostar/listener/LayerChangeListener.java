/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface LayerChangeListener extends EventListener {

    void layerChanged(LayerChangedEvent event);

    void visibleChanged(LayerChangedEvent event, boolean nowBool);

    void nameChanged(LayerChangedEvent event, String oldName, String newName);
}

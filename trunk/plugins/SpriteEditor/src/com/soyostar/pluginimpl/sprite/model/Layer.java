/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import com.soyostar.pluginimpl.sprite.listener.LayerChangeListener;
import com.soyostar.pluginimpl.sprite.listener.LayerChangedEvent;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public abstract class Layer {

    protected Rectangle bound = new Rectangle(0, 0, 0, 0);
    protected boolean visible = true;
    protected String name = "新建图层";
    protected final List<LayerChangeListener> layerChangeListeners = new LinkedList();

    public void addLayerChangeListener(LayerChangeListener listener) {
        layerChangeListeners.add(listener);
//        System.out.println("ls:" + layerChangeListeners.size());
    }

    public void removeLayerChangeListener(LayerChangeListener listener) {
        layerChangeListeners.remove(listener);
    }

    protected void fireLayerChanged() {
        LayerChangedEvent event = new LayerChangedEvent(this);
        for (LayerChangeListener listener : layerChangeListeners) {
            listener.layerChanged(event);
        }
    }

    protected void fireNameChanged(String oldName, String newName) {
        LayerChangedEvent event = new LayerChangedEvent(this);
        for (LayerChangeListener listener : layerChangeListeners) {
            listener.nameChanged(event, oldName, newName);
        }
    }

    protected void fireVisibleChanged(boolean newBool) {
        LayerChangedEvent event = new LayerChangedEvent(this);

        for (LayerChangeListener listener : layerChangeListeners) {
            listener.visibleChanged(event, newBool);
        }
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
        fireLayerChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String na = this.name;
        this.name = name;
        fireNameChanged(na, name);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        fireVisibleChanged(visible);
    }
}

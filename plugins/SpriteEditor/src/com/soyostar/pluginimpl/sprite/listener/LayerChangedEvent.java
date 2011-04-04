/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Layer;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class LayerChangedEvent extends EventObject {

    public LayerChangedEvent(Layer ani) {
        super(ani);
    }

    public Layer getLayer() {
        return (Layer) getSource();
    }
}

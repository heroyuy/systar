/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

import com.soyostar.model.map.Layer;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class LayerChangedEvent extends EventObject {

    public LayerChangedEvent(Layer layer) {
        super(layer);
    }

    public Layer getLayer() {
        return (Layer) getSource();
    }
}

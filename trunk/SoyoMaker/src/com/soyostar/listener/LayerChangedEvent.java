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

    /**
     * 
     * @param layer
     */
    public LayerChangedEvent(Layer layer) {
        super(layer);
    }

    /**
     * 
     * @return
     */
    public Layer getLayer() {
        return (Layer) getSource();
    }
}

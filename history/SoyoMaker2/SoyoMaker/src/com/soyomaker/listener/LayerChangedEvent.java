/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.listener;

import com.soyomaker.model.map.Layer;
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

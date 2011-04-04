/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Layer;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface FrameChangeListener extends EventListener {

    public void frameChanged(FrameChangedEvent e);

    public void nameChanged(FrameChangedEvent e, String oname,String nname);

    public void layerAdded(FrameChangedEvent e, Layer layer);

    public void layerRemoved(FrameChangedEvent e, int index);
}

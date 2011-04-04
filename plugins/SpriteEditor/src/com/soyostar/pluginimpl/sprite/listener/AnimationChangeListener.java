/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Action;
import com.soyostar.pluginimpl.sprite.model.Frame;
import com.soyostar.pluginimpl.sprite.model.TileSet;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface AnimationChangeListener extends EventListener {

    public void animationChanged(AnimationChangedEvent e);

    public void actionAdded(AnimationChangedEvent e, Action action);

    public void actionRemoved(AnimationChangedEvent e, int index);

    public void tileSetAdded(AnimationChangedEvent e, TileSet tileset);

    public void tileSetRemoved(AnimationChangedEvent e, int index);

    public void frameAdded(AnimationChangedEvent e, Frame frame);

    public void frameRemoved(AnimationChangedEvent e, int index);
}

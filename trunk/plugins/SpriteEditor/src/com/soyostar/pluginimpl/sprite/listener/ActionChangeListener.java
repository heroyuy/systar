/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Sequence;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface ActionChangeListener extends EventListener {

    public void actionChanged(ActionChangedEvent e);

    public void nameChanged(ActionChangedEvent e, String oname, String nname);

    public void sequenceAdded(ActionChangedEvent e, Sequence seq);

    public void sequenceRemoved(ActionChangedEvent e, int index);
}

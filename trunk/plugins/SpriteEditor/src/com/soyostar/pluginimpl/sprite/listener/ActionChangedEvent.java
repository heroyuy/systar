/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Action;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class ActionChangedEvent extends EventObject {

    public ActionChangedEvent(Action ani) {
        super(ani);
    }

    public Action getAction() {
        return (Action) getSource();
    }
}

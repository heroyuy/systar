/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Sequence;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class SequenceChangedEvent extends EventObject {

    public SequenceChangedEvent(Sequence ani) {
        super(ani);
    }

    public Sequence getSequence() {
        return (Sequence) getSource();
    }
}

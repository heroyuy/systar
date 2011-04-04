/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Frame;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class FrameChangedEvent  extends EventObject{
    public FrameChangedEvent(Frame ani) {
        super(ani);
    }

    public Frame getFrame() {
        return (Frame) getSource();
    }
}

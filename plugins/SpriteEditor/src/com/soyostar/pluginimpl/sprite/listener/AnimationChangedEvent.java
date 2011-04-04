/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.listener;

import com.soyostar.pluginimpl.sprite.model.Animation;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class AnimationChangedEvent extends EventObject {

    public AnimationChangedEvent(Animation ani) {
        super(ani);
    }

    public Animation getAnimation() {
        return (Animation) getSource();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.emulator.engine.animation.Animation;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class AnimationDao implements Dao {

    private HashMap animations = null;

    public Animation getAnimation(int index) {
        return (Animation) animations.get(index);
    }

    public Animation[] getAnimationList() {
        return (Animation[]) animations.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.listener;

import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Picture;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface ProjectAnimationChangeListener extends EventListener {

    /**
     *
     * @param e
     * @param ani
     */
    public void animationAdded(ProjectAnimationChangedEvent e, Animation ani);

    /**
     *
     * @param e
     * @param pic
     */
    public void pictureAdded(ProjectAnimationChangedEvent e, Picture pic);
        /**
     *
     * @param e
     * @param index
     */
    public void animationRemoved(ProjectAnimationChangedEvent e, int index);

    /**
     *
     * @param e
     * @param index
     */
    public void pictureRemoved(ProjectAnimationChangedEvent e, int index);
}

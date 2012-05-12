/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.io.animation;

import com.soyomaker.model.animation.Animation;

/**
 *
 * @author Administrator
 */
public interface IAnimationReader {
    /**
     *
     * @param aniFile
     * @throws Exception
     */
    public void readAnimation(String aniFile) throws Exception;
}

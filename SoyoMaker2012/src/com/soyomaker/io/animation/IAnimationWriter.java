/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.animation;

/**
 *
 * @author Administrator
 */
public interface IAnimationWriter {

    /**
     * Saves a ani to a file.
     * @param filename the filename of the ani file
     * @throws Exception
     */
    public void writeAnimation(String filename) throws Exception;
}

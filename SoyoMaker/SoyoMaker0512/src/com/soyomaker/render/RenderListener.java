/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.render;

import java.awt.Point;

/**
 *
 * @author Administrator
 */
public interface RenderListener {

    /**
     *
     * @param p
     */
    public void focusPointChanged(Point p);

    /**
     * 
     * @param p
     */
    public void pressPointChanged(Point p);
}

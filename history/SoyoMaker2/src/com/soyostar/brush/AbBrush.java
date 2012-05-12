/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.brush;

import com.soyostar.model.map.Map;
import com.soyostar.model.map.SelectionLayer;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author Administrator
 */
public abstract class AbBrush extends Map implements IBrush {

    /**
     *
     */
    protected int numLayers = 1;
    /**
     *
     */
    protected Map affectedMp;
    /**
     *
     */
    protected int initLayer;
    /**
     *
     */
    protected boolean paintingStarted = false;

    /**
     *
     */
    public AbBrush() {
    }

    /**
     *
     * @param ab
     */
    public AbBrush(AbBrush ab) {
        numLayers = ab.numLayers;
    }

    /**
     *
     * @return
     */
    public boolean isPaintingStarted() {
        return paintingStarted;
    }

    /**
     * This will set the number of layers to affect, the default is 1 - the
     * layer specified in commitPaint.
     *
     * @see Brush#doPaint(int, int)
     * @param num   the number of layers to affect.
     */
    public void setAffectedLayers(int num) {
        numLayers = num;
    }

    public int getAffectedLayers() {
        return numLayers;
    }

    public void startPaint(Map mp, int x, int y, int button, int layer) {
        affectedMp = mp;
        initLayer = layer;
        paintingStarted = true;
    }

    public Rectangle doPaint(int x, int y) throws Exception {

        if (!paintingStarted) {
            throw new Exception("Attempted to call doPaint() without calling startPaint()!");
        }
        return null;
    }

    public void endPaint() {
        paintingStarted = false;
    }

    /**
     *
     * @return
     */
    public abstract Shape getShape();
}

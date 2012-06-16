/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.brush;

import com.soyomaker.model.map.Layer;
import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public abstract class AbBrush implements IBrush {

    /**
     *
     */
    protected Layer affectedLayer;
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
     * @return
     */
    public boolean isPaintingStarted() {
        return paintingStarted;
    }

    public void startPaint(Layer mp, int x, int y, int button) {
        affectedLayer = mp;
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
}

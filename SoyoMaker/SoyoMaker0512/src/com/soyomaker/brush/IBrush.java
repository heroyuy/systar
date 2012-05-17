/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.brush;

import com.soyomaker.model.map.Layer;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author Administrator
 */
public interface IBrush {

    /**
     * Returns the bounds of this brush. This is used for determining the area
     * to redraw when the brush moves.
     * @return
     */
    public Shape getShape();

    /**
     * Called before painting operation starts. This is when the mouse is
     * initially pressed.
     *
     * @param mp      the MultilayerPlane to be affected.
     * @param x       the tile x-coordinate where the user initiated the paint.
     * @param y       the tile y-coordinate where the user initiated the paint.
     * @param button  the mouse button that was used.
     * @param layer   the selected layer.
     *
     * @see MultilayerPlane
     */
    public void startPaint(
            Layer mp, int x, int y, int button);

    /**
     * This is the main processing method for a brush. This method should only
     * be called between calls to startPaint and endPaint.
     *
     * @param x       the tile x-coordinate of the mouse.
     * @param y       the tile y-coordinate of the mouse.
     *
     * @return the rectangular region affected by the painting, used to
     *         determine which area to redraw.
     * @throws Exception
     */
    public Rectangle doPaint(int x, int y) throws Exception;

    /**
     * Called when painting operation finishes. This is when the mouse is
     * released.
     */
    public void endPaint();
}
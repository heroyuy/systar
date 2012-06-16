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
public class TileLayerBrush extends AbBrush {

    private Layer brushLayer;

    /**
     *
     * @param mapLayer
     */
    public TileLayerBrush(Layer mapLayer) {
        brushLayer = mapLayer;
    }

    /**
     *
     * @return
     */
    public Layer getBrushLayer() {
        return brushLayer;
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return brushLayer.getBounds();
    }

    @Override
    public void startPaint(Layer mp, int x, int y, int button) {
        super.startPaint(mp, x, y, button);
    }

    /**
     * The custom brush will merge its internal layers onto the layers of the
     * specified MultilayerPlane.
     *
     * @see tiled.core.TileLayer#mergeOnto(tiled.core.MapLayer)
     * @see tiled.mapeditor.brush.Brush#doPaint(int, int)
     * @throws Exception
     */
    @Override
    public Rectangle doPaint(int x, int y) throws Exception {
        int centerx = x - brushLayer.getWidth() / 2;
        int centery = y - brushLayer.getHeight() / 2;
        super.doPaint(x, y);
        if (affectedLayer != null && affectedLayer.isVisible()) {
            brushLayer.setOffset(centerx, centery);
            brushLayer.mergeOnto(affectedLayer);
        }
        return new Rectangle(centerx, centery, brushLayer.getWidth(), brushLayer.getHeight());
    }
}

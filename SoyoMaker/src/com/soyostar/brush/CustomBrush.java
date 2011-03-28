/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.brush;

import com.soyostar.model.map.Layer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.TileLayer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ListIterator;

/**
 *
 * @author Administrator
 */
public class CustomBrush extends AbBrush {

    /**
     *
     * @param mlp
     */
    public CustomBrush(Map mlp) {
        addAllLayers(mlp.getLayerArrayList());
        fitBoundsToLayers();
    }

    /**
     *
     * @param mapLayer
     */
    public CustomBrush(Layer mapLayer) {
        addLayer(mapLayer);
        fitBoundsToLayers();
    }

    @Override
    public int getAffectedLayers() {
        return getTotalLayers();
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return getBounds();
    }

    /**
     * Determines whether this brush is equal to another brush.
     * @param b
     */
    public boolean equals(IBrush b) {
        if (b instanceof CustomBrush) {
            if (b == this) {
                return true;
            } else {
                //TODO: THIS
            }
        }
        return false;
    }

    @Override
    public void startPaint(Map mp, int x, int y, int button, int layer) {
        super.startPaint(mp, x, y, button, layer);
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
        int layer = initLayer;
        int centerx = x - bounds.width / 2;
        int centery = y - bounds.height / 2;

        super.doPaint(x, y);

        ListIterator itr = getListLayers();
        while (itr.hasNext()) {
            Layer tl = (Layer) itr.next();
            Layer tm = (Layer) affectedMp.getLayer(layer++);
            if (tm != null && tm.isIsVisible()) {
                tl.setOffset(centerx, centery);
                tl.mergeOnto(tm);
            }
        }

        return new Rectangle(centerx, centery, bounds.width, bounds.height);
    }
}

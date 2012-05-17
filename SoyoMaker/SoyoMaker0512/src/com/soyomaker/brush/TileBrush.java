/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.brush;

import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.model.map.Tile;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @version $Id$
 */
public class TileBrush extends AbBrush {

    /**
     *
     */
    protected Area shape;
    /**
     *
     */
    protected Tile paintTile;

    /**
     *
     */
    public TileBrush() {
    }

    /**
     *
     */
    public TileBrush(Tile tile) {
        paintTile = tile;
    }

    /**
     * Makes this brush a circular brush.
     *
     * @param rad the radius of the circular region
     */
    public void makeCircleBrush(double rad) {
        shape = new Area(new Ellipse2D.Double(0, 0, rad * 2, rad * 2));
    }

    /**
     * Makes this brush a rectangular brush.
     *
     * @param r a Rectangle to use as the shape of the brush
     */
    public void makeQuadBrush(Rectangle r) {
        shape = new Area(new Rectangle2D.Double(r.x, r.y, r.width, r.height));
    }

    /**
     *
     * @param p
     */
    public void makePolygonBrush(Polygon p) {
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        if (shape.isRectangular()) {
            makeQuadBrush(new Rectangle(0, 0, size, size));
        } else if (!shape.isPolygonal()) {
            makeCircleBrush(size / 2);
        } else {
            // TODO: scale the polygon brush
        }
    }

    /**
     *
     * @return
     */
    public Tile getBrushTile() {
        return paintTile;
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return shape;
    }

    @Override
    public void startPaint(Layer mp, int x, int y, int button) {
        super.startPaint(mp, x, y, button);
    }

    /**
     * Paints the entire area of the brush with the set tile. This brush can
     * affect several layers.
     * @throws Exception
     *
     * @see tiled.mapeditor.brush.Brush#doPaint(int, int)
     */
    @Override
    public Rectangle doPaint(int x, int y) throws Exception {
        Rectangle shapeBounds = shape.getBounds();
        int centerx = x - shapeBounds.width / 2;
        int centery = y - shapeBounds.height / 2;

        super.doPaint(x, y);

        if (affectedLayer != null && affectedLayer instanceof TileLayer) {
            for (int i = 0; i <= shapeBounds.height + 1; i++) {
                for (int j = 0; j <= shapeBounds.width + 1; j++) {
                    if (shape.contains(j, i)) {
                        ((TileLayer) affectedLayer).setTileAt(j + centerx, i + centery, paintTile);
                    }
                }
            }
        } else if (affectedLayer != null && affectedLayer instanceof CollideLayer) {
            for (int i = 0; i <= shapeBounds.height + 1; i++) {
                for (int j = 0; j <= shapeBounds.width + 1; j++) {
                    if (shape.contains(j, i)) {
                        ((CollideLayer) affectedLayer).setCollideAt(j + centerx, i + centery, true);
                    }
                }
            }
        }

        // Return affected area
        return new Rectangle(
                centerx, centery, shapeBounds.width, shapeBounds.height);
    }
}

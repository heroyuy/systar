/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.map;

import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public class CollideLayer extends Layer {

    protected boolean[][] collides;

    /**
     * Default contructor.
     */
    public CollideLayer() {
    }

    /**
     * Construct a CollideLayer from the given width and height.
     *
     * @param w width in tiles
     * @param h height in tiles
     */
    public CollideLayer(int w, int h) {
        super(w, h);
    }

    /**
     * Create a tile layer using the given bounds.
     *
     * @param r the bounds of the tile layer.
     */
    public CollideLayer(Rectangle r) {
        super(r);
    }

    /**
     * @param m the map this layer is part of
     */
    CollideLayer(Map m) {
        super(m);
    }

    /**
     * @param m the map this layer is part of
     * @param w width in tiles
     * @param h height in tiles
     */
    public CollideLayer(Map m, int w, int h) {
        super(w, h);
        setMap(m);
    }

    /**
     * Sets the bounds (in tiles) to the specified Rectangle.
     *
     * @param bounds
     */
    @Override
    protected void setBounds(Rectangle bounds) {
        this.bounds = new Rectangle(bounds);
        collides = new boolean[bounds.height][bounds.width];
        fireLayerChanged();
    }

    public void setCollideAt(int tx, int ty, boolean bool) {
        if (bounds.contains(tx, ty) && isIsVisible()) {
            collides[ty - bounds.y][tx - bounds.x] = bool;
            fireLayerChanged();
        }
    }

    public boolean getCollideAt(int tx, int ty) {
        return (bounds.contains(tx, ty))
            ? collides[ty - bounds.y][tx - bounds.x] : false;
    }
//    private boolean isPaintingStarted = false;
//
//    public boolean isIsPaintingStarted() {
//        return isPaintingStarted;
//    }
//
//    public void setIsPaintingStarted(boolean isPaintingStarted) {
//        this.isPaintingStarted = isPaintingStarted;
//    }

    /**
     * Creates a copy of this layer.
     *
     * @see Object#clone
     * @return a clone of this layer, as complete as possible
     * @exception CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        CollideLayer clone = (CollideLayer) super.clone();
        return clone;
    }
    //还未实现

    @Override
    public Layer createDiff(Layer ml) {
        return new CollideLayer();
    }

    @Override
    public void copyTo(Layer other) {
    }

    @Override
    public void copyFrom(Layer other) {
    }

    @Override
    public void mergeOnto(Layer other) {
        if (!other.isIsVisible()) {
            return;
        }

        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                boolean tile = getCollideAt(x, y);
                if (tile) {
                    ((CollideLayer) other).setCollideAt(x, y, tile);
                }
            }
        }
    }
}

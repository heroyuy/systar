/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public class CollideLayer extends Layer {

    /**
     * 
     */
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

    /**
     * 
     * @param tx
     * @param ty
     * @param bool
     */
    public void setCollideAt(int tx, int ty, boolean bool) {
        if (bounds.contains(tx, ty) && isVisible()) {
            collides[ty - bounds.y][tx - bounds.x] = bool;
            fireLayerChanged();
        }
    }

    /**
     * 
     * @param tx
     * @param ty
     * @return
     */
    public boolean getCollideAt(int tx, int ty) {
        return (bounds.contains(tx, ty))
                ? collides[ty - bounds.y][tx - bounds.x] : false;
    }

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
        clone.collides = new boolean[collides.length][];
        for (int i = 0, n = collides.length; i < n; i++) {
            clone.collides[i] = new boolean[collides[i].length];
            System.arraycopy(collides[i], 0, clone.collides[i], 0, collides[i].length);
        }
        return clone;
    }

    @Override
    public void copyTo(Layer other) {
        if (!other.isVisible()) {
            return;
        }

        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                ((CollideLayer) other).setCollideAt(x, y, getCollideAt(x, y));
            }
        }
//        System.out.println("c copyto");
    }

    /**
     * 
     * @param other
     */
    @Override
    public void copyFrom(Layer other) {
        if (!isVisible()) {
            return;
        }

        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                setCollideAt(x, y, ((CollideLayer) other).getCollideAt(x, y));
            }
        }
//        System.out.println("c copyfrom");
    }

    @Override
    public void mergeOnto(Layer other) {
        if (!other.isVisible()) {
            return;
        }
        if (other instanceof CollideLayer) {
            for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
                for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                    boolean tile = getCollideAt(x, y);
                    if (tile) {
                        ((CollideLayer) other).setCollideAt(x, y, tile);
                    }
                }
            }
        }
//        System.out.println("c mergeonto");
    }

    /**
     *
     * @param width
     * @param height
     * @param dx
     * @param dy
     */
    @Override
    public void resize(int width, int height, int dx, int dy) {
        boolean[][] newMapt = new boolean[height][width];
        int maxX = Math.min(width, bounds.width + dx);
        int maxY = Math.min(height, bounds.height + dy);

        for (int x = Math.max(0, dx); x < maxX; x++) {
            for (int y = Math.max(0, dy); y < maxY; y++) {
                newMapt[y][x] = getCollideAt(x - dx, y - dy);
            }
        }
        collides = newMapt;
        bounds.width = width;
        bounds.height = height;
    }
}

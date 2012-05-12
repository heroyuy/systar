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
public class SpriteLayer extends Layer {

    private Npc[][] npcs;

    /**
     * Default contructor.
     */
    public SpriteLayer() {
    }

    /**
     * Construct a SpriteLayer from the given width and height.
     *
     * @param w width in tiles
     * @param h height in tiles
     */
    public SpriteLayer(int w, int h) {
        super(w, h);
    }

    /**
     * Create a tile layer using the given bounds.
     *
     * @param r the bounds of the tile layer.
     */
    public SpriteLayer(Rectangle r) {
        super(r);
    }

    /**
     * @param m the map this layer is part of
     */
    SpriteLayer(Map m) {
        super(m);
    }

    /**
     * @param m the map this layer is part of
     * @param w width in tiles
     * @param h height in tiles
     */
    public SpriteLayer(Map m, int w, int h) {
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
        npcs = new Npc[bounds.height][bounds.width];
        fireLayerChanged();
    }

    /**
     * 
     * @param tx
     * @param ty
     * @param npc
     */
    public void setNpcAt(int tx, int ty, Npc npc) {
        if (bounds.contains(tx, ty) && isVisible()) {
            npcs[ty - bounds.y][tx - bounds.x] = npc;
            if (npc != null) {
                npcs[ty - bounds.y][tx - bounds.x].setCol(tx);
                npcs[ty - bounds.y][tx - bounds.x].setRow(ty);
                npcs[ty - bounds.y][tx - bounds.x].setMapId(map.getIndex());
            }
            fireLayerChanged();
        }
    }

    /**
     * 
     * @param tx
     * @param ty
     * @return
     */
    public Npc getNpcAt(int tx, int ty) {
        return (bounds.contains(tx, ty))
                ? npcs[ty - bounds.y][tx - bounds.x] : null;
    }

    /**
     * Creates a copy of this layer.
     *
     * @see Object#clone
     * @return a clone of this layer, as complete as possible
     * @exception CloneNotSupportedException
     */
    @Override
    public SpriteLayer clone() throws CloneNotSupportedException {
        SpriteLayer clone = (SpriteLayer) super.clone();

        // Clone the layer data
        clone.npcs = new Npc[npcs.length][];

        for (int i = 0, n = npcs.length; i < n; i++) {
            clone.npcs[i] = new Npc[npcs[i].length];
            System.arraycopy(npcs[i], 0, clone.npcs[i], 0, npcs[i].length);
        }
        return clone;
    }

    @Override
    public void copyTo(Layer other) {
//        if (!other.isIsVisible()) {
//            return;
//        }
//
//        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
//            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
//                ((SpriteLayer) other).setNpcAt(x, y, getNpcAt(x, y));
//            }
//        }
//        System.out.println("s copyto");
    }

    /**
     * 
     * @param other
     */
    @Override
    public void copyFrom(Layer other) {
//        if (!isIsVisible()) {
//            return;
//        }
//
//        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
//            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
//                setNpcAt(x, y, ((SpriteLayer) other).getNpcAt(x, y));
//            }
//        }
//        System.out.println("s copyfrom");
    }

    @Override
    public void mergeOnto(Layer other) {
//        if (!other.isIsVisible()) {
//            return;
//        }
//        if (other instanceof SpriteLayer) {
//            for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
//                for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
//                    Script tile = getNpcAt(x, y);
//                    if (tile != null) {
//                        ((SpriteLayer) other).setNpcAt(x, y, tile);
//                    }
//                }
//            }
//        }
//        System.out.println("s mergeonto");
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
        Npc[][] newMapt = new Npc[height][width];
        int maxX = Math.min(width, bounds.width + dx);
        int maxY = Math.min(height, bounds.height + dy);

        for (int x = Math.max(0, dx); x < maxX; x++) {
            for (int y = Math.max(0, dy); y < maxY; y++) {
                newMapt[y][x] = getNpcAt(x - dx, y - dy);
            }
        }
        npcs = newMapt;
        bounds.width = width;
        bounds.height = height;
    }
}

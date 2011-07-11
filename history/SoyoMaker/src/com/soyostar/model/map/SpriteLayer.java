/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.map;

import com.soyostar.model.script.Npc;
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

    public void setNpcAt(int tx, int ty, Npc npc) {
        if (bounds.contains(tx, ty) && isIsVisible()) {
            npcs[ty - bounds.y][tx - bounds.x] = npc;
            fireLayerChanged();
        }
    }

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
    public Object clone() throws CloneNotSupportedException {
        SpriteLayer clone = (SpriteLayer) super.clone();

        // Clone the layer data
        clone.npcs = new Npc[npcs.length][];

        for (int i = 0, n = npcs.length; i < n; i++) {
            clone.npcs[i] = new Npc[npcs[i].length];
            System.arraycopy(npcs[i], 0, clone.npcs[i], 0, npcs[i].length);
        }

        return clone;
    }
    //还未实现

    @Override
    public Layer createDiff(Layer ml) {
        return new SpriteLayer();
    }

    @Override
    public void copyTo(Layer other) {
    }

    @Override
    public void copyFrom(Layer other) {
    }

    @Override
    public void mergeOnto(Layer other) {
    }
}

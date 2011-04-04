/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Administrator
 */
public class Tile {

    private Rectangle bound;
    private TileSet tileset;
    private double zoom = 1.0;          //缩放比例

    public TileSet getTileSet() {
        return tileset;
    }

    public void setTileSet(TileSet tileset) {
        this.tileset = tileset;
    }

    public Tile() {
        bound = new Rectangle();
    }

    public Tile(int x, int y) {
        bound = new Rectangle(x, y, 1, 1);
    }

    public Tile(Rectangle r) {
        bound = new Rectangle(r.x, r.y, r.width, r.height);
    }

    public Tile(TileSet tileset, Rectangle r) {
        this.tileset = tileset;
        bound = new Rectangle(r.x, r.y, r.width, r.height);
    }

    public Tile(TileSet tileset, int x, int y, int w, int h) {
        this.tileset = tileset;
        bound = new Rectangle(x, y, w, h);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean contains(int x, int y) {
        return (x >= bound.x) && (y >= bound.y) && (x <= (bound.x + bound.width)) && (y <= (bound.y + bound.height));
    }

    public void setX(int x) {
        setBound(new Rectangle(x, bound.y, bound.width, bound.height));
    }

    /**
     *
     * @param g
     * @param t
     * @param x
     * @param y
     * @param zoom
     */
    public void paint(Graphics g, int t, int x, int y, int zoom) {
        if (tileset != null) {
            tileset.paint(g, bound.x, bound.y, bound.width, bound.height, t, x, y, zoom);
        }
    }

    public void setY(int y) {
        setBound(new Rectangle(bound.x, y, bound.width, bound.height));
    }

    public void setWidth(int w) {
        setBound(new Rectangle(bound.x, bound.y, w, bound.height));
    }

    public void setHeight(int h) {
        setBound(new Rectangle(bound.x, bound.y, bound.width, h));
    }

    public void setSize(int w, int h) {
        setBound(new Rectangle(bound.x, bound.y, w, h));
    }

    public int getX() {
        return bound.x;
    }

    public int getY() {
        return bound.y;
    }

    public int getWidth() {
        return bound.width;
    }

    public int getHeight() {
        return bound.height;
    }

    /**
     *
     * @return
     */
    public Rectangle getBound() {
        return bound;
    }

    /**
     *
     * @param bounds
     */
    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
}

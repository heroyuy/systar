/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public class Module {

    private Tile tile;
    private Point point;
    private ModuleLayer layer;

    public ModuleLayer getLayer() {
        return layer;
    }

    public void setLayer(ModuleLayer layer) {
        this.layer = layer;
    }

    public Module() {
        tile = new Tile();
        point = new Point();

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
        layer.updateBounds();
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Module(Tile tile, Point point) {
        this.tile = tile;
        this.point = point;
    }

    public void setX(int x) {
        Point p = new Point(x, point.y);
        setPoint(p);
    }

    public void setY(int y) {
        Point p = new Point(point.x, y);
        setPoint(p);
    }

    /**
     *
     * @return
     */
    public int getX() {
        return point.x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return point.y;
    }

    public int getWidth() {
        return tile.getWidth();
    }

    public int getHeight() {
        return tile.getHeight();
    }

    public Rectangle getBound() {
        return new Rectangle(point.x, point.y, tile.getWidth(), tile.getHeight());
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean contains(int x, int y) {
        return (x >= point.x) && (y >= point.y) && (x <= (point.x + tile.getWidth())) && (y <= (point.y + tile.getHeight()));
    }

    public void paint(Graphics g, int dir, int x, int y, int zoom) {
        int cx = point.x;
        int cy = point.y;
        cx *= zoom;
        cy *= zoom;
        tile.paint(g, 0, cx + x, cy + y, zoom);
    }
}

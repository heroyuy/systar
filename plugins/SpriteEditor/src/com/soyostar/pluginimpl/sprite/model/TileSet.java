/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class TileSet {

    private ArrayList<Tile> tiles;
    private int firstGid;               //区分各个tileset的标识
    private String name;                //图集名
    private BufferedImage tileSetImage;         //图集图像
    private String tilebmpFile;         //图集文件名

    public TileSet() {
        tiles = new ArrayList<Tile>();
    }

    public int indexOf(Tile tile) {
        return tiles.indexOf(tile);
    }

    public int getTileCounts() {
        return tiles.size();
    }

    public void addTile(Tile tile) {
        if (tile != null) {
            tiles.add(tile);
        } else {
            System.out.println("无效的Tile");
        }
    }

    public void removeTile(Tile tile) {
        if (tile != null) {
            tiles.remove(tile);
        } else {
            System.out.println("无效的Tile");
        }
    }

    /**
     *
     * @return
     */
    public Iterator getTiles() {
        return tiles.iterator();
    }

    public Tile getTile(int id) {
        if (id < 0 || id >= tiles.size()) {
            return null;
        }
        return tiles.get(id);
    }

    public int getFirstGid() {
        return firstGid;
    }

    public void setFirstGid(int firstGid) {
        this.firstGid = firstGid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param g
     * @param x
     * @param y
     * @param size
     */
    public void paintPreview(Graphics g, int x, int y, int size) {
        g.drawImage(getTileSetImage(), x, y, size, size, null);
    }

    /**
     *
     * @param g
     * @param cx
     * @param cy
     * @param cw
     * @param ch
     * @param x
     * @param y
     * @param zoom
     */
    public void paint(Graphics g, int cx, int cy, int cw, int ch, int trans, int x, int y, int zoom) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage bImage = this.getTileSetImage().getSubimage(cx, cy, cw, ch);

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(x, y);
        switch (trans) {
            case Trans.NONE:
                affineTransform.scale(zoom, zoom);
                break;
            case Trans.MIRROR_ROT180:
                affineTransform.scale(zoom, -zoom);
                affineTransform.translate(0, -ch);
                break;
            case Trans.MIRROR:
                affineTransform.scale(-zoom, zoom);
                affineTransform.translate(-cw, 0);
                break;
            case Trans.ROT180:
                affineTransform.scale(-zoom, -zoom);
                affineTransform.translate(-cw, -ch);
                break;
            case Trans.MIRROR_ROT270:
                affineTransform.rotate(Math.toRadians(270));
                affineTransform.scale(-zoom, zoom);
                affineTransform.translate(0, 0);
                break;
            case Trans.ROT90:
                affineTransform.rotate(Math.toRadians(90));
                affineTransform.scale(zoom, zoom);
                affineTransform.translate(0, -ch);
                break;
            case Trans.ROT270:
                affineTransform.rotate(Math.toRadians(270));
                affineTransform.scale(zoom, zoom);
                affineTransform.translate(-cw, 0);
                break;
            case Trans.MIRROR_ROT90:
                affineTransform.rotate(Math.toRadians(90));
                affineTransform.scale(-zoom, zoom);
                affineTransform.translate(-cw, -ch);
                break;
        }
        g2d.drawImage(bImage, affineTransform, null);
    }

    public BufferedImage getTileSetImage() {
        return tileSetImage;
    }

    public void setTileSetImage(BufferedImage tileSetImage) {
        this.tileSetImage = tileSetImage;
    }

    public String getTilebmpFile() {
        return tilebmpFile;
    }

    public void setTilebmpFile(String tilebmpFile) {
        this.tilebmpFile = tilebmpFile;
    }
}

/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.model.map;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Administrator
 */
public class Tile {

    private int id = -1;                //瓷砖序号
    protected int tileImageId = -1;     //瓷砖图像序号
    private double zoom = 1.0;          //缩放比例
    private TileSet tileset;            //所属图集

    /**
     *
     */
    public Tile() {
    }

    /**
     *
     * @param tileSet
     */
    public Tile(TileSet tileSet) {
        setTileSet(tileSet);
    }

    /**
     *
     * @param set
     */
    public void setTileSet(TileSet set) {

        if (tileset != null && tileset != set) {
            setImage(set.addImage(getImage()));
        }
        tileset = set;
    }

    /**
     * Returns the {@link tiled.core.TileSet} that this tile is part of.
     *
     * @return TileSet
     */
    public TileSet getTileSet() {
        return tileset;
    }

    /**
     * Returns the tile image for this Tile.
     *
     * @return Image
     */
    public Image getImage() {
        if (tileset != null) {
            return tileset.getImageById(tileImageId);
        }
        return null;
    }

    /**
     * Changes the image of the tile as long as it is not null.
     *
     * @param i the new image of the tile
     */
    public void setImage(Image i) {
        if (tileset != null) {
            tileset.overlayImage(tileImageId, i);
        }
    }

    /**
     *
     * @param id
     */
    public void setImage(int id) {
        tileImageId = id;
    }

    /**
     *
     * @return
     */
    public int getImageId() {
        return tileImageId;
    }

    /**
     * Returns the tile id of this tile, relative to tileset.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the tile as long as it is at least 0.
     *
     * @param i The id of the tile
     */
    public void setId(int i) {
        if (i >= 0) {
            id = i;
        }
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        if (tileset != null) {
            Dimension d = tileset.getImageDimensions(tileImageId);
            return d.width;
        }
        return 0;

    }

    /**
     *
     * @return
     */
    public int getHeight() {
        if (tileset != null) {
            Dimension d = tileset.getImageDimensions(tileImageId);
            return d.height;
        }
        return 0;
    }

    /**
     * This drawing function handles drawing the tile image at the
     * specified zoom level. It will attempt to use a cached copy,
     * but will rescale if the requested zoom does not equal the
     * current cache zoom.
     *
     * @param g Graphics instance to draw to
     * @param x x-coord to draw tile at
     * @param y y-coord to draw tile at
     * @param zoom Zoom level to draw the tile
     */
    public void draw(Graphics g, int x, int y, double zoom) {
        Image img = getScaledImage(zoom);
        if (img != null) {
            g.drawImage(img, x, y - img.getHeight(null), null);
        } else {
            // TODO: Allow drawing IDs when no image data exists as a
            // config option
        }
    }

    /**
     * Returns a scaled instance of the tile image. Using a MediaTracker
     * instance, this function waits until the scaling operation is done.
     * <p/>
     * Internally it caches the scaled image in order to optimize the common
     * case, where the same scale is requested as the last time.
     *
     * @param zo
     * @return Image
     */
    public Image getScaledImage(double zo) {
        Image scaledImage = null;
        if (zo == 1.0) {
            return getImage();
        } else if (zo == zoom && scaledImage != null) {
            return scaledImage;
        } else {
            Image img = getImage();
//            System.out.println(getWidth());
            if (img != null) {
                scaledImage = img.getScaledInstance(
                    (int) (getWidth() * zo), (int) (getHeight() * zo),
                    BufferedImage.SCALE_SMOOTH);

                MediaTracker mediaTracker = new MediaTracker(new Canvas());
                mediaTracker.addImage(scaledImage, 0);
                try {
                    mediaTracker.waitForID(0);
                } catch (InterruptedException ie) {
                    System.err.println(ie);
                }
                mediaTracker.removeImage(scaledImage);
                zoom = zo;
                return scaledImage;
            }
        }

        return null;
    }
}

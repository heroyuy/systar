/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.model.map;

import java.awt.*;

/**
 *
 * @author Administrator
 */
public class TileLayer extends Layer {

    /**
     * 
     */
    protected Tile[][] tiles;

    /**
     * Default contructor.
     */
    public TileLayer() {
    }

    /**
     * Construct a TileLayer from the given width and height.
     *
     * @param w width in tiles
     * @param h height in tiles
     */
    public TileLayer(int w, int h) {
        super(w, h);
    }

    /**
     * Create a tile layer using the given bounds.
     *
     * @param r the bounds of the tile layer.
     */
    public TileLayer(Rectangle r) {
        super(r);
    }

    /**
     * @param m the map this layer is part of
     */
    TileLayer(Map m) {
        super(m);
    }

    /**
     * @param m the map this layer is part of
     * @param w width in tiles
     * @param h height in tiles
     */
    public TileLayer(Map m, int w, int h) {
        super(w, h);
        setMap(m);
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
        TileLayer clone = (TileLayer) super.clone();
        // Clone the layer data
        clone.tiles = new Tile[tiles.length][];
        for (int i = 0, n = tiles.length; i < n; i++) {
            clone.tiles[i] = new Tile[tiles[i].length];
            System.arraycopy(tiles[i], 0, clone.tiles[i], 0, tiles[i].length);
        }
        return clone;
    }

    /**
     * Copy data from another layer onto this layer. Unlike mergeOnto,
     * copyFrom() copies the empty cells as well.
     *
     * @see MapLayer#mergeOnto
     * @param other
     */
    public void copyFrom(Layer other) {
        if (!isIsVisible()) {
            return;
        }
        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                setTileAt(x, y, ((TileLayer) other).getTileAt(x, y));
            }
        }
    }

    /**
     * Sets the bounds (in tiles) to the specified Rectangle.
     *
     * @param bounds
     */
    @Override
    protected void setBounds(Rectangle bounds) {
        this.bounds = new Rectangle(bounds);
        tiles = new Tile[bounds.height][bounds.width];
        fireLayerChanged();
    }

    /**
     * Removes any occurences of the given tile from this map layer. If layer
     * is locked, an exception is thrown.
     *
     * @param tile the Tile to be removed
     * @throws Exception
     */
    public void removeTile(Tile tile) throws Exception {
        if (!isIsVisible()) {
            throw new Exception(
                    "图层不可视！");
        }

        for (int y = 0; y < bounds.height; y++) {
            for (int x = 0; x < bounds.width; x++) {
                if (tiles[y][x] == tile) {
                    setTileAt(x + bounds.x, y + bounds.y, null);
                }
            }
        }
    }
    private static final int NONE = 0x00000000;
    private static final int LEFT = 0x00000001;
    private static final int RIGHT = 0x00000010;
    private static final int TOP = 0x00000100;
    private static final int BOTTOM = 0x00001000;
    private static final int LEFT_TOP = 0x00010000;
    private static final int RIGHT_TOP = 0x00100000;
    private static final int LEFT_BOTTOM = 0x01000000;
    private static final int RIGHT_BOTTOM = 0x10000000;

    private void updateAutoTile(int row, int col) {
        if (bounds.contains(col, row)) {
            Tile t = tiles[row - bounds.y][col - bounds.x];
            if (t != null && t.getTileSet() != null && t.getTileSet().isAutoTile()) {

                int state = 0;
                //左上
                if (bounds.contains(col - 1, row - 1)) {
                    Tile tt = tiles[row - bounds.y - 1][col - bounds.x - 1];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= LEFT_TOP;
                    }
                }
                //上
                if (bounds.contains(col, row - 1)) {
                    Tile tt = tiles[row - bounds.y - 1][col - bounds.x];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= TOP;
                    }
                }
                //左
                if (bounds.contains(col - 1, row)) {
                    Tile tt = tiles[row - bounds.y][col - bounds.x - 1];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= LEFT;
                    }
                }
                //右
                if (bounds.contains(col + 1, row)) {
                    Tile tt = tiles[row - bounds.y][col - bounds.x + 1];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= RIGHT;
                    }
                }
                //右下
                if (bounds.contains(col + 1, row + 1)) {
                    Tile tt = tiles[row - bounds.y + 1][col - bounds.x + 1];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= RIGHT_BOTTOM;
                    }
                }
                //下
                if (bounds.contains(col, row + 1)) {
                    Tile tt = tiles[row - bounds.y + 1][col - bounds.x];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= BOTTOM;
                    }
                }
                //左下
                if (bounds.contains(col - 1, row + 1)) {
                    Tile tt = tiles[row - bounds.y + 1][col - bounds.x - 1];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= LEFT_BOTTOM;
                    }
                }
                //右上
                if (bounds.contains(col + 1, row - 1)) {
                    Tile tt = tiles[row - bounds.y - 1][col - bounds.x + 1];
                    if (tt != null && tt.getTileSet() != null && tt.getTileSet().isAutoTile()) {
                        state |= RIGHT_TOP;
                    }
                }
//                System.out.println("col:" + col + " row:" + row + " state:" + Integer.toHexString(state));
                if ((state & LEFT) == 0) {//如果左边没有，则左上和左下失去影响力
                    if ((state & RIGHT) == 0) {//如果右边没有，则右上和右下失去影响力
                        if ((state & TOP) == 0) {//如果上面没有，则右上和左上失去影响力
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                t.setAutoId(46);
                            } else {
                                t.setAutoId(42);
                            }
                        } else {
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                t.setAutoId(44);
                            } else {
                                t.setAutoId(32);
                            }
                        }
                    } else {
                        //左边没有，右边有
                        if ((state & TOP) == 0) {//如果上面没有，则右上和左上失去影响力
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                t.setAutoId(43);
                            } else {
                                if ((state & RIGHT_BOTTOM) == 0) {
                                    t.setAutoId(35);
                                } else {
                                    t.setAutoId(34);
                                }
                            }
                        } else {
                            //左边没有，右边有，上面有
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                if ((state & RIGHT_TOP) == 0) {
                                    t.setAutoId(41);
                                } else {
                                    t.setAutoId(40);
                                }
                            } else {
                                //左边没有，右边有，上面有，下面有
                                if ((state & RIGHT_TOP) == 0) {
                                    if ((state & RIGHT_BOTTOM) == 0) {
                                        t.setAutoId(19);
                                    } else {
                                        t.setAutoId(17);
                                    }
                                } else {
                                    if ((state & RIGHT_BOTTOM) == 0) {
                                        t.setAutoId(18);
                                    } else {
                                        t.setAutoId(16);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    //左边有
                    if ((state & RIGHT) == 0) {//如果右边没有，则右上和右下失去影响力
                        if ((state & TOP) == 0) {//如果上面没有，则右上和左上失去影响力
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                t.setAutoId(45);
                            } else {
                                if ((state & LEFT_BOTTOM) == 0) {
                                    t.setAutoId(37);
                                } else {
                                    t.setAutoId(36);
                                }
                            }
                        } else {
                            //左边有，右边没有，上面有
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                if ((state & LEFT_TOP) == 0) {
                                    t.setAutoId(39);
                                } else {
                                    t.setAutoId(38);
                                }
                            } else {
                                //左边有，右边没有，上面有，下面有
                                if ((state & LEFT_TOP) == 0) {
                                    if ((state & LEFT_BOTTOM) == 0) {
                                        t.setAutoId(27);
                                    } else {
                                        t.setAutoId(26);
                                    }
                                } else {
                                    if ((state & LEFT_BOTTOM) == 0) {
                                        t.setAutoId(25);
                                    } else {
                                        t.setAutoId(24);
                                    }
                                }
                            }
                        }
                    } else {
//                        //左边有，右边有
                        if ((state & TOP) == 0) {//如果上面没有，则右上和左上失去影响力
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                t.setAutoId(33);
                            } else {
                                //左边有，右边有，上面没有，下面有
                                if ((state & LEFT_BOTTOM) == 0) {
                                    if ((state & RIGHT_BOTTOM) == 0) {
                                        t.setAutoId(23);
                                    } else {
                                        t.setAutoId(22);
                                    }
                                } else {
                                    if ((state & RIGHT_BOTTOM) == 0) {
                                        t.setAutoId(21);
                                    } else {
                                        t.setAutoId(20);
                                    }
                                }
                            }
                        } else {
//                          //左边有，右边有，上面有
                            if ((state & BOTTOM) == 0) {//如果下面没有，则右下和左下失去影响力
                                if ((state & LEFT_TOP) == 0) {
                                    if ((state & RIGHT_TOP) == 0) {
                                        t.setAutoId(31);
                                    } else {
                                        t.setAutoId(29);
                                    }
                                } else {
                                    if ((state & RIGHT_TOP) == 0) {
                                        t.setAutoId(30);
                                    } else {
                                        t.setAutoId(28);
                                    }
                                }
                            } else {
                                //左边有，右边有，上面有，下面有
                                if ((state & LEFT_TOP) == 0) {
                                    if ((state & RIGHT_TOP) == 0) {
                                        if ((state & LEFT_BOTTOM) == 0) {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(46);
                                            } else {
                                                t.setAutoId(11);
                                            }
                                        } else {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(7);
                                            } else {
                                                t.setAutoId(3);
                                            }
                                        }
                                    } else {
                                        //左边有，右边有，上面有，下面有，左上没有，右上有
                                        if ((state & LEFT_BOTTOM) == 0) {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(13);
                                            } else {
                                                t.setAutoId(9);
                                            }
                                        } else {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(5);
                                            } else {
                                                t.setAutoId(1);
                                            }
                                        }
                                    }
                                } else {
                                    //左边有，右边有，上面有，下面有，左上有
                                    if ((state & RIGHT_TOP) == 0) {
                                        if ((state & LEFT_BOTTOM) == 0) {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(14);
                                            } else {
                                                t.setAutoId(10);
                                            }
                                        } else {
                                            //左边有，右边有，上面有，下面有，左上有，左下有，右上没有
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(6);
                                            } else {
                                                t.setAutoId(2);
                                            }
                                        }
                                    } else {
                                        //左边有，右边有，上面有，下面有，左上有，右上有
                                        if ((state & LEFT_BOTTOM) == 0) {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(12);
                                            } else {
                                                t.setAutoId(8);
                                            }
                                        } else {
                                            if ((state & RIGHT_BOTTOM) == 0) {
                                                t.setAutoId(4);
                                            } else {
                                                t.setAutoId(0);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the tile at the specified position. Does nothing if (tx, ty) falls
     * outside of this layer.
     *
     * @param tx x position of tile
     * @param ty y position of tile
     * @param ti the tile object to place
     */
    public void setTileAt(int tx, int ty, Tile ti) {
        if (bounds.contains(tx, ty) && isIsVisible()) {
            try {
                if (ti != null) {
                    tiles[ty - bounds.y][tx - bounds.x] = (Tile) ti.clone();
                    if (ti.getTileSet() != null && ti.getTileSet().isAutoTile()) {
                        updateAutoTile(ty, tx);
                        updateAutoTile(ty - 1, tx - 1);
                        updateAutoTile(ty - 1, tx);
                        updateAutoTile(ty - 1, tx + 1);
                        updateAutoTile(ty, tx - 1);
                        updateAutoTile(ty, tx + 1);
                        updateAutoTile(ty + 1, tx - 1);
                        updateAutoTile(ty + 1, tx);
                        updateAutoTile(ty + 1, tx + 1);
                    }
                } else {
                    tiles[ty - bounds.y][tx - bounds.x] = null;
                }
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
            fireLayerChanged();
        }
    }

    /**
     * Returns the tile at the specified position.
     *
     * @param tx Tile-space x coordinate
     * @param ty Tile-space y coordinate
     * @return tile at position (tx, ty) or <code>null</code> when (tx, ty) is
     *         outside this layer
     */
    public Tile getTileAt(int tx, int ty) {
        Tile tile = null;
        if (bounds.contains(tx, ty)) {
            return tiles[ty - bounds.y][tx - bounds.x];
        }
        return tile;
    }

    /**
     * Returns the first occurance (using top down, left to right search) of
     * the given tile.
     *
     * @param t the {@link Tile} to look for
     * @return A java.awt.Point instance of the first instance of t, or
     *         <code>null</code> if it is not found
     */
    public Point locationOf(Tile t) {
        for (int y = bounds.y; y < bounds.height + bounds.y; y++) {
            for (int x = bounds.x; x < bounds.width + bounds.x; x++) {
                if (getTileAt(x, y) == t) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    /**
     * Replaces all occurances of the Tile <code>find</code> with the Tile
     * <code>replace</code> in the entire layer
     *
     * @param find    the tile to replace
     * @param replace the replacement tile
     * @throws Exception
     */
    public void replaceTile(Tile find, Tile replace) throws Exception {
        if (!isIsVisible()) {
            throw new Exception(
                    "图层不可视！");
        }

        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                if (getTileAt(x, y) == find) {
                    setTileAt(x, y, replace);
                }
            }
        }
    }

    /**
     * Checks to see if the given Tile is used anywhere in the layer.
     *
     * @param t a Tile object to check for
     * @return <code>true</code> if the Tile is used at least once,
     *         <code>false</code> otherwise.
     */
    public boolean isUsed(Tile t) {
        for (int y = 0; y < bounds.height; y++) {
            for (int x = 0; x < bounds.width; x++) {
                if (tiles[y][x] == t) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        for (int p = 0; p < 2; p++) {
            for (int y = 0; y < bounds.height; y++) {
                for (int x = p; x < bounds.width; x += 2) {
                    if (tiles[y][x] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param other
     * @inheritDoc MapLayer#mergeOnto(MapLayer)
     */
    public void mergeOnto(Layer other) {
        if (!other.isIsVisible()) {
            return;
        }
        if (other instanceof TileLayer) {
            for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
                for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                    Tile tile = getTileAt(x, y);
                    if (tile != null) {
                        ((TileLayer) other).setTileAt(x, y, tile);
                    }
                }
            }
        }
    }

    /**
     * @see MultilayerPlane#resize
     *
     * @param width  the new width of the layer
     * @param height the new height of the layer
     * @param dx     the shift in x direction
     * @param dy     the shift in y direction
     */
    public void resize(int width, int height, int dx, int dy) {
//        if (!isIsVisible()) {
//            return;
//        }

        Tile[][] newMapt = new Tile[height][width];
        int maxX = Math.min(width, bounds.width + dx);
        int maxY = Math.min(height, bounds.height + dy);

        for (int x = Math.max(0, dx); x < maxX; x++) {
            for (int y = Math.max(0, dy); y < maxY; y++) {
                newMapt[y][x] = getTileAt(x - dx, y - dy);
            }
        }
        tiles = newMapt;
        bounds.width = width;
        bounds.height = height;
    }

    /**
     * Unlike mergeOnto, copyTo includes the null tile when merging.
     *
     * @see MapLayer#copyFrom
     * @see MapLayer#mergeOnto
     * @param other the layer to copy this layer to
     */
    public void copyTo(Layer other) {
        if (!other.isIsVisible()) {
            return;
        }
        for (int y = bounds.y; y < bounds.y + bounds.height; y++) {
            for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
                ((TileLayer) other).setTileAt(x, y, getTileAt(x, y));
            }
        }
//        System.out.println("t copyto");
    }
}

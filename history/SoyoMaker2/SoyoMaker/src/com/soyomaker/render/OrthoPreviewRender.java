/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.render;

import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.Tile;
import com.soyomaker.model.map.TileLayer;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;

/**
 *
 * @author Administrator
 */
public class OrthoPreviewRender extends PreviewRender {

    /**
     * 
     * @param map
     */
    public OrthoPreviewRender(Map map) {
        super(map);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension tsize = getTileSize();
        return new Dimension(
                map.getWidth() * tsize.width,
                map.getHeight() * tsize.height);
    }

    /**
     *
     * @param tx
     * @param ty
     * @param border
     * @return
     */
    protected Polygon createGridPolygon(int tx, int ty, int border) {
        Dimension tsize = getTileSize();
        Polygon poly = new Polygon();
        poly.addPoint(tx - border, ty - border);
        poly.addPoint(tx + tsize.width + border, ty - border);
        poly.addPoint(tx + tsize.width + border, ty + tsize.height + border);
        poly.addPoint(tx - border, ty + tsize.height + border);
        return poly;
    }

    @Override
    protected void paintTileLayer(Graphics2D g2d, TileLayer layer) {

        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }
        int startX = 0;
        int startY = 0;
        int endX = this.getWidth() / tsize.width + 1;
        int endY = this.getHeight() / tsize.height + 1;

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
                y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                    x < endX; x++, gx += tsize.width) {
                Tile tile = layer.getTileAt(x, y);
                if (tile != null) {
                    if (showTile) {
                        tile.draw(g2d, gx, gy, zoom);
                    }
                    if (!tile.isCross() && showCollide) {
                        g2d.setColor(Color.RED);
                        g2d.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                                BasicStroke.JOIN_MITER));
                        g2d.drawOval(gx + 1, gy - tsize.height + 1, tsize.width - 3, tsize.height - 3);
                        g2d.drawLine(gx + 1, gy - (tsize.height >> 1), gx + tsize.width - 2, gy - (tsize.height >> 1));
                    }
                }
            }
        }
    }

    @Override
    protected void paintCollideLayer(Graphics2D g2d, CollideLayer layer) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }
        int startX = 0;
        int startY = 0;
        int endX = this.getWidth() / tsize.width + 1;
        int endY = this.getHeight() / tsize.height + 1;
        // (endY +2 for high tiles, could be done more properly)

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
                y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                    x < endX; x++, gx += tsize.width) {
                if (layer.getCollideAt(x, y)) {
                    g2d.setColor(Color.RED);
                    g2d.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER));
                    g2d.drawOval(gx + 1, gy - tsize.height + 1, tsize.width - 3, tsize.height - 3);
                    g2d.drawLine(gx + 1, gy - (tsize.height >> 1), gx + tsize.width - 2, gy - (tsize.height >> 1));
                }
            }
        }
    }

    @Override
    protected void paintSpriteLayer(Graphics2D g2d, SpriteLayer layer) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }

        int startX = 0;
        int startY = 0;
        int endX = this.getWidth() / tsize.width + 1;
        int endY = this.getHeight() / tsize.height + 1;
        // (endY +2 for high tiles, could be done more properly)

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
                y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                    x < endX; x++, gx += tsize.width) {

                if (layer.getNpcAt(x, y) != null) {

                    if (layer.getNpcAt(x, y).getNpcState(0).
                            getImg() != null) {
                        g2d.setComposite(AlphaComposite.getInstance(
                                AlphaComposite.SRC_ATOP, 1.0f));
                        switch (layer.getNpcAt(x, y).getNpcState(0).getFace()) {

                            case NpcState.DOWN_ONE_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        0, 0, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.LEFT_ONE_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        0, layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4,
                                        2 * layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.RIGHT_ONE_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        0, 2 * (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4), layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4,
                                        3 * layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.UP_ONE_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        0, 3 * (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4), layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null), null);
                                break;
                            case NpcState.DOWN_LEFT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4, 0, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null),
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.DOWN_RIGHT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4, 0, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 2,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.DOWN_TWO_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        2 * (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4), 0, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.LEFT_LEFT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null),
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 2, null);
                                break;
                            case NpcState.LEFT_RIGHT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 2,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 2, null);
                                break;
                            case NpcState.LEFT_TWO_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        2 * (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4), layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 2, null);
                                break;
                            case NpcState.RIGHT_LEFT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 2, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null),
                                        3 * layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.RIGHT_RIGHT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 2, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 2,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * 3 / 4, null);
                                break;
                            case NpcState.RIGHT_TWO_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        2 * (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4), layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 2, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * 3 / 4, null);
                                break;
                            case NpcState.UP_LEFT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * 3 / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null),
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null), null);
                                break;
                            case NpcState.UP_RIGHT_STEP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4, 3 * layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 2,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null), null);
                                break;
                            case NpcState.UP_TWO_STAND:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState(0).getImg(), gx + 1, gy - (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) * zoom) / 4 + 1, gx + (int) (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * zoom) / 4 - 1, gy - 1,
                                        2 * (layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) / 4), 3 * layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState(0).getImg().getWidth(null) * 3 / 4,
                                        layer.getNpcAt(x, y).getNpcState(0).getImg().getHeight(null), null);
                                break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Point screenToTileCoords(int x, int y) {
        Dimension tsize = getTileSize();
        return new Point(x / tsize.width, y / tsize.height);
    }

    @Override
    public Point screenToPixelCoords(int x, int y) {
        return new Point(
                (int) (x / zoom), (int) (y / zoom));
    }

    @Override
    public Point tileToScreenCoords(int x, int y) {
        Dimension tsize = getTileSize();
        return new Point(x * tsize.width, y * tsize.height);
    }
}

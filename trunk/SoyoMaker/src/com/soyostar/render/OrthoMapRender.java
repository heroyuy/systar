/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.render;

import com.soyostar.model.map.CollideLayer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.SpriteLayer;
import com.soyostar.model.map.TileLayer;
import com.soyostar.model.map.SelectionLayer;
import com.soyostar.model.map.Tile;
import com.soyostar.model.script.Npc;
import com.soyostar.util.ImageUtil;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.SwingConstants;

/**
 *
 * @author Administrator
 */
public class OrthoMapRender extends MapRender {

    /**
     *
     * @param map
     */
    public OrthoMapRender(Map map) {
        super(map);
    }

    /**
     *
     */
    public OrthoMapRender() {
        super();
    }

    public String getName() {
        return "正视角地图";
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect,
        int orientation, int direction) {
        Dimension tsize = getTileSize();

        if (orientation == SwingConstants.VERTICAL) {
            return (visibleRect.height / tsize.height) * tsize.height;
        } else {
            return (visibleRect.width / tsize.width) * tsize.width;
        }
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect,
        int orientation, int direction) {
        Dimension tsize = getTileSize();
        if (orientation == SwingConstants.VERTICAL) {
            return tsize.height;
        } else {
            return tsize.width;
        }
    }

    public Dimension getPreferredSize() {
        Dimension tsize = getTileSize();

        return new Dimension(
            map.getWidth() * tsize.width,
            map.getHeight() * tsize.height);
    }

    @Override
    protected void paintGrid(Graphics2D g2d) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }
        // Determine lines to draw from clipping rectangle
        Rectangle clipRect = g2d.getClipBounds();
        int startX = clipRect.x / tsize.width * tsize.width;
        int startY = clipRect.y / tsize.height * tsize.height;
        int endX = clipRect.x + clipRect.width;
        int endY = clipRect.y + clipRect.height;
//        g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
//            1f, new float[]{5, 5,}, 0f));
        for (int x = startX; x < endX; x += tsize.width) {
            g2d.drawLine(x, clipRect.y, x, clipRect.y + clipRect.height);
        }
        for (int y = startY; y < endY; y += tsize.height) {
            g2d.drawLine(clipRect.x, y, clipRect.x + clipRect.width, y);
        }
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

    protected void paintTileLayer(Graphics2D g2d, TileLayer layer) {
        // Determine tile size and offset
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }
        Polygon gridPoly = createGridPolygon(0, -tsize.height, 0);

        // Determine area to draw from clipping rectangle
        Rectangle clipRect = g2d.getClipBounds();
        int startX = clipRect.x / tsize.width;
        int startY = clipRect.y / tsize.height;
        int endX = (clipRect.x + clipRect.width) / tsize.width + 1;
        int endY = (clipRect.y + clipRect.height) / tsize.height + 1;
        // (endY +2 for high tiles, could be done more properly)

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
            y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                x < endX; x++, gx += tsize.width) {
                Tile tile = layer.getTileAt(x, y);

                if (tile != null) {
                    if (layer instanceof SelectionLayer) {
                        gridPoly.translate(gx, gy);
                        g2d.fillPolygon(gridPoly);
                        gridPoly.translate(-gx, -gy);
                        //paintEdge(g, layer, gx, gy);
                    } else {
                        tile.draw(g2d, gx, gy, zoom);
                    }
                }
            }
        }
    }

    @Override
    public void repaintRegion(Rectangle region) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }

        // Calculate the visible corners of the region
        int startX = region.x * tsize.width;
        int startY = region.y * tsize.height;
        int endX = (region.x + region.width) * tsize.width;
        int endY = (region.y + region.height) * tsize.height;

        Rectangle dirty =
            new Rectangle(startX, startY, endX - startX, endY - startY);

        repaint(dirty);
//        map.getPreviewRender().repaint();
//        map.getPreviewRender().repaintRegion(region);
    }

    public Point tileToScreenCoords(int x, int y) {
        Dimension tsize = getTileSize();
        return new Point(x * tsize.width, y * tsize.height);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Point screenToTileCoords(int x, int y) {
        Dimension tsize = getTileSize();
        return new Point(x / tsize.width, y / tsize.height);
    }

    public Point screenToPixelCoords(int x, int y) {
        return new Point(
            (int) (x / zoom), (int) (y / zoom));
    }

    @Override
    protected void paintCollideLayer(Graphics2D g2d, CollideLayer layer) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }
//        Polygon gridPoly = createGridPolygon(0, -tsize.height, 0);

        // Determine area to draw from clipping rectangle
        Rectangle clipRect = g2d.getClipBounds();
        int startX = clipRect.x / tsize.width;
        int startY = clipRect.y / tsize.height;
        int endX = (clipRect.x + clipRect.width) / tsize.width + 1;
        int endY = (clipRect.y + clipRect.height) / tsize.height + 1;
        // (endY +2 for high tiles, could be done more properly)

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
            y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                x < endX; x++, gx += tsize.width) {
//                boolean bool = layer.getCollideAt(x, y);
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
//        Polygon gridPoly = createGridPolygon(0, -tsize.height, 0);

        // Determine area to draw from clipping rectangle
        Rectangle clipRect = g2d.getClipBounds();
        int startX = clipRect.x / tsize.width;
        int startY = clipRect.y / tsize.height;
        int endX = (clipRect.x + clipRect.width) / tsize.width + 1;
        int endY = (clipRect.y + clipRect.height) / tsize.height + 1;
        // (endY +2 for high tiles, could be done more properly)

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
            y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                x < endX; x++, gx += tsize.width) {
//                boolean bool = layer.getCollideAt(x, y);
                if (layer.getNpcAt(x, y) != null) {
                    g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER));
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(gx + 1, gy - tsize.height + 1, tsize.width - 1, tsize.height - 1);
                    g2d.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_ATOP, 0.25f));
                    g2d.fillRect(gx + 1, gy - tsize.height + 1, tsize.width - 1, tsize.height - 1);

                    if (layer.getNpcAt(x, y).getImg() != null) {
                        g2d.setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_ATOP, 1.0f));
                        switch (layer.getNpcAt(x, y).getFace()) {
                            case Npc.UP:
                                g2d.drawImage(layer.getNpcAt(x, y).getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                    0, 0, layer.getNpcAt(x, y).getImg().getWidth(null) / 7, layer.getNpcAt(x, y).getImg().getHeight(null), null);
                                break;
                            case Npc.DOWN:
                                g2d.drawImage(layer.getNpcAt(x, y).getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                    2 * layer.getNpcAt(x, y).getImg().getWidth(null) / 7, 0, 3 * layer.getNpcAt(x, y).getImg().getWidth(null) / 7,
                                    layer.getNpcAt(x, y).getImg().getHeight(null), null);
                                break;
                            case Npc.LEFT:
                                g2d.drawImage(layer.getNpcAt(x, y).getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                    4 * layer.getNpcAt(x, y).getImg().getWidth(null) / 7, 0, 5 * layer.getNpcAt(x, y).getImg().getWidth(null) / 7,
                                    layer.getNpcAt(x, y).getImg().getHeight(null), null);
                                break;
                            case Npc.RIGHT:
                                g2d.drawImage(ImageUtil.flipImage(layer.getNpcAt(x, y).getImg()), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                    2 * layer.getNpcAt(x, y).getImg().getWidth(null) / 7, 0, 3 * layer.getNpcAt(x, y).getImg().getWidth(null) / 7,
                                    layer.getNpcAt(x, y).getImg().getHeight(null), null);
                                break;
                        }

                    }

                }
            }
        }
    }
}

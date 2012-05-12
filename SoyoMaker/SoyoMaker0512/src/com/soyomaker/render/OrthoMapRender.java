/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.render;

import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.model.map.SelectionLayer;
import com.soyomaker.model.map.Tile;
import com.soyomaker.model.map.NpcState;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
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
                        tile.draw(g2d, gx, gy, zoom);
                        gridPoly.translate(-gx, -gy);
                    } else {
                        tile.draw(g2d, gx, gy, zoom);
                        if (!tile.isCross()) {
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
    }
    //向外扩大一个刷新，以支持自动图元对周围格子的影响

    @Override
    public void repaintRegion(Rectangle region) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }

        // Calculate the visible corners of the region
        int startX = Math.max((region.x - 1) * tsize.width, 0);
        int startY = Math.max((region.y - 1) * tsize.height, 0);
        int endX = (region.x + region.width + 1) * tsize.width;
        int endY = (region.y + region.height + 1) * tsize.height;

        Rectangle dirty =
                new Rectangle(startX, startY, endX - startX, endY - startY);

        repaint(dirty);
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

    /**
     * 
     * @param g2d
     * @param layer
     */
    @Override
    protected void paintCollideLayer(Graphics2D g2d, CollideLayer layer) {
        Dimension tsize = getTileSize();
        if (tsize.width <= 0 || tsize.height <= 0) {
            return;
        }
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

    /**
     * 
     * @param g2d
     * @param layer
     */
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
//                    System.out.println("npc");
                    g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER));
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(gx + 1, gy - tsize.height + 1, tsize.width - 2, tsize.height - 2);
                    g2d.setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_ATOP, 0.5f));
                    g2d.fillRect(gx + 2, gy - tsize.height + 2, tsize.width - 4, tsize.height - 4);
                    g2d.setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_ATOP, 1.0f));
                    if (layer.getNpcAt(x, y).getNpcState().
                            getImg() != null) {
                        switch (layer.getNpcAt(x, y).getNpcState().getFace()) {
                            case NpcState.DOWN:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState().getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                        0, 0, layer.getNpcAt(x, y).getNpcState().getImg().getWidth(null) / 4, layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.LEFT:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState().getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                        0, layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null) / 4, layer.getNpcAt(x, y).getNpcState().getImg().getWidth(null) / 4,
                                        2 * layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.RIGHT:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState().getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                        0, 2 * (layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null) / 4), layer.getNpcAt(x, y).getNpcState().getImg().getWidth(null) / 4,
                                        3 * layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null) / 4, null);
                                break;
                            case NpcState.UP:
                                g2d.drawImage(layer.getNpcAt(x, y).getNpcState().getImg(), gx + 1, gy - tsize.height + 1, gx + tsize.width - 1, gy - 1,
                                        0, 3 * (layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null) / 4), layer.getNpcAt(x, y).getNpcState().getImg().getWidth(null) / 4,
                                        layer.getNpcAt(x, y).getNpcState().getImg().getHeight(null), null);
                                break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void paintPlayer(Graphics2D g2d, int col, int row) {
        Dimension tsize = getTileSize();
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 0.8f));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(col * tsize.width + 1, row * tsize.height + 1, tsize.width - 2, tsize.height - 2);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(col * tsize.width, row * tsize.height, tsize.width, tsize.height);

        g2d.setFont(new Font("微软雅黑", Font.PLAIN, tsize.height));
        FontRenderContext context = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout("S", g2d.getFont(), context);
        Rectangle2D bounds = layout.getBounds();
        g2d.drawString("S", (int) (col * tsize.width + (tsize.width - bounds.getWidth()) / 2), (int) (row * tsize.height + (bounds.getHeight() + tsize.height) / 2));

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 1.0f));
    }
}

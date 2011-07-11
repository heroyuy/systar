/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.render;

import com.soyostar.model.map.Layer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.Tile;
import com.soyostar.model.map.TileLayer;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class OrthoPreviewRender extends PreviewRender {

    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0x3B3B3B);       //背景色

    public OrthoPreviewRender(Map map) {
        super(map);
        zoom = (float) 250 / ((float) map.getWidth() * map.getHeight());
        System.out.println("zoom:" + zoom);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
//        Dimension tsize = getTileSize();
        Rectangle clip = g.getClipBounds();
        g2d.setClip(clip);
//        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(DEFAULT_BACKGROUND_COLOR);
        g2d.fillRect(clip.x, clip.y, clip.width, clip.height);
//        g2d.fillRect(0, 0, map.getWidth() * tsize.width,
//            map.getHeight() * tsize.height);
        paintMap(map, g2d);
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

    }

    /**
     *
     * @param m
     * @param g2d
     * @param mapOpacity
     */
    public void paintMap(Map m, Graphics2D g2d) {

        Iterator li = m.getLayers();
        Layer layer;
        while (li.hasNext()) {
            layer = (Layer) li.next();
            if (layer != null) {
//                if (layer.isIsVisible()) {
                g2d.setComposite(AlphaComposite.SrcOver);
                if (layer instanceof TileLayer) {
                    paintTileLayer(g2d, (TileLayer) layer);
                }

//                }
            }
        }
    }

    /**
     *
     * @return
     */
    protected Dimension getTileSize() {
        return new Dimension(
            (int) (map.getTileWidth() * zoom),
            (int) (map.getTileHeight() * zoom));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension tsize = getTileSize();

        return new Dimension(
            map.getWidth() * tsize.width,
            map.getHeight() * tsize.height);
    }

    protected void paintTileLayer(Graphics2D g2d, TileLayer layer) {
        // Determine tile size and offset
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
        int endY = (clipRect.y + clipRect.height) / tsize.height + 3;
        // (endY +2 for high tiles, could be done more properly)

        // Draw this map layer
        for (int y = startY, gy = (startY + 1) * tsize.height;
            y < endY; y++, gy += tsize.height) {
            for (int x = startX, gx = startX * tsize.width;
                x < endX; x++, gx += tsize.width) {
                Tile tile = layer.getTileAt(x, y);

                if (tile != null) {
                    tile.draw(g2d, gx, gy, zoom);
                }
            }
        }
    }
}

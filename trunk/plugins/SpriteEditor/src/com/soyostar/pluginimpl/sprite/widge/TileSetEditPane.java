/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.widge;

import com.soyostar.pluginimpl.sprite.model.Tile;
import com.soyostar.pluginimpl.sprite.model.TileSet;
import com.soyostar.pluginimpl.sprite.util.FrameTileSelection;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author Administrator
 */
public class TileSetEditPane extends JPanel implements Scrollable {

    private Color background = new Color(0x458D7F);
    private TileSet tileset;
    private int selectedIndex = -1;
    private int zoom = 1;

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 16;
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return (visibleRect.height / 16) * 16;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    /**
     *
     * @return
     */
    public int getZoom() {
        return zoom;
    }

    /**
     *
     * @return
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     *
     * @return
     */
    public TileSet getTileSet() {
        return tileset;
    }

    /**
     *
     * @param zoom
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
        this.updateUI();
    }

    /**
     *
     */
    public void zoomUp() {
        this.setZoom(Math.min(this.zoom + 1, 8));
    }

    /**
     *
     */
    public void zoomDown() {
        this.setZoom(Math.max(this.zoom - 1, 1));
    }

    /**
     *
     * @param index
     */
    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
        this.updateUI();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (this.tileset == null) {
            return size;
        } else {
            Image buff = this.tileset.getTileSetImage();
            int w = buff.getWidth(null) * this.zoom;
            int h = buff.getHeight(null) * this.zoom;
            return new Dimension(Math.max(size.width, w), Math.max(size.height, h));
        }
    }

    /**
     *
     * @param tileset
     */
    public void setTileSet(TileSet tileset) {
        if (tileset == null) {
            setSelectedIndex(-1);
        }
        this.tileset = tileset;
        this.updateUI();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (this.tileset != null) {
            BufferedImage img = this.tileset.getTileSetImage();
            g2d.drawImage(img, 0, 0, img.getWidth() * this.zoom, img.getHeight() * this.zoom, null);
            g2d.setColor(Color.YELLOW);
            g2d.drawRect(0, 0, img.getWidth() * this.zoom, img.getHeight() * this.zoom);
            Iterator ti = this.tileset.getTiles();
            Tile tile;
            g2d.setColor(Color.GREEN);
            while (ti.hasNext()) {
                tile = (Tile) ti.next();
                if (tile != null) {
                    g2d.draw3DRect(tile.getX() * zoom, tile.getY() * zoom, tile.getWidth() * zoom, tile.getHeight() * zoom, false);
                }
            }
            if (this.selectedIndex >= 0) {
                g2d.setColor(Color.RED);
                Tile m = this.tileset.getTile(this.selectedIndex);
                g2d.draw3DRect(m.getX() * zoom, m.getY() * zoom, m.getWidth() * zoom, m.getHeight() * zoom, true);
                g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_ATOP, 0.2f));
                g.fillRect(m.getX() * zoom, m.getY() * zoom, m.getWidth() * zoom, m.getHeight() * zoom);
            }
        }
    }
}

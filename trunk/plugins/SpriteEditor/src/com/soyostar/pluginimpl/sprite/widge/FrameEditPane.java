/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.widge;

import com.soyostar.pluginimpl.sprite.data.Proxy;
import com.soyostar.pluginimpl.sprite.model.Frame;
import com.soyostar.pluginimpl.sprite.model.Layer;
import com.soyostar.pluginimpl.sprite.model.Module;
import com.soyostar.pluginimpl.sprite.model.ModuleLayer;
import com.soyostar.pluginimpl.sprite.model.Tile;
import com.soyostar.pluginimpl.sprite.model.ModuleLayer;
import com.soyostar.pluginimpl.sprite.util.FrameTileSelection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author Administrator
 */
public class FrameEditPane extends JPanel implements DropTargetListener, Scrollable {

//    /**
//     *
//     */
//    public int frameWidth = 320;
//    /**
//     *
//     */
//    public int frameHeight = 320;
    /**
     *
     */
    public Frame frame;
    /**
     *
     */
    public int selectedIndex = -1;

    /**
     *
     */
//    public int zoom = 1;
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 32;
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return (visibleRect.height / 32) * 32;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

//    /**
//     *
//     * @param zoom
//     */
//    public void setZoom(int zoom) {
//        this.zoom = zoom;
//        this.updateUI();
//    }
//
//    /**
//     *
//     */
//    public void zoomUp() {
//        this.setZoom(Math.min(this.zoom + 1, 8));
//    }
//
//    /**
//     *
//     */
//    public void zoomDown() {
//        this.setZoom(Math.max(this.zoom - 1, 1));
//    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        int tw = getWidth();
        int th = getHeight();

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, tw, th);

        g2d.setColor(new Color(0, 120, 0));
        g2d.drawLine(0, th >> 1, tw, th >> 1);
        g2d.drawLine(tw >> 1, 0, tw >> 1, th);

        if (this.frame != null) {
            this.frame.paint(g, tw >> 1, th >> 1, 1);
            if (this.selectedIndex >= 0) {
                g2d.setColor(Color.RED);
                Layer layer = this.frame.getLayer(Proxy.getInstance().getMainDialog().getCurrentLayerIndex());
                if (layer instanceof ModuleLayer) {
                    Module mo = ((ModuleLayer) layer).getModule(selectedIndex);
                    if (mo != null) {
                        g2d.drawRect(mo.getX() + (tw >> 1), mo.getY() + (th >> 1),
                            mo.getWidth(), mo.getHeight());
                    }
                }
            }
        }
    }

    /**
     *
     * @param index
     */
    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
        this.updateUI();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     *
     * @param frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
        this.updateUI();
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void drop(DropTargetDropEvent event) {
        if (this.frame != null) {
            try {
                Transferable transferable = event.getTransferable();
                Object o = transferable.getTransferData(
                    FrameTileSelection.TILE_FLAVOR);
                if (o != null) {
                    if (o instanceof Tile) {
                        Tile tile = (Tile) o;
                        Module m = new Module();
                        Point p = event.getLocation();

                        Layer layer = this.frame.getLayer(Proxy.getInstance().getMainDialog().getCurrentLayerIndex());
                        if (layer.isVisible()) {
                            if (layer instanceof ModuleLayer) {
                                ModuleLayer ml = (ModuleLayer) layer;
                                m.setLayer(ml);
                                m.setTile(tile);
                                System.out.println("x:" + (p.x - (getWidth() + m.getTile().getWidth() >> 1)));
                                System.out.println("y:" + (p.y - (getHeight() + m.getTile().getHeight() >> 1)));
                                m.setPoint(new Point(p.x - (getWidth() + m.getTile().getWidth() >> 1),
                                    p.y - (getHeight() + m.getTile().getHeight() >> 1)));
                                System.out.println("w:" + m.getWidth());
                                System.out.println("h:" + m.getHeight());
                                ml.addModule(m);
                                this.frame.updateBounds();
                                System.out.println("add");
                            }
                            this.updateUI();
                        }
                    }
                }
                event.dropComplete(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.adpter;

import com.soyostar.pluginimpl.sprite.data.Proxy;
import com.soyostar.pluginimpl.sprite.model.Tile;
import com.soyostar.pluginimpl.sprite.util.FrameTileSelection;
import com.soyostar.pluginimpl.sprite.widge.TileSetEditPane;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Administrator
 */
public class TileSetEditMouseAdapter extends MouseInputAdapter implements DragGestureListener {

    private Point createOrgine = new Point();
    private Point createTraget = new Point();
    private Point resizeOrgine = new Point();
    private Point resizeTraget = new Point();
    private Point moveOrgine = new Point();
    private Point moveTraget = new Point();
    private Cursor createCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
    private Cursor resizeCursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
    private Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);
    private TileSetEditPane traget;

    /**
     *
     * @param traget
     */
    public TileSetEditMouseAdapter(TileSetEditPane traget) {
        this.traget = traget;
    }

    public void dragGestureRecognized(DragGestureEvent event) {
        if (button == MouseEvent.BUTTON3) {//只对右键的拖拽起反应
            if (this.traget.getTileSet() != null) {
                Tile tile = this.traget.getTileSet().getTile(this.traget.getSelectedIndex());
                if (tile != null) {
                    Transferable transferable = new FrameTileSelection(tile);
                    event.startDrag(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), transferable);
                }
            }
        }
    }
    private int button = 0;

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        traget.requestFocus();
        int zoom = this.traget.getZoom();
        if (traget.getTileSet() != null) {
            switch (Proxy.getInstance().getOpType()) {
                case Proxy.PEN:
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        button = MouseEvent.BUTTON1;

                        if (e.isControlDown()) {
                            this.createOrgine.setLocation(e.getX(), e.getY());
                            this.createTraget.setLocation(e.getX(), e.getY());
                            Tile newTile = new Tile(this.traget.getTileSet(), e.getX() / zoom, e.getY() / zoom, 1, 1);
                            this.traget.getTileSet().addTile(newTile);
                            int i = this.traget.getTileSet().getTileCounts() - 1;
                            traget.setSelectedIndex(i);
                            this.traget.setCursor(this.createCursor);
                        } else {
                            int cx = e.getX() / zoom;
                            int cy = e.getY() / zoom;
                            Iterator ti = this.traget.getTileSet().getTiles();
                            Tile tile;
                            while (ti.hasNext()) {
                                tile = (Tile) ti.next();
                                if (tile.contains(cx, cy)) {
                                    traget.setSelectedIndex(this.traget.getTileSet().indexOf(tile));
                                    this.resizeOrgine.setLocation(e.getX(), e.getY());
                                    this.resizeTraget.setLocation(e.getX(), e.getY());
                                    this.moveOrgine.setLocation(e.getX(), e.getY());
                                    this.moveTraget.setLocation(e.getX(), e.getY());
                                    if (e.isShiftDown()) {
                                        this.traget.setCursor(this.resizeCursor);
                                    } else if (e.isAltDown()) {
                                        this.traget.setCursor(this.moveCursor);
                                    }
                                    return;
                                }
                            }
                            traget.setSelectedIndex(-1);
                        }
                    }
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        button = MouseEvent.BUTTON3;
                        int cx = e.getX() / zoom;
                        int cy = e.getY() / zoom;
                        Iterator ti = this.traget.getTileSet().getTiles();
                        Tile tile;
                        while (ti.hasNext()) {
                            tile = (Tile) ti.next();
                            if (tile.contains(cx, cy)) {
                                traget.setSelectedIndex(this.traget.getTileSet().indexOf(tile));
                                this.resizeOrgine.setLocation(e.getX(), e.getY());
                                this.resizeTraget.setLocation(e.getX(), e.getY());
                                this.moveOrgine.setLocation(e.getX(), e.getY());
                                this.moveTraget.setLocation(e.getX(), e.getY());
                                if (e.isShiftDown()) {
                                    this.traget.setCursor(this.resizeCursor);
                                } else if (e.isAltDown()) {
                                    this.traget.setCursor(this.moveCursor);
                                }
                                return;
                            }
                        }
                        traget.setSelectedIndex(-1);
                    }
                    break;
                case Proxy.ERASER:
                    button = MouseEvent.BUTTON1;
                    int cx = e.getX() / zoom;
                    int cy = e.getY() / zoom;
                    Iterator ti = this.traget.getTileSet().getTiles();
                    Tile tile;
                    while (ti.hasNext()) {
                        tile = (Tile) ti.next();
                        if (tile.contains(cx, cy)) {
                            this.traget.getTileSet().removeTile(tile);
                            traget.repaint();
                            return;
                        }
                    }
                    break;
            }


        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int zoom = this.traget.getZoom();
        if (traget.getTileSet() != null) {
            Tile tile = this.traget.getTileSet().getTile(this.traget.getSelectedIndex());

            if (tile != null) {
                if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == MouseEvent.BUTTON1_MASK) {
                    if (e.isControlDown()) {
                        this.createTraget.setLocation(e.getX(), e.getY());
                        int ww = (this.createTraget.x - this.createOrgine.x) / zoom;
                        int hh = (this.createTraget.y - this.createOrgine.y) / zoom;
                        if (ww < 1) {
                            ww = 1;
                        }
                        if (ww > this.traget.getTileSet().getTileSetImage().getWidth() - tile.getX()) {
                            ww = this.traget.getTileSet().getTileSetImage().getWidth() - tile.getX();
                        }
                        if (hh < 1) {
                            hh = 1;
                        }
                        if (hh > this.traget.getTileSet().getTileSetImage().getHeight() - tile.getY()) {
                            hh = this.traget.getTileSet().getTileSetImage().getHeight() - tile.getY();
                        }
                        tile.setSize(ww, hh);
                    } else if (e.isShiftDown()) {
                        this.resizeTraget.setLocation(e.getX(), e.getY());
                        int mx = this.resizeTraget.x - this.resizeOrgine.x;
                        int my = this.resizeTraget.y - this.resizeOrgine.y;
                        if (Math.abs(mx) >= zoom) {
                            int ww = tile.getWidth() + mx / zoom;
                            if (ww < 1) {
                                ww = 1;
                            }
                            if (ww > this.traget.getTileSet().getTileSetImage().getWidth() - tile.getX()) {
                                ww = this.traget.getTileSet().getTileSetImage().getWidth() - tile.getX();
                            }
                            tile.setWidth(ww);
                            this.resizeOrgine.x = this.resizeTraget.x;
                        }
                        if (Math.abs(my) >= zoom) {
                            int hh = tile.getHeight() + my / zoom;
                            if (hh < 1) {
                                hh = 1;
                            }
                            if (hh > this.traget.getTileSet().getTileSetImage().getHeight() - tile.getY()) {
                                hh = this.traget.getTileSet().getTileSetImage().getHeight() - tile.getY();
                            }
                            tile.setHeight(hh);
                            this.resizeOrgine.y = this.resizeTraget.y;
                        }
                    } else {
                        this.moveTraget.setLocation(e.getX(), e.getY());
                        int mx = this.moveTraget.x - this.moveOrgine.x;
                        int my = this.moveTraget.y - this.moveOrgine.y;
                        if (Math.abs(mx) >= zoom) {
                            int xx = tile.getX() + mx / zoom;
                            if (xx < 0) {
                                xx = 0;
                            }
                            if (xx > this.traget.getTileSet().getTileSetImage().getWidth() - tile.getWidth()) {
                                xx = this.traget.getTileSet().getTileSetImage().getWidth() - tile.getWidth();
                            }
                            tile.setX(xx);
                            this.moveOrgine.x = this.moveTraget.x;
                        }
                        if (Math.abs(my) >= zoom) {
                            int yy = tile.getY() + my / zoom;
                            if (yy < 0) {
                                yy = 0;
                            }
                            if (yy > this.traget.getTileSet().getTileSetImage().getHeight() - tile.getHeight()) {
                                yy = this.traget.getTileSet().getTileSetImage().getHeight() - tile.getHeight();
                            }
                            tile.setY(yy);
                            this.moveOrgine.y = this.moveTraget.y;
                        }
                    }
                }
//                if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) == MouseEvent.BUTTON3_MASK) {
//
//                }
            }

            traget.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.traget.setCursor(Cursor.getDefaultCursor());
    }
}

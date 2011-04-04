/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.adpter;

import com.soyostar.pluginimpl.sprite.data.Proxy;
import com.soyostar.pluginimpl.sprite.model.Layer;
import com.soyostar.pluginimpl.sprite.model.Module;
import com.soyostar.pluginimpl.sprite.model.ModuleLayer;
import com.soyostar.pluginimpl.sprite.widge.FrameEditPane;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Administrator
 */
public class FrameEditMouseAdapter extends MouseInputAdapter {

    private FrameEditPane traget;
    private Point moveOrgine = new Point();
    private Point moveTraget = new Point();
    private Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);

    /**
     *
     * @param traget
     */
    public FrameEditMouseAdapter(FrameEditPane traget) {
        this.traget = traget;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.traget.requestFocus();
//            int zoom = this.traget.zoom;
            if (this.traget.frame != null) {
                int cx = e.getX() - this.traget.getWidth() / 2;
                int cy = e.getY() - this.traget.getHeight() / 2;
                Layer layer = this.traget.frame.getLayer(Proxy.getInstance().getMainDialog().getCurrentLayerIndex());
                switch (Proxy.getInstance().getOpType()) {
                    case Proxy.PEN:

                        if (layer instanceof ModuleLayer) {

                            for (int i = 0; i < ((ModuleLayer) layer).getModuleCounts(); i++) {
                                Module tile = ((ModuleLayer) layer).getModule(i);
                                if (tile.contains(cx, cy)) {
                                    this.moveOrgine.setLocation(e.getX(), e.getY());
                                    this.moveTraget.setLocation(e.getX(), e.getY());
                                    this.traget.setCursor(this.moveCursor);
                                    this.traget.setSelectedIndex(i);
                                    return;
                                }
                            }
                        }
                        this.traget.setSelectedIndex(-1);
                        break;
                    case Proxy.ERASER:
                        Iterator ti = ((ModuleLayer) layer).getModules();
                        Module tile = null;
                        while (ti.hasNext()) {
                            tile = (Module) ti.next();
                            if (tile.contains(cx, cy)) {
                                ((ModuleLayer) layer).removeModule(tile);
                                this.traget.repaint();
                                return;
                            }
                        }
                        break;
                }

            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == MouseEvent.BUTTON1_MASK) {
//            int zoom = this.traget.zoom;
            if (this.traget.frame != null) {
                Layer layer = this.traget.frame.getLayer(Proxy.getInstance().getMainDialog().getCurrentLayerIndex());
                if (layer instanceof ModuleLayer) {
                    Module mo = ((ModuleLayer) layer).getModule(traget.getSelectedIndex());
                    if (mo != null) {
                        this.moveTraget.setLocation(e.getX(), e.getY());
                        int mx = this.moveTraget.x - this.moveOrgine.x;
                        int my = this.moveTraget.y - this.moveOrgine.y;
                        if (Math.abs(mx) >= 1) {
                            int xx = mo.getX() + mx;
                            mo.setX(xx);
                            this.moveOrgine.x = this.moveTraget.x;
                        }
                        if (Math.abs(my) >= 1) {
                            int yy = mo.getY() + my;
                            mo.setY(yy);
                            this.moveOrgine.y = this.moveTraget.y;
                        }
                    }
                }
                this.traget.frame.updateBounds();
                traget.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.traget.setCursor(Cursor.getDefaultCursor());
    }
}

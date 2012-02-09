/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.render;

import com.soyomaker.brush.AbBrush;
import com.soyomaker.brush.CustomBrush;
import com.soyomaker.config.Preference;
import com.soyomaker.AppData;
import com.soyomaker.dialog.EventManagerDialog;
import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.model.map.SelectionLayer;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.Tile;
import com.soyomaker.undoredo.LayerEdit;
import com.soyomaker.undoredo.UndoRedoHandler;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.undo.UndoableEditSupport;

/**
 * 地图绘制器
 * @author Administrator
 */
public abstract class MapRender extends JPanel implements MouseListener, MouseMotionListener, Scrollable, MouseWheelListener {

    /**
     * 
     */
    public static final int ZOOM_NORMALSIZE = 5;
    /**
     * 
     */
    protected double zoom = 1.0;                    //正常缩放级别为1
    /**
     * 
     */
    protected int zoomLevel = ZOOM_NORMALSIZE;      //初始话为正常缩放级别
    /**
     * 
     */
    protected Map map;                              //所属地图
    /**
     * 
     */
    protected boolean showGrid = false;              //是否显示网格
    /**
     * 
     */
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0x3B3B3B);       //背景色
    /**
     * 
     */
    public static final Color DEFAULT_GRID_COLOR = new Color(0, 0, 0);              //网格颜色
    /**
     * 
     */
    protected AbBrush currentBrush;
    /**
     *
     */
    protected static double[] zoomLevels = {
        0.0625, 0.125, 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0
    };
    private LayerEdit paintEdit;
    private final UndoRedoHandler undoHandler;
    private final UndoableEditSupport undoSupport;

    /**
     *
     * @param map
     */
    protected MapRender(Map map) {
        this();
        cursorSelectionLayer.select(0, 0);
        cursorSelectionLayer.setVisible(true);
        setMap(map);
    }

    /**
     *
     */
    protected MapRender() {
        undoHandler = new UndoRedoHandler(this);
        undoSupport = new UndoableEditSupport();
        undoSupport.addUndoableEditListener(undoHandler);
        this.setOpaque(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    /**
     * Sets a new brush. The brush can draw a preview of the change while
     * editing.
     * @param brush the new brush
     */
    public void setBrush(AbBrush brush) {
        currentBrush = brush;
    }

    /**
     * 
     * @return
     */
    public AbBrush getBrush() {
        return currentBrush;
    }

    /**
     *
     * @return
     */
    public Map getMap() {
        return map;
    }

    /**
     *
     * @param map
     */
    public void setMap(Map map) {
        this.map = map;
        map.setMapRender(this);
    }

    /**
     *
     * @return
     */
    public boolean isShowGrid() {
        return showGrid;
    }

    /**
     *
     * @param showGrid
     */
    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
    }

    /**
     *
     * @return
     */
    public boolean zoomIn() {
        if (zoomLevel < zoomLevels.length - 1) {
            setZoomLevel(zoomLevel + 1);
        }

        return zoomLevel < zoomLevels.length - 1;
    }

    /**
     *
     * @return
     */
    public boolean zoomOut() {
        if (zoomLevel > 0) {
            setZoomLevel(zoomLevel - 1);
        }

        return zoomLevel > 0;
    }

    /**
     *
     * @param zoom
     */
    public void setZoom(double zoom) {
        if (zoom > 0) {
            this.zoom = zoom;
            //revalidate();
            setSize(getPreferredSize());
        }
    }

    /**
     *
     * @param zoomLevel
     */
    public void setZoomLevel(int zoomLevel) {
        if (zoomLevel >= 0 && zoomLevel < zoomLevels.length) {
            this.zoomLevel = zoomLevel;
            setZoom(zoomLevels[zoomLevel]);
        }
    }

    private void scrollTileToVisible(Point tile) {
        int twidth = map.getTileWidth();
        int theight = map.getTileHeight();
        scrollRectToVisible(new Rectangle(
                tile.x * twidth,
                tile.y * theight,
                twidth, theight));
    }

    @Override
    public abstract String getName();

    @Override
    public abstract Dimension getPreferredSize();

    /**
     * Tells this view a certain region of the map needs to be repainted.
     * <p>
     * Same as calling repaint() unless implemented more efficiently in a
     * subclass.
     *
     * @param region the region that has changed in tile coordinates
     */
    public void repaintRegion(Rectangle region) {
        repaint();
    }

    /**
     * Draws all the visible layers of the map. Takes several flags into
     * account when drawing, and will also draw the grid, and any 'special'
     * layers.
     *
     * @param g the Graphics2D object to paint to
     * @see javax.swing.JComponent#paintComponent(Graphics)
     * @see MapLayer
     * @see SelectionLayer
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Rectangle clip = g.getClipBounds();
        g2d.setClip(clip);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(DEFAULT_BACKGROUND_COLOR);
        g2d.fillRect(clip.x, clip.y, clip.width, clip.height);
        paintMap(g2d);
        if (showGrid) {
            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.setStroke(new BasicStroke());
            g2d.setColor(DEFAULT_GRID_COLOR);
            paintGrid(g2d);
        }
    }
    private AppData data = AppData.getInstance();
    private SelectionLayer cursorSelectionLayer = new SelectionLayer(1, 1); //当前选框

    /**
     *
     * @return  
     */
    public SelectionLayer getCursorSelectionLayer() {
        return cursorSelectionLayer;
    }

    /**
     * 
     * @param g2d
     */
    public void paintSelection(Graphics2D g2d) {
        g2d.setColor(new Color(128, 128, 255));
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 0.75f));
        paintTileLayer(g2d, cursorSelectionLayer);
    }

    /**
     *
     * 
     * @param g2d
     */
    public void paintMap(Graphics2D g2d) {
        Iterator li = map.getLayers();
        Layer layer;
        while (li.hasNext()) {
            layer = (Layer) li.next();
            if (layer != null) {
                if (layer.isVisible()) {
                    g2d.setComposite(AlphaComposite.SrcOver);
                    if (layer instanceof TileLayer) {
                        paintTileLayer(g2d, (TileLayer) layer);
                    } else if (layer instanceof CollideLayer) {
                        paintCollideLayer(g2d, (CollideLayer) layer);
                    } else if (layer instanceof SpriteLayer) {
                        paintSpriteLayer(g2d, (SpriteLayer) layer);
                    }

                }
            }
        }
        paintSelection(g2d);//选框在最上层
    }

    /**
     * Draws a TileLayer. Implemented in a subclass.
     *
     * @param g2d   the graphics context to draw the layer onto
     * @param layer the TileLayer to be drawn
     */
    protected abstract void paintTileLayer(Graphics2D g2d, TileLayer layer);//画图层

    /**
     * 
     * @param g2d
     * @param layer
     */
    protected abstract void paintCollideLayer(Graphics2D g2d, CollideLayer layer);//画碰撞层

    /**
     * 
     * @param g2d
     * @param layer
     */
    protected abstract void paintSpriteLayer(Graphics2D g2d, SpriteLayer layer);//画碰撞层

    /**
     * Draws the map grid.
     *
     * @param g2d the graphics context to draw the grid onto
     */
    protected abstract void paintGrid(Graphics2D g2d);                  //画网格

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public abstract Point screenToTileCoords(int x, int y);             //屏幕坐标转为瓷砖位置

    /**
     * Returns the pixel coordinates on the map based on the given screen
     * coordinates. The map pixel coordinates may be different in more ways
     * than the zoom level, depending on the projection the view implements.
     *
     * @param x x in screen coordinates
     * @param y y in screen coordinates
     * @return the position in map pixel coordinates
     */
    public abstract Point screenToPixelCoords(int x, int y);            //屏幕坐标转为像素坐标

    /**
     * Returns the location on the screen of the top corner of a tile.
     *
     * @param x
     * @param y
     * @return Point
     */
    public abstract Point tileToScreenCoords(int x, int y);             //瓷砖位置转为屏幕坐标

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public abstract int getScrollableBlockIncrement(Rectangle visibleRect,
            int orientation, int direction);

    public abstract int getScrollableUnitIncrement(Rectangle visibleRect,
            int orientation, int direction);
    /**
     *
     */
    public Point mouseInitialPressLocation;
    /**
     *
     */
    public Point mousePressLocation;

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            setZoomLevel(zoomLevel + e.getWheelRotation());
            this.repaint();
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Point tile = screenToTileCoords(e.getX(), e.getY());
        mouseButton = e.getButton();
        mousePressLocation = screenToTileCoords(e.getX(), e.getY());
        scrollTileToVisible(mousePressLocation);
        mouseInitialPressLocation = mousePressLocation;
        //左键按下
        if (e.getButton() == MouseEvent.BUTTON1) {

            switch (data.currentPsType) {
                case AppData.PS_PEN:
                    if (data.getCurrentLayer() instanceof TileLayer) {
//                        ((TileLayer) data.getCurrentLayer()).setSync(true);
                        currentBrush.startPaint(map, tile.x, tile.y,
                                e.getButton(), map.indexOfLayer(data.getCurrentLayer()));
//                        ((TileLayer) data.getCurrentLayer()).setSync(false);
//                        System.out.println("paint");
                        paintEdit =
                                new LayerEdit(data.getCurrentLayer(), createLayerCopy(data.getCurrentLayer()), null);
                    } else if (data.getCurrentLayer() instanceof CollideLayer) {
                        currentBrush.startPaint(map, tile.x, tile.y,
                                e.getButton(), map.indexOfLayer(data.getCurrentLayer()));
//                        ((CollideLayer) data.getCurrentLayer()).setCollideAt(tile.x, tile.y, true);
                        paintEdit =
                                new LayerEdit(data.getCurrentLayer(), createLayerCopy(data.getCurrentLayer()), null);
                    } else if (data.getCurrentLayer() instanceof SpriteLayer) {
                        if (e.getClickCount() >= 2) {
                            EventManagerDialog emd = new EventManagerDialog(data.getMainFrame(), true);
                            if (((SpriteLayer) data.getCurrentLayer()).getNpcAt(tile.x, tile.y) != null) {
//                                System.out.println("npc不为空");
                                emd.setNpc(((SpriteLayer) data.getCurrentLayer()).getNpcAt(tile.x, tile.y));
                            } else {
                                Npc npc = new Npc();
                                npc.addNpcState(new NpcState());
                                npc.setMapId(map.getIndex());
                                npc.setRow(tile.y);
                                npc.setCol(tile.x);
                                emd.setNpc(npc);
                            }
                            emd.setVisible(true);
                        }
                    }
                    break;
                case AppData.PS_ERASER:
//                    if (data.getCurrentLayer() instanceof TileLayer) {
//                        ((TileLayer) data.getCurrentLayer()).setSync(true);
//                    }
                    paintEdit =
                            new LayerEdit(data.getCurrentLayer(), createLayerCopy(data.getCurrentLayer()), null);
                    break;
                case AppData.PS_FILL:
//                    if (data.getCurrentLayer() instanceof TileLayer) {
//                        ((TileLayer) data.getCurrentLayer()).setSync(true);
//                    }
                    paintEdit =
                            new LayerEdit(data.getCurrentLayer(), createLayerCopy(data.getCurrentLayer()), null);
                    break;
                case AppData.PS_CHOOSE:
                    break;
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (Preference.getIsRightEraser()) {
                switch (data.currentPsType) {
                    case AppData.PS_PEN:
                    case AppData.PS_ERASER:
//                        if (data.getCurrentLayer() instanceof TileLayer) {
//                            ((TileLayer) data.getCurrentLayer()).setSync(true);
//                        }
                        paintEdit =
                                new LayerEdit(data.getCurrentLayer(), createLayerCopy(data.getCurrentLayer()), null);
                        break;
                }
            }
        }
        mouse(e);
    }

    /**
     * 
     * @param layer
     * @return
     */
    protected static Layer createLayerCopy(Layer layer) {
        try {
            return (Layer) layer.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the {@link UndoableEditSupport} instance.
     * @return the undo support
     */
    public UndoableEditSupport getUndoSupport() {
        return undoSupport;
    }

    /**
     * Returns the {@link UndoHandler} instance.
     * @return the undo stack
     */
    public UndoRedoHandler getUndoHandler() {
        return undoHandler;
    }

    public void mouseReleased(MouseEvent e) {
        Point limp = mouseInitialPressLocation;
        switch (data.currentPsType) {
            case AppData.PS_PEN:
                if (data.getCurrentLayer() instanceof TileLayer) {
                    currentBrush.endPaint();
//                    ((TileLayer) data.getCurrentLayer()).setSync(false);
                } else if (data.getCurrentLayer() instanceof CollideLayer) {
                    currentBrush.endPaint();
                } else if (data.getCurrentLayer() instanceof SpriteLayer) {
                }
                break;
            case AppData.PS_ERASER:
//                if (data.getCurrentLayer() instanceof TileLayer) {
//                    ((TileLayer) data.getCurrentLayer()).setSync(false);
//                }
                break;
            case AppData.PS_CHOOSE:
                break;
            case AppData.PS_FILL:
//                if (data.getCurrentLayer() instanceof TileLayer) {
//                    ((TileLayer) data.getCurrentLayer()).setSync(false);
//                }
                break;
        }
        repaint();
        if (paintEdit != null) {
            if (data.getCurrentLayer() != null) {
                try {
//                    Layer endLayer = paintEdit.getStart().createDiff(data.getCurrentLayer());
                    Layer endLayer = (Layer) data.getCurrentLayer().clone();
                    endLayer.setMap(map);
                    paintEdit.end(endLayer);
                    undoSupport.postEdit(paintEdit);
//                    System.out.println("paint");
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
            paintEdit = null;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        mouse(e);
        mousePressLocation = screenToTileCoords(e.getX(), e.getY());
        scrollTileToVisible(mousePressLocation);
        Point tile = screenToTileCoords(e.getX(), e.getY());
        updateCursorHighlight(tile);
    }

    public void mouseMoved(MouseEvent e) {
        Point tile = screenToTileCoords(e.getX(), e.getY());
        if (e.getX() / map.getTileWidth() < map.getWidth() && e.getY() / map.getTileHeight() < map.getHeight()) {
            data.getMainFrame().stateLabel.setText(
                    " 当前位置: " + e.getY() / map.getTileHeight() + "行" + e.getX() / map.getTileWidth() + "列");
        }
        updateCursorHighlight(tile);
    }
    private int mouseButton;

    /**
     * 
     * @param e
     */
    public void mouse(MouseEvent e) {
        Point tile = screenToTileCoords(e.getX(), e.getY());
        if (mouseButton == MouseEvent.BUTTON3) {
            if (Preference.getIsRightEraser()) {
                switch (data.currentPsType) {
                    case AppData.PS_PEN:
                    case AppData.PS_ERASER:
                        if (data.getCurrentLayer() instanceof TileLayer) {
                            paintEdit.setPresentationName("擦除");
                            ((TileLayer) data.getCurrentLayer()).setTileAt(tile.x, tile.y, null);
                            repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                        } else if (data.getCurrentLayer() instanceof CollideLayer) {
                            paintEdit.setPresentationName("擦除");
                            ((CollideLayer) data.getCurrentLayer()).setCollideAt(tile.x, tile.y, false);
                            repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                        } else if (data.getCurrentLayer() instanceof SpriteLayer) {
                            ((SpriteLayer) data.getCurrentLayer()).setNpcAt(tile.x, tile.y, null);
                            repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                        }
                        break;
                }
            }

        } else if (mouseButton == MouseEvent.BUTTON1) {
            switch (data.currentPsType) {
                case AppData.PS_PEN:
                    if (currentBrush.isPaintingStarted()) {
                        paintEdit.setPresentationName("绘制");
                        try {
                            repaintRegion(
                                    currentBrush.doPaint(tile.x, tile.y));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                case AppData.PS_ERASER:
                    if (data.getCurrentLayer() instanceof TileLayer) {
                        paintEdit.setPresentationName("擦除");
                        ((TileLayer) data.getCurrentLayer()).setTileAt(tile.x, tile.y, null);
                        repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                    } else if (data.getCurrentLayer() instanceof CollideLayer) {
                        paintEdit.setPresentationName("擦除");
                        ((CollideLayer) data.getCurrentLayer()).setCollideAt(tile.x, tile.y, false);
                        repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                    } else if (data.getCurrentLayer() instanceof SpriteLayer) {
                        ((SpriteLayer) data.getCurrentLayer()).setNpcAt(tile.x, tile.y, null);
                        repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                    }
                    break;
                case AppData.PS_CHOOSE:
                    break;
                case AppData.PS_FILL:

//                    paintEdit = null;
                    if (data.getCurrentLayer() instanceof TileLayer) {
                        paintEdit.setPresentationName("填充");
                        TileLayer tileLayer = (TileLayer) data.getCurrentLayer();
                        Tile oldTile = tileLayer.getTileAt(tile.x, tile.y);
//                        System.out.println("x:" + tile.x);
//                        System.out.println("y:" + tile.y);
//                        ((TileLayer) tileLayer).setSync(true);
                        pour(tileLayer, tile.x, tile.y, data.getCurrentTile(), oldTile);
//                        ((TileLayer) tileLayer).setSync(false);
                        repaint();
                    }
                    break;
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
    //扫描边种子填充算法

    private void pour(TileLayer layer, int x, int y, Tile newTile, Tile oldTile) {
        if (newTile == oldTile || !layer.isVisible()) {
            return;
        }
//        System.out.println("pour");
        Stack<Point> stack = new Stack<Point>();

        stack.push(new Point(x, y));
        while (!stack.empty()) {
            // Remove the next tile from the stack
            Point p = stack.pop();
            int px = p.x;
            int py = p.y;
            int savex = px;/* 保存种子坐标x分量的值 */

            //妙！！
            // If the tile it meets the requirements, set it and push its
            // neighbouring tiles on the stack.
//            if (layer.contains(p.x, p.y) && layer.getTileAt(p.x, p.y) == oldTile) {
//                layer.setTileAt(p.x, p.y, newTile);
////                area.add(p);
//
//                stack.push(new Point(p.x, p.y - 1));
//                stack.push(new Point(p.x, p.y + 1));
//                stack.push(new Point(p.x + 1, p.y));
//                stack.push(new Point(p.x - 1, p.y));
//            }
            int xleft = 0, xright = 0;
            for (; layer.contains(px, py) && layer.getTileAt(px, py) == oldTile; px++) {
                layer.setTileAt(px, py, newTile);
            }
            xright = px - 1;/* 得到种子区段的右端点 */
            px = savex - 1;/* 准备向种子左侧填充 */
            for (; layer.contains(px, py) && layer.getTileAt(px, py) == oldTile; px--) {
                layer.setTileAt(px, py, newTile);
            }
            xleft = px + 1; /* 得到种子区段的左端点 */
            px = xleft;
            py = py - 1; /* 考虑种子相邻的上扫描线 */
            for (; px <= xright; px++) {
                if (layer.contains(px, py) && layer.getTileAt(px, py) == oldTile) {
                    stack.push(new Point(px, py));
                    break;
                }
            }
            px = xleft;
            for (; px <= xright; px++) {
                if (layer.contains(px, py) && layer.getTileAt(px - 1, py) != oldTile && layer.getTileAt(px, py) == oldTile) {
                    stack.push(new Point(px, py));
                }
            }
            px = xleft;
            py = py + 2;/* 检查相邻的下扫描线 */
            for (; px <= xright; px++) {
                if (layer.contains(px, py) && layer.getTileAt(px, py) == oldTile) {
                    stack.push(new Point(px, py));
                    break;
                }

            }
            px = xleft;
            for (; px <= xright; px++) {
                if (layer.contains(px, py) && layer.getTileAt(px - 1, py) != oldTile && layer.getTileAt(px, py) == oldTile) {
                    stack.push(new Point(px, py));
                }
            }
        }
//        System.out.println("size:" + stack.size());
    }

    private void updateCursorHighlight(Point tile) {

        Rectangle redraw = cursorSelectionLayer.getBounds();
        Rectangle brushRedraw = currentBrush.getBounds();

        brushRedraw.x = tile.x - brushRedraw.width / 2;
        brushRedraw.y = tile.y - brushRedraw.height / 2;

        if (!redraw.equals(brushRedraw)) {
            if (currentBrush instanceof CustomBrush) {
                CustomBrush customBrush = (CustomBrush) currentBrush;
                ListIterator<Layer> layers = customBrush.getListLayers();
                while (layers.hasNext()) {
                    Layer layer = (Layer) layers.next();
                    layer.setOffset(brushRedraw.x, brushRedraw.y);
//                    if (layer instanceof TileLayer) {
//                    }
                }
            }
            repaintRegion(redraw);
            cursorSelectionLayer.setOffset(brushRedraw.x, brushRedraw.y);
//            cursorHighlight.selectRegion(currentBrush.getShape());
            repaintRegion(brushRedraw);
        }
    }
}

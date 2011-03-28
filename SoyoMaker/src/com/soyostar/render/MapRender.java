/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.render;

import com.soyostar.brush.CustomBrush;
import com.soyostar.brush.IBrush;
import com.soyostar.data.GlobalData;
import com.soyostar.dialog.EventManagerDialog;
import com.soyostar.model.map.CollideLayer;
import com.soyostar.model.map.Layer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.TileLayer;
import com.soyostar.model.map.SelectionLayer;
import com.soyostar.model.map.SpriteLayer;
import com.soyostar.model.map.Tile;
import com.soyostar.undoredo.LayerEdit;
import com.soyostar.undoredo.UndoRedoHandler;
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
public abstract class MapRender extends JPanel implements MouseListener, MouseMotionListener, Scrollable {

    public static final int ZOOM_NORMALSIZE = 5;
    protected double zoom = 1.0;                    //正常缩放级别为1
    protected int zoomLevel = ZOOM_NORMALSIZE;      //初始话为正常缩放级别
    protected Map map;                              //所属地图
    protected boolean showGrid = false;              //是否显示网格
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0x3B3B3B);       //背景色
    public static final Color DEFAULT_GRID_COLOR = new Color(0, 0, 0);              //网格颜色
    protected IBrush currentBrush;
    /**
     *
     */
    protected static double[] zoomLevels = {
        0.0625, 0.125, 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 3.0, 4.0
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
        cursorSelectionLayer.setIsVisible(true);
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
    }

    /**
     * Sets a new brush. The brush can draw a preview of the change while
     * editing.
     * @param brush the new brush
     */
    public void setBrush(IBrush brush) {
        currentBrush = brush;
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
        paintMap(map, g2d);
        if (showGrid) {
            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.setStroke(new BasicStroke());
            g2d.setColor(DEFAULT_GRID_COLOR);
            paintGrid(g2d);
        }
    }
    private GlobalData data = GlobalData.getInstance();
    private SelectionLayer cursorSelectionLayer = new SelectionLayer(1, 1); //当前选框

    /**
     *
     * @param g2d
     */
    public SelectionLayer getCursorSelectionLayer() {
        return cursorSelectionLayer;
    }

    public void paintSelection(Graphics2D g2d) {
        g2d.setColor(new Color(128, 128, 255));
        g2d.setComposite(AlphaComposite.getInstance(
            AlphaComposite.SRC_ATOP, 0.5f));
        paintTileLayer(g2d, cursorSelectionLayer);
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
                if (layer.isIsVisible()) {
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
     * Draws an ObjectGroup. Implemented in a subclass.
     *
     * @param g2d   the graphics context to draw the object group onto
     * @param og    the ObjectGroup to be drawn
     */
    /**
     * Draws a TileLayer. Implemented in a subclass.
     *
     * @param g2d   the graphics context to draw the layer onto
     * @param layer the TileLayer to be drawn
     */
    protected abstract void paintTileLayer(Graphics2D g2d, TileLayer layer);//画图层

    protected abstract void paintCollideLayer(Graphics2D g2d, CollideLayer layer);//画碰撞层

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
            if (data.getCurrentLayer() instanceof SpriteLayer) {
                if (e.getClickCount() >= 2) {
                    EventManagerDialog emd = new EventManagerDialog();
                    emd.setVisible(true);
                }
            }

            switch (data.currentPsType) {
                case GlobalData.PS_PEN:
                    if (data.getCurrentLayer() instanceof TileLayer) {
                        data.getBrush().startPaint(data.getCurrentMap(), tile.x, tile.y,
                            e.getButton(), data.getCurrentMap().indexOfLayer(data.getCurrentLayer()));
                    }
                    if (data.getCurrentLayer() instanceof CollideLayer) {
                        data.getBrush().startPaint(data.getCurrentMap(), tile.x, tile.y,
                            e.getButton(), data.getCurrentMap().indexOfLayer(data.getCurrentLayer()));
//                        ((CollideLayer) data.getCurrentLayer()).setCollideAt(tile.x, tile.y, true);
                    }
                    if (data.getCurrentLayer() instanceof SpriteLayer) {
                    }
                case GlobalData.PS_ERASER:
                case GlobalData.PS_FILL:
                    paintEdit =
                        new LayerEdit(data.getCurrentLayer(), createLayerCopy(data.getCurrentLayer()), null);
                    break;
                case GlobalData.PS_CHOOSE:
                    break;
//                case GlobalData.PS_COLIIDE:
//                    break;
//                case GlobalData.PS_EVENT:
//                    break;

            }
        }
        mouse(e);

    }

    private static Layer createLayerCopy(Layer layer) {
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
            case GlobalData.PS_PEN:
                if (data.getCurrentLayer() instanceof TileLayer) {
                    data.getBrush().endPaint();
                }
                if (data.getCurrentLayer() instanceof CollideLayer) {
                    data.getBrush().endPaint();
                }
                if (data.getCurrentLayer() instanceof SpriteLayer) {
                }
                break;
            case GlobalData.PS_ERASER:
                break;
            case GlobalData.PS_CHOOSE:
                break;
            case GlobalData.PS_FILL:
                break;
//            case GlobalData.PS_COLIIDE:
//                break;
//            case GlobalData.PS_EVENT:
//                break;

        }
        repaint();

        if (paintEdit != null) {
            if (data.getCurrentLayer() != null) {
                try {
                    Layer endLayer = paintEdit.getStart().createDiff(data.getCurrentLayer());
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
        updateCursorHighlight(tile);
    }
    private int mouseButton;

    public void mouse(MouseEvent e) {
        Point tile = screenToTileCoords(e.getX(), e.getY());
        if (mouseButton == MouseEvent.BUTTON1) {
            switch (data.currentPsType) {
                case GlobalData.PS_PEN:
                    paintEdit.setPresentationName("绘制");
                    if (data.getBrush().isPaintingStarted()) {
                        try {
                            repaintRegion(
                                data.getBrush().doPaint(tile.x, tile.y));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
//                    if (data.getCurrentLayer() instanceof CollideLayer) {
//                        ((CollideLayer) data.getCurrentLayer()).setCollideAt(tile.x, tile.y, true);
////                        try {
//                        repaintRegion(new Rectangle((int) getTileSize().getWidth(), (int) getTileSize().getHeight()));
////                        } catch (Exception ex) {
////                            ex.printStackTrace();
////                        }
//                    }
                    break;
                case GlobalData.PS_ERASER:
                    paintEdit.setPresentationName("擦除");
                    if (data.getCurrentLayer() instanceof TileLayer) {
                        ((TileLayer) data.getCurrentLayer()).setTileAt(tile.x, tile.y, null);
                        repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                    }
                    if (data.getCurrentLayer() instanceof CollideLayer) {
                        ((CollideLayer) data.getCurrentLayer()).setCollideAt(tile.x, tile.y, false);
                        repaintRegion(new Rectangle(tile.x, tile.y, 1, 1));
                    }
                    break;
                case GlobalData.PS_CHOOSE:
                    break;
                case GlobalData.PS_FILL:
//                    paintEdit.setPresentationName("填充");
                    paintEdit = null;
                    if (data.getCurrentLayer() instanceof TileLayer) {
                        TileLayer tileLayer = (TileLayer) data.getCurrentLayer();
                        Tile oldTile = tileLayer.getTileAt(tile.x, tile.y);
//                        System.out.println("x:" + tile.x);
//                        System.out.println("y:" + tile.y);
                        pour(tileLayer, tile.x, tile.y, data.getCurrentTile(), oldTile);
                        repaint();
                    }
                    break;
//                case GlobalData.PS_COLIIDE:
//                    break;
//                case GlobalData.PS_EVENT:
//                    break;

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
        if (newTile == oldTile || !layer.isIsVisible()) {
            return;
        }

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
                    Layer layer = layers.next();
                    layer.setOffset(brushRedraw.x, brushRedraw.y);
                }
            }
            repaintRegion(redraw);
            cursorSelectionLayer.setOffset(brushRedraw.x, brushRedraw.y);
            //cursorHighlight.selectRegion(currentBrush.getShape());
            repaintRegion(brushRedraw);
        }
    }
}

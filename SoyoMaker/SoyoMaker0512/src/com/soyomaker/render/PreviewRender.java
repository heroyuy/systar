/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.render;

import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.TileLayer;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author Administrator
 */
public abstract class PreviewRender extends JPanel implements Scrollable, MouseListener, MouseMotionListener {

    /**
     * 
     */
    protected double zoom = 1.0;                        //正常缩放级别为1
    /**
     * 
     */
    protected Map map;
    /**
     *
     */
    protected boolean showPressPoint = false;
    /**
     * 
     */
    protected boolean showCollide = false;
    private BufferedImage image;
    private Rectangle bound = new Rectangle(480, 800);  //虚拟屏幕框大小，默认为480*800
    /**
     * 
     */
    public static final Color DEFAULT_GRID_COLOR = new Color(0, 0, 0);              //网格颜色
    /**
     * 
     */
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0x3B3B3B);       //背景色
    /**
     *
     */
    protected static double[] zoomLevels = {
        0.0625, 0.125, 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 3.0, 4.0
    };
    /**
     * 
     */
    public static final int ZOOM_NORMALSIZE = 5;
    /**
     * 
     */
    protected int zoomLevel = ZOOM_NORMALSIZE;      //初始话为正常缩放级别
    /**
     *
     */
    protected ArrayList<RenderListener> listeners = new ArrayList<RenderListener>();
    /**
     *
     */
    protected Point pressPoint = null;

    /**
     * 
     * @param map
     */
    public PreviewRender(Map map) {
        setMap(map);
        image = new BufferedImage(map.getTileWidth() * map.getWidth(),
                map.getTileHeight() * map.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    /**
     *
     * @return
     */
    public Point getPressPoint() {
        return pressPoint;
    }

    /**
     * 设置当前按下的点
     * @param pressPoint
     */
    public void setPressPoint(Point pressPoint) {
        this.pressPoint = pressPoint;
    }

    /**
     *
     * @param listener
     */
    public void addRenderListener(RenderListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     *
     * @param listener
     */
    public void removeRenderListener(RenderListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
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

    /**
     * 
     * @param map
     */
    public void setMap(Map map) {
        map.setPreviewRender(this);
        this.map = map;
    }

    /**
     * 
     * @param w
     * @param h
     */
    public void setBound(int w, int h) {
        bound.width = w;
        bound.height = h;
        repaint();
    }

    /**
     * 
     * @return
     */
    public BufferedImage getBufferedImage() {
        return image;
    }

    /**
     * 
     * @return
     */
    public boolean isShowPressPoint() {
        return showPressPoint;
    }

    /**
     *
     * @param showPressPoint
     */
    public void setShowPressPoint(boolean showPressPoint) {
        this.showPressPoint = showPressPoint;
    }

    /**
     * 
     * @return
     */
    public boolean isShowCollide() {
        return showCollide;
    }

    /**
     * 
     * @param showCollide
     */
    public void setShowCollide(boolean showCollide) {
        this.showCollide = showCollide;
    }

    /**
     * 
     */
    protected boolean showSprite = true;

    /**
     * 
     * @return
     */
    public boolean isShowSprite() {
        return showSprite;
    }

    /**
     * 
     * @param showSprite
     */
    public void setShowSprite(boolean showSprite) {
        this.showSprite = showSprite;
    }
    /**
     *
     */
    protected boolean showTile = true;

    /**
     *
     * @return
     */
    public boolean isShowTile() {
        return showTile;
    }

    /**
     *
     * @param showTile
     */
    public void setShowTile(boolean showTile) {
        this.showTile = showTile;
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
    public abstract Dimension getPreferredSize();

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(DEFAULT_BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = image.createGraphics();
        paintMap(g2d);
        g.drawImage(image, 0, 0, null);
        if (showPressPoint && pressPoint != null) {
            g.drawRect((int) (pressPoint.x * getTileSize().getWidth()),
                    (int) (pressPoint.y * getTileSize().getHeight()),
                    (int) getTileSize().getWidth(),
                    (int) getTileSize().getHeight());
        }
    }

    /**
     * 
     * @param g2d
     */
    protected void paintMap(Graphics2D g2d) {
        Iterator li = map.getLayers();
        Layer layer;
        while (li.hasNext()) {
            layer = (Layer) li.next();
            if (layer != null) {
                g2d.setComposite(AlphaComposite.SrcOver);
                if (layer instanceof TileLayer) {
                    paintTileLayer(g2d, (TileLayer) layer);
                } else if (layer instanceof CollideLayer) {
                    if (showCollide) {
                        paintCollideLayer(g2d, (CollideLayer) layer);
                    }
                } else if (layer instanceof SpriteLayer) {
                    if (showSprite) {
                        paintSpriteLayer(g2d, (SpriteLayer) layer);
                    }
                }
            }
        }
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

    public void mouseDragged(MouseEvent e) {
        bound.x = (e.getX() - bound.width / 2);
        bound.y = (e.getY() - bound.height / 2);
        repaint();
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     * @param e 
     */
    public void mouseMoved(MouseEvent e) {
        Point tile = screenToTileCoords(e.getX(), e.getY());
        if (e.getX() / map.getTileWidth() < map.getWidth() && e.getY() / map.getTileHeight() < map.getHeight()) {
            for (int i = 0; i < listeners.size(); i++) {
                listeners.get(i).focusPointChanged(tile);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Point tile = screenToTileCoords(e.getX(), e.getY());
        if (e.getX() / map.getTileWidth() < map.getWidth() && e.getY() / map.getTileHeight() < map.getHeight()) {
            pressPoint = tile;
            for (int i = 0; i < listeners.size(); i++) {
                listeners.get(i).pressPointChanged(tile);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 16;
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 16;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}

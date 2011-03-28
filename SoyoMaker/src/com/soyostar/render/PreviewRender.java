/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.render;

import com.soyostar.model.map.Map;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public abstract class PreviewRender extends JPanel {

    protected Map map;                              //所属地图
    protected double zoom = 0.25;                   //默认为四分之一缩放

    /**
     *
     * @param map
     */
    protected PreviewRender(Map map) {
        this();
        setMap(map);
    }

    /**
     *
     */
    protected PreviewRender() {

        this.setOpaque(true);
    }

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

    @Override
    public abstract Dimension getPreferredSize();

//    @Override
//    public Dimension getSize() {
//        return new Dimension(100, 100);
//    }
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
        if (map != null) {
            map.setPreviewRender(this);
        }

    }
}

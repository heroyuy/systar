/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import com.soyostar.pluginimpl.sprite.listener.FrameChangeListener;
import com.soyostar.pluginimpl.sprite.listener.FrameChangedEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Frame {

    private String name = "新建帧";
    private ArrayList<Layer> layers;
    private Rectangle bound = new Rectangle(0, 0, 0, 0);
    protected final List<FrameChangeListener> frameChangeListeners = new LinkedList();


    public Frame() {
        layers = new ArrayList<Layer>();
    }


    public void addFrameChangeListener(FrameChangeListener listener) {
        frameChangeListeners.add(listener);
//        System.out.println("ls:" + layerChangeListeners.size());
    }

    public void removeFrameChangeListener(FrameChangeListener listener) {
        frameChangeListeners.remove(listener);
    }

    protected void fireFrameChanged() {
        FrameChangedEvent event = new FrameChangedEvent(this);
        for (FrameChangeListener listener : frameChangeListeners) {
            listener.frameChanged(event);
        }
    }

    protected void fireNameChanged(String oldName, String newName) {
        FrameChangedEvent event = new FrameChangedEvent(this);
        for (FrameChangeListener listener : frameChangeListeners) {
            listener.nameChanged(event, oldName, newName);
        }
    }

    protected void fireAddLayerChanged(Layer layer) {
        FrameChangedEvent event = new FrameChangedEvent(this);

        for (FrameChangeListener listener : frameChangeListeners) {
            listener.layerAdded(event, layer);
        }
    }

    protected void fireRemoveLayerChanged(int index) {
        FrameChangedEvent event = new FrameChangedEvent(this);

        for (FrameChangeListener listener : frameChangeListeners) {
            listener.layerRemoved(event, index);
        }
    }

    /**
     *
     * @return
     */
    public Iterator getLayers() {
        return layers.iterator();
    }

    public int getLayersCount() {
        return layers.size();
    }

    public void addLayer() {
        Layer layer = new ModuleLayer();
        layers.add(layer);
        fireAddLayerChanged(layer);
    }

    public void addLayer(Layer layer) {
        if (layer != null) {
            layers.add(layer);
            fireAddLayerChanged(layer);
        } else {
            System.out.println("无效的Layer");
        }
    }

    public Layer getLayer(int id) {
        if (id < 0 || id >= layers.size()) {
            return null;
        }
        return layers.get(id);
    }

    public void removeLayer(int index) {
        if (index != -1) {
            layers.remove(index);
            fireRemoveLayerChanged(index);
        } else {
            System.out.println("无效的Layer");
        }
    }

    public void removeLayer(Layer layer) {
        int index = layers.indexOf(layer);
        if (index != -1) {
            layers.remove(index);
            fireRemoveLayerChanged(index);
        } else {
            System.out.println("无效的Layer");
        }
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
        fireFrameChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String na = this.name;
        this.name = name;
        fireNameChanged(na, name);
    }

    /**
     * Moves the layer at <code>index</code> up one in the vector.
     *
     * @param index the index of the layer to swap up
     */
    public void swapLayerDown(int index) {
        if (index + 1 == layers.size()) {
            throw new RuntimeException(
                "Can't swap up when already at the top.");
        }
        Layer hold = layers.get(index + 1);
        layers.set(index + 1, getLayer(index));
        layers.set(index, hold);
        fireFrameChanged();
    }

    /**
     * Moves the layer at <code>index</code> down one in the vector.
     *
     * @param index the index of the layer to swap down
     */
    public void swapLayerUp(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                "Can't swap down when already at the bottom.");
        }
        Layer hold = layers.get(index - 1);
        layers.set(index - 1, getLayer(index));
        layers.set(index, hold);
        fireFrameChanged();
    }

    /**
     *
     */
    public void updateBounds() {
        this.bound.setBounds(0, 0, 0, 0);
        if (layers.isEmpty()) {
        } else if (layers.size() == 1) {
            Layer t = layers.get(0);
            Rectangle tb = t.getBound();
            this.bound.setBounds(tb);
        } else {
            Iterator li = this.layers.iterator();
            Layer layer = null;
            while (li.hasNext()) {
                layer = (Layer) li.next();
                if (layer instanceof ModuleLayer) {
                    Rectangle tb = ((ModuleLayer) layer).getBound();
                    this.bound.add(tb);
                }
            }
        }

        System.out.println("frame:" + bound.width + "x" + bound.height);
    }

    public void paintPreview(Graphics g, int x, int y, int size) {
        int maxSize = Math.max(this.bound.width, this.bound.height);
        if (maxSize <= 0) {
            return;
        }
        BufferedImage image = new BufferedImage(maxSize, maxSize, BufferedImage.TYPE_INT_ARGB);
        int refX = 0;
        int refY = 0;
        if (this.bound.width != this.bound.height) {
            if (this.bound.width > this.bound.height) {
                refY = (this.bound.width - this.bound.height) >> 1;
            } else {
                refX = (this.bound.height - this.bound.width) >> 1;
            }
        }

        Graphics bg = image.getGraphics();
        Iterator li = this.layers.iterator();
        Layer layer = null;
        while (li.hasNext()) {
            layer = (Layer) li.next();
            if (layer instanceof ModuleLayer) {
                ((ModuleLayer) layer).paintPreview(bg, refX - this.bound.x, refY - this.bound.y, size);
            }
        }
        g.drawImage(image.getScaledInstance(size, size, BufferedImage.SCALE_DEFAULT), x, y, null);

    }
//    private BufferedImage frameImg = new BufferedImage(bound.width, bound.height, BufferedImage.TYPE_INT_ARGB);

    /**
     *
     * @param g
     * @param x
     * @param y
     * @param zoom
     */
    public void paint(Graphics g, int x, int y, int zoom) {
        Iterator li = this.layers.iterator();
        Layer layer = null;
        while (li.hasNext()) {
            layer = (Layer) li.next();
            if (layer instanceof ModuleLayer) {
                if (layer.isVisible()) {
                    Iterator ti = ((ModuleLayer) layer).getModules();
                    Module tile = null;
                    while (ti.hasNext()) {
                        tile = (Module) ti.next();
                        tile.paint(g, 0, x, y, zoom);//暂不支持翻转
                    }
                }

            }
        }
    }
}

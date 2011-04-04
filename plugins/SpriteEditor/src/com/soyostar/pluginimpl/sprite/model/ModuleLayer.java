/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class ModuleLayer extends Layer {

    ArrayList<Module> modules;

    public ModuleLayer() {
        modules = new ArrayList<Module>();
    }

    /**
     *
     */
    public void updateBounds() {
        this.bound.setBounds(0, 0, 0, 0);
        if (modules.isEmpty()) {
        } else if (modules.size() == 1) {
            Module t = modules.get(0);
            Rectangle tb = t.getBound();
            this.bound.setBounds(tb);
        } else {
            Iterator ti = modules.iterator();
            Module tile = null;
            while (ti.hasNext()) {
                tile = (Module) ti.next();
                Rectangle tb = tile.getBound();
                this.bound.add(tb);
            }
        }

//        System.out.println("layer:" + bound.width + "x" + bound.height);

    }

    /**
     *
     * @return
     */
    public Iterator getModules() {
        return modules.iterator();
    }

    public int indexOf(Module Module) {
        return modules.indexOf(Module);
    }

    public int getModuleCounts() {
        return modules.size();
    }

    public Module getModule(int id) {
        if (id < 0 || id >= modules.size()) {
            return null;
        }
        return modules.get(id);
    }

    public void addModule(Module Module) {
        if (Module != null) {
            modules.add(Module);
            updateBounds();
            fireLayerChanged();
        } else {
            System.out.println("无效的Module");
        }
    }

    public void removeModule(Module Module) {
        if (Module != null) {
            modules.remove(Module);
            updateBounds();
            fireLayerChanged();
        } else {
            System.out.println("无效的Module");
        }
    }

    /**
     *
     * @param g
     * @param x
     * @param y
     * @param size
     */
    public void paintPreview(Graphics g, int x, int y, int size) {
        Iterator ti = modules.iterator();
        Module tile = null;
        while (ti.hasNext()) {
            tile = (Module) ti.next();
            tile.paint(g, 0, x, y, 1);//暂不支持翻转
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

/**
 *
 * @author Administrator
 */
public class LList extends Layer {

    private Adapter adapter = null;

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public LList(Adapter adapter) {
        this.adapter = adapter;
    }
}

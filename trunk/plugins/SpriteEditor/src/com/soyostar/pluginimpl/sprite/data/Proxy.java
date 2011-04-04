/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.data;

import com.soyostar.pluginimpl.sprite.SpriteEditorFrame;

/**
 *
 * @author Administrator
 */
public class Proxy {

    private Proxy() {
    }
    private static Proxy data;

    public static Proxy getInstance() {
        if (data == null) {
            data = new Proxy();
        }
        return data;
    }
    private SpriteEditorFrame smd;

    public SpriteEditorFrame getMainDialog() {
        return smd;
    }

    public void setMainDialog(SpriteEditorFrame smd) {
        this.smd = smd;
    }
    private byte OpType = 0;

    public byte getOpType() {
        return OpType;
    }

    public void setOpType(byte OpType) {
        this.OpType = OpType;
    }
    public static final byte PEN = 0;
    public static final byte ERASER = 1;
}

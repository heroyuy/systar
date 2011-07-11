/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.animation;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Action {

    private byte type;//特效类型

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
    private ArrayList<String> para;//参数列表

    public ArrayList<String> getPara() {
        return para;
    }

    public void setPara(ArrayList<String> para) {
        this.para = para;
    }
}

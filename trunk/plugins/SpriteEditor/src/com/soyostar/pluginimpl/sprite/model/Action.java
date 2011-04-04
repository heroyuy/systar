/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class Action {

    private ArrayList<Sequence> sequences;
    private String name = "新建动作";

    public Action() {
        sequences = new ArrayList<Sequence>();
    }

    /**
     *
     * @return
     */
    public Iterator getSequences() {
        return sequences.iterator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

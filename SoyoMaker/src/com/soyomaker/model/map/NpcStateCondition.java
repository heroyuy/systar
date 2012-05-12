/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

/**
 *
 * @author Administrator
 */
public class NpcStateCondition implements Cloneable {

    /**
     *
     */
    public static final int SWITCH = 0;
    /**
     *
     */
    public static final int VAR = 1;
    /**
     *
     */
    public int conditionType = -1;
    /**
     *
     */
    public int[] paramList;

    @Override
    public NpcStateCondition clone() throws CloneNotSupportedException {
        NpcStateCondition clone = (NpcStateCondition) super.clone();
        clone.paramList = paramList.clone();
        return clone;
    }
}

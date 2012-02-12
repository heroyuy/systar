/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Model implements Serializable {

    /**
     *
     */
    public static final int CONFIG = 0;
    /**
     *
     */
    public static final int STATUS = 1;
    /**
     *
     */
    public static final int SKILL = 2;
    /**
     *
     */
    public static final int ITEM = 3;
    /**
     *
     */
    public static final int EQUIP = 4;
    /**
     *
     */
    public static final int VOCATION = 5;
    /**
     *
     */
    public static final int PLAYER = 6;
    /**
     *
     */
    public static final int ENEMY = 7;
    /**
     *
     */
    public static final int ENEMYTROOP = 8;
    private int index = -1;

    /**
     *
     * @return
     */
    public final int getIndex() {
        return index;
    }

    /**
     *
     * @param index
     */
    public final void setIndex(int index) {
        if (this.index != -1) {
            try {
                throw new Exception("不能修改对象索引");
            } catch (Exception ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.index = index;
        }
    }
}

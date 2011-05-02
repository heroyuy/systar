package com.soyostar.data.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Model implements Serializable {

    public static final int CONFIG = 0;
    public static final int PLAYER = 1;
    public static final int ENEMY = 2;
    public static final int ENEMYTROOP = 3;
    public static final int EQUIP = 4;
    public static final int ITEM = 5;
    public static final int SKILL = 6;
    public static final int MAP = 7;
    public static final int ANIMATION = 8;
    private int index = -1;

    public final int getIndex() {
        return index;
    }

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

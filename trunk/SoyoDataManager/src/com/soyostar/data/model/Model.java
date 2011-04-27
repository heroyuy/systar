package com.soyostar.data.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Model implements Serializable {

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

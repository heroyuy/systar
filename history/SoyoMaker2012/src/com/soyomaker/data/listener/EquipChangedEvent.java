/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.Equip;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class EquipChangedEvent  extends EventObject {

    /**
     *
     * @param obj
     */
    public EquipChangedEvent(Equip obj) {
        super(obj);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.Item;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class ItemChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public ItemChangedEvent(Item obj) {
        super(obj);
    }
}

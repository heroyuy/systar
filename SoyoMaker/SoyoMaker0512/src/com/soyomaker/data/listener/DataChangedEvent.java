/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.listener;

import com.soyomaker.data.DataManager;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class DataChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public DataChangedEvent(DataManager obj) {
        super(obj);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.listener;

import com.soyomaker.data.model.Vocation;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class VocationChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public VocationChangedEvent(Vocation obj) {
        super(obj);
    }
}

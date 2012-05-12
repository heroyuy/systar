/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.Status;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class StatusChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public StatusChangedEvent(Status obj) {
        super(obj);
    }
}

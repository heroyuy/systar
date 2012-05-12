/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.Player;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class PlayerChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public PlayerChangedEvent(Player obj) {
        super(obj);
    }
}

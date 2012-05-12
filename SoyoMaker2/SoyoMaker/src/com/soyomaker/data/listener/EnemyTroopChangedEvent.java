/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.EnemyTroop;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class EnemyTroopChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public EnemyTroopChangedEvent(EnemyTroop obj) {
        super(obj);
    }
}

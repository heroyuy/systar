/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.Enemy;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class EnemyChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public EnemyChangedEvent(Enemy obj) {
        super(obj);
    }
}

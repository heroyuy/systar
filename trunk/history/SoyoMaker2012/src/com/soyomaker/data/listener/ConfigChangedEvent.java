/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.listener;

import com.soyomaker.data.model.Config;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class ConfigChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public ConfigChangedEvent(Config obj) {
        super(obj);
    }
}

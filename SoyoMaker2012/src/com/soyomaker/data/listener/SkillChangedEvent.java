/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyomaker.data.listener;

import com.soyomaker.data.model.Skill;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class SkillChangedEvent extends EventObject {

    /**
     *
     * @param obj
     */
    public SkillChangedEvent(Skill obj) {
        super(obj);
    }
}

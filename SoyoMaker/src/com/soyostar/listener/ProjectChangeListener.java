/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

import com.soyostar.model.animation.Animation;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.Npc;
import com.soyostar.model.map.ScriptFile;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface ProjectChangeListener extends EventListener {

    /**
     * 
     * @param e
     */
    public void projectChanged(ProjectChangedEvent e);

    public void animationAdded(ProjectChangedEvent e, Animation ani);

    /**
     * 
     * @param e
     * @param map
     */
    public void mapAdded(ProjectChangedEvent e, Map map);

    /**
     * 
     * @param e
     * @param npc
     */
    public void npcAdded(ProjectChangedEvent e, Npc npc);

    /**
     * 
     * @param e
     * @param npc
     */
    public void scriptAdded(ProjectChangedEvent e, ScriptFile npc);

    public void animationRemoved(ProjectChangedEvent e, int index);

    /**
     * 
     * @param e
     * @param index
     */
    public void mapRemoved(ProjectChangedEvent e, int index);

    /**
     * 
     * @param e
     * @param index
     */
    public void npcRemoved(ProjectChangedEvent e, int index);

    /**
     * 
     * @param e
     * @param index
     */
    public void scriptRemoved(ProjectChangedEvent e, int index);
}

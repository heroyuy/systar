/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.listener;

import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Picture;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.ScriptFile;
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

    /**
     *
     * @param e
     * @param ani
     */
    public void animationAdded(ProjectChangedEvent e, Animation ani);

    /**
     *
     * @param e
     * @param pic
     */
    public void pictureAdded(ProjectChangedEvent e, Picture pic);

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

    /**
     *
     * @param e
     * @param index
     */
    public void animationRemoved(ProjectChangedEvent e, int index);

    /**
     *
     * @param e
     * @param index
     */
    public void pictureRemoved(ProjectChangedEvent e, int index);

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

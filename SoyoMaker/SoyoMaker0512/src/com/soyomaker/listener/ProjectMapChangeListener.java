/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.listener;

import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.Script;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface ProjectMapChangeListener extends EventListener {

    /**
     *
     * @param e
     * @param map
     */
    public void mapAdded(ProjectMapChangedEvent e, Map map);

    /**
     *
     * @param e
     * @param npc
     */
    public void npcAdded(ProjectMapChangedEvent e, Npc npc);

    /**
     *
     * @param e
     * @param npc
     */
    public void scriptAdded(ProjectMapChangedEvent e, Script npc);

    /**
     *
     * @param e
     * @param index
     */
    public void mapRemoved(ProjectMapChangedEvent e, int index);

    /**
     *
     * @param e
     * @param index
     */
    public void npcRemoved(ProjectMapChangedEvent e, int index);

    /**
     *
     * @param e
     * @param index
     */
    public void scriptRemoved(ProjectMapChangedEvent e, int index);
}

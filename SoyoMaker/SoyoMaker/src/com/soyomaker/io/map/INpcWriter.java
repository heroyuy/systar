/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Npc;

/**
 *
 * @author Administrator
 */
public interface INpcWriter {
        /**
     * Saves a map to a file.
     *
     * @param npc 
     * @param filename the filename of the map file
     * @throws Exception
     */
    public void writeNpc(Npc npc, String filename) throws Exception;
}

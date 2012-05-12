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
public interface INpcReader {

    /**
     * 
     * @param mapFile
     * @return
     * @throws Exception
     */
    public Npc readNpc(String mapFile) throws Exception;
}

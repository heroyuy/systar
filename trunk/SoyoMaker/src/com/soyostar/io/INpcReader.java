/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.io;

import com.soyostar.model.map.Npc;

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

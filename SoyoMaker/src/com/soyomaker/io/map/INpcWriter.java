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
    //写在不同文件里

    /**
     *
     * @param npc
     * @param filename
     * @throws Exception
     */
    public void writeNpc(Npc npc, String filename) throws Exception;
    //写在一个文件里

    /**
     *
     * @param filename
     * @throws Exception
     */
    public void writeNpc(String filename) throws Exception;
}

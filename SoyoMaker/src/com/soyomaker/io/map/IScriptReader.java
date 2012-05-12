/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Script;

/**
 *
 * @author Administrator
 */
public interface IScriptReader {

    /**
     * 
     * @param filename
     * @return
     * @throws Exception
     */
    public Script readScript(String filename) throws Exception;
}

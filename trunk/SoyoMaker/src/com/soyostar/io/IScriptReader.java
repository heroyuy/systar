/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.io;

import com.soyostar.model.map.ScriptFile;

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
    public ScriptFile readScript(String filename) throws Exception;
}

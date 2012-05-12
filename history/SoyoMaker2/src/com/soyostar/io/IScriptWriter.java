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
public interface IScriptWriter {

    /**
     * 
     * @param file
     * @param filename
     * @throws Exception
     */
    public void compileScript(ScriptFile file,String filename) throws Exception;
}

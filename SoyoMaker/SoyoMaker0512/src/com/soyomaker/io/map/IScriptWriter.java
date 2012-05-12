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
public interface IScriptWriter {

    /**
     * 
     * @param file
     * @param filename
     * @throws Exception
     */
    public void writeScript(Script file,String filename) throws Exception;
}

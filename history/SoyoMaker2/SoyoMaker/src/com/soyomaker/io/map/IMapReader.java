/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Map;

/**
 *
 * @author Administrator
 */
public interface IMapReader {

    /**
     * 
     * @param mapFile
     * @return
     * @throws Exception
     */
    public Map readMap(String mapFile) throws Exception;
}

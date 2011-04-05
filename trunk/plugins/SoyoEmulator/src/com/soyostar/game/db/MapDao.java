/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Map;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class MapDao implements Dao {

    private HashMap maps = null;

    public Map getMap(int index) {
        return (Map) maps.get(index);
    }

    public Map[] getMapList() {
        return (Map[]) maps.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}

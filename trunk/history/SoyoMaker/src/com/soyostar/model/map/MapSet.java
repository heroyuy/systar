/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.model.map;

import com.soyostar.util.NumberedSet;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class MapSet {

    private ArrayList<Map> maps;

    public MapSet() {
        maps = new ArrayList<Map>();
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    /**
     *
     * @param map
     */
    public void addMap(Map map) {
        maps.add(map);
        map.setMapSet(this);
    }

    public int indexOf(Map map) {
        return maps.indexOf(map);
    }

    /**
     *
     * @param id
     */
    public void removeMap(int id) {
        maps.remove(id);
    }

    public void removeMap(Map map) {
        int index = maps.indexOf(map);
        maps.remove(index);
    }

    /**
     *
     * @return
     */
    public Iterator iterator() {
        return maps.iterator();
    }

    /**
     *
     * @return
     */
    public int size() {
        return maps.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Map getMap(int i) {
        return maps.get(i);
    }
}

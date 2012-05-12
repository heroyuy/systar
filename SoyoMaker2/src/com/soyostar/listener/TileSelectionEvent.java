/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.listener;

import com.soyostar.model.map.Tile;
import java.util.EventObject;

/**
 * An event that describes the selection of a tile.
 *
 * @version $Id$
 */
public class TileSelectionEvent extends EventObject
{
    private Tile tile;

    /**
     *
     * @param source
     * @param tile
     */
    public TileSelectionEvent(Object source, Tile tile) {
        super(source);
        this.tile = tile;
    }

    /**
     *
     * @return
     */
    public Tile getTile() {
        return tile;
    }
}
/*
 *  Tiled Map Editor, (c) 2004-2006
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  Adam Turk <aturk@biggeruniverse.com>
 *  Bjorn Lindeijer <bjorn@lindeijer.nl>
 */
package com.soyomaker.listener;

import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.TileSet;
import java.util.EventListener;

/**
 * Gets notified about changes made to a map. This includes events relating to
 * changes in the list of layers or tilesets used by this map.
 *
 * @version $Id$
 */
public interface MapChangeListener extends EventListener {

    /**
     * 
     * @param e
     */
    public void mapChanged(MapChangedEvent e);

    /**
     * 
     * @param e
     * @param layer
     */
    public void layerAdded(MapChangedEvent e, Layer layer);

    /**
     * 
     * @param e
     * @param index
     */
    public void layerRemoved(MapChangedEvent e, int index);

    /**
     * 
     * @param e
     * @param tileset
     */
    public void tilesetAdded(MapChangedEvent e, TileSet tileset);

    /**
     * 
     * @param e
     * @param index
     */
    public void tilesetRemoved(MapChangedEvent e, int index);
}

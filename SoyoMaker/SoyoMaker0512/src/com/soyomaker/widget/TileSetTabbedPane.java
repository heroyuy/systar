/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.widget;

import com.soyomaker.brush.TileLayerBrush;
import com.soyomaker.AppData;
import com.soyomaker.brush.TileBrush;
import com.soyomaker.listener.MapChangeListener;
import com.soyomaker.listener.MapChangedEvent;
import com.soyomaker.listener.TileRegionSelectionEvent;
import com.soyomaker.listener.TileSelectionEvent;
import com.soyomaker.listener.TileSelectionListener;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Tile;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.model.map.TileSet;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import javax.swing.JScrollPane;

/**TileSetPaletteView
 *
 * @author Administrator
 */
public class TileSetTabbedPane extends JSnapTipTabbedPane implements TileSelectionListener {

    private final HashMap<TileSet, TileSetPalettePanel> tilePanels =
            new HashMap<TileSet, TileSetPalettePanel>();                        //每个图集对应一个面板
    private Map map;
    private final MapChangeListenerImpl listener = new MapChangeListenerImpl();

    /**
     * 
     */
    public TileSetTabbedPane() {
        super();
    }

    /**
     *
     * @param map
     */
    public void setMap(Map map) {
        if (this.map != null) {
            this.map.removeMapChangeListener(listener);
        }
        if (map == null) {
            removeAll();
        } else {
            recreateTabs(map.getTileSets());
            map.addMapChangeListener(listener);
        }
        this.map = map;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180, 250);
    }

    /**
     * Creates the panels for the tilesets.
     * @param tilesets the list of tilesets to create panels for
     */
    private void recreateTabs(List<TileSet> tilesets) {
        // Stop listening to the tile palette panels and their tilesets
        for (TileSetPalettePanel panel : tilePanels.values()) {
            panel.removeTileSelectionListener(this);
        }
        tilePanels.clear();

        // Remove all tabs
        removeAll();

        if (tilesets != null) {
            // Add a new tab for each tileset of the map
            for (TileSet tileset : tilesets) {
                if (tileset != null) {
                    addTabForTileset(tileset);
                }
            }
        }
    }

    /**
     * Adds a tab with a {@link TilePalettePanel} for the given tileset.
     *
     * @param tileset the given tileset
     */
    private void addTabForTileset(TileSet tileset) {
        TileSetPalettePanel tilePanel = new TileSetPalettePanel();
        tilePanel.setTileSet(tileset);
        tilePanel.addTileSelectionListener(this);
        tilePanel.setToolTipText(tileset.getName());
        JScrollPane paletteScrollPane = new JScrollPane(tilePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tilePanels.put(tileset, tilePanel);
        add(paletteScrollPane, "图" + tilePanels.size());
    }
    /**
     *
     */
    public AppData data = AppData.getInstance();

    public void tileSelected(TileSelectionEvent e) {
        Tile tile = e.getTile();
        TileBrush brush = new TileBrush(tile);
        brush.makeQuadBrush(new Rectangle(0, 0, 1, 1));
        data.setBrush(brush);
    }

    public void tileRegionSelected(TileRegionSelectionEvent e) {
        TileLayer layer = e.getTileRegion();
        TileLayerBrush layerBrush = new TileLayerBrush(layer);
        data.setBrush(layerBrush);
    }

    private class MapChangeListenerImpl implements MapChangeListener {

        public void mapChanged(MapChangedEvent e) {
        }

        public void tilesetAdded(MapChangedEvent e, TileSet tileset) {
            addTabForTileset(tileset);
        }

        public void tilesetRemoved(MapChangedEvent e, int index) {
            JScrollPane scroll = (JScrollPane) getComponentAt(index);
            TileSetPalettePanel panel = (TileSetPalettePanel) scroll.getViewport().getView();
            TileSet set = panel.getTileset();
            panel.removeTileSelectionListener(TileSetTabbedPane.this);
            tilePanels.remove(set);
            removeTabAt(index);
        }

        public void layerAdded(MapChangedEvent e, Layer layer) {
        }

        public void layerRemoved(MapChangedEvent e, int index) {
        }
    }
}

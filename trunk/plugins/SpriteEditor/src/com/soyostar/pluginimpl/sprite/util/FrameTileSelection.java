/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.util;

import com.soyostar.pluginimpl.sprite.model.Tile;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class FrameTileSelection implements Transferable {

    /**
     *
     */
    public static final DataFlavor TILE_FLAVOR = new DataFlavor(Tile.class, "Tile");
    private static final DataFlavor FLAVOR[] = {
        TILE_FLAVOR
    };
    private Tile data;

    /**
     *
     * @return
     */
    public Tile getTile() {
        return this.data;
    }

    /**
     *
     * @param data
     */
    public FrameTileSelection(Tile data) {
        this.data = data;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return FLAVOR;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return false;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(TILE_FLAVOR)) {
            return this.data;
        }
        return null;
    }
}

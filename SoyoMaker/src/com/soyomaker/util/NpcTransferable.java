/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.util;

import com.soyomaker.model.map.Npc;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class NpcTransferable implements Transferable {

    /**
     *
     */
    public static final DataFlavor NPC_FLAVOR = new DataFlavor(Npc.class, "Npc");
    private static final DataFlavor FLAVOR[] = {
        NPC_FLAVOR
    };
    private Npc data;

    /**
     *
     * @return
     */
    public Npc getNpc() {
        return data;
    }

    /**
     *
     * @param tile
     */
    public NpcTransferable(Npc tile) {
        this.data = tile;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return FLAVOR;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return false;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(NPC_FLAVOR)) {
            return this.data;
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.util;

import com.soyostar.pluginimpl.sprite.model.Frame;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class ActionFrameSelection implements Transferable {

    public static final DataFlavor FRAME_FLAVOR = new DataFlavor(Frame.class, "Frame");
    private static final DataFlavor FLAVOR[] = {
        FRAME_FLAVOR
    };
    private Frame data;

    public Frame getFrame() {
        return this.data;
    }

    public ActionFrameSelection(Frame data) {
        this.data = data;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return FLAVOR;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return false;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(FRAME_FLAVOR)) {
            return this.data;
        }
        return null;
    }
}

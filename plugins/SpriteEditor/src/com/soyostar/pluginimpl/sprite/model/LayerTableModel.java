/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import com.soyostar.pluginimpl.sprite.data.Proxy;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class LayerTableModel extends AbstractTableModel {

    private static final String LAYER_COLUMN_NAME[] = {
        "可见", "图层名", "贴图数"
    };
    private static final Class LAYER_COLUMN_CLASS[] = {
        Boolean.class, String.class, Integer.class
    };

    @Override
    public String getColumnName(int c) {
        return LAYER_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return LAYER_COLUMN_CLASS[c];
    }

    public int getColumnCount() {
        return LAYER_COLUMN_NAME.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Layer layer = Animation.getInstance().getFrame(Proxy.getInstance().getMainDialog().getCurrentFrameIndex()).
            getLayer(rowIndex);
        switch (columnIndex) {
            case 0:
                return layer.isVisible();
            case 1:
                return layer.getName();
            case 2:
                if (layer instanceof ModuleLayer) {
                    return ((ModuleLayer) layer).getModuleCounts();
                } else {
                    return 0;
                }

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 2:
                return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        switch (c) {
            case 0:
                Animation.getInstance().getFrame(Proxy.getInstance().getMainDialog().getCurrentFrameIndex()).
                    getLayer(r).setVisible(!Animation.getInstance().getFrame(Proxy.getInstance().getMainDialog().
                    getCurrentFrameIndex()).getLayer(r).isVisible());
                break;
            case 1:
                Animation.getInstance().getFrame(Proxy.getInstance().getMainDialog().getCurrentFrameIndex()).
                    getLayer(r).setName((String) v);
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    public int getRowCount() {
        if (Proxy.getInstance().getMainDialog().getCurrentFrameIndex() == -1) {
            return 0;
        }
        return Animation.getInstance().getFrame(Proxy.getInstance().getMainDialog().getCurrentFrameIndex()).
            getLayersCount();
    }
}

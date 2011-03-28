/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model;

import com.soyostar.model.map.TileSet;
import com.soyostar.model.map.Layer;
import com.soyostar.data.GlobalData;
import com.soyostar.listener.MapChangeListener;
import com.soyostar.listener.MapChangedEvent;
import com.soyostar.listener.TilesetChangeListener;
import com.soyostar.listener.TilesetChangedEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class TileSetTableModel extends AbstractTableModel implements MapChangeListener, TilesetChangeListener {

    private static final String TILESET_COLUMN_NAME[] = {
        "序号", "名称", "源文件", "所属地图"
    };
    private static final Class TILESET_COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, String.class
    };

    @Override
    public String getColumnName(int c) {
        return TILESET_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return TILESET_COLUMN_CLASS[c];
    }

    public int getColumnCount() {
        return TILESET_COLUMN_NAME.length;
    }
    private GlobalData data = GlobalData.getInstance();

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return data.getCurrentMap().getTileSets().get(rowIndex).getName();
            case 2:
                return data.getCurrentMap().getTileSets().get(rowIndex).getTilebmpFile();
            case 3:
                return data.getCurrentMap().getTileSets().get(rowIndex).getMap().getName();
        }
        return null;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        switch (c) {
            case 1:
                data.getCurrentMap().getTileSets().get(r).setName((String) v);
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 1:
                return true;
        }
        return false;
    }

    public int getRowCount() {
        if (data.getCurrentMap() == null) {
            return 0;
        }
        return data.getCurrentMap().getTileSets().size();
    }

    public void mapChanged(MapChangedEvent e) {
    }

    public void layerAdded(MapChangedEvent e, Layer layer) {
    }

    public void layerRemoved(MapChangedEvent e, int index) {
    }

    public void tilesetAdded(MapChangedEvent e, TileSet tileset) {
        int index = data.getCurrentMap().getTileSets().indexOf(tileset);

        if (index == -1) {
            return;
        }

        tileset.addTilesetChangeListener(this);

        fireTableRowsInserted(index, index);
    }

    public void tilesetRemoved(MapChangedEvent e, int index) {
        fireTableRowsDeleted(index - 1, index);
    }

    public void tilesetsSwapped(MapChangedEvent e, int index0, int index1) {
        fireTableRowsUpdated(index0, index1);
    }

    public void tilesetChanged(TilesetChangedEvent event) {
    }

    public void nameChanged(TilesetChangedEvent event, String oldName, String newName) {
        int index = data.getCurrentMap().getTileSets().indexOf(event.getTileset());

        if (index == -1) {
            return;
        }

        fireTableCellUpdated(index, 0);
    }

    public void sourceChanged(TilesetChangedEvent event, String oldSource, String newSource) {
        int index = data.getCurrentMap().getTileSets().indexOf(event.getTileset());

        if (index == -1) {
            return;
        }

        fireTableCellUpdated(index, 1);
    }

    public void layerVisibled(MapChangedEvent e, boolean newBool) {
    }
}
